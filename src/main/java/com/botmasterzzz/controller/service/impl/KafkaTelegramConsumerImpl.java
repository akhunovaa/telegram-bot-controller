package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.controller.container.BotApiMethodContainer;
import com.botmasterzzz.controller.controller.BotApiMethodController;
import com.botmasterzzz.controller.controller.tiktok.BotMessageHelperUtil;
import com.botmasterzzz.controller.dto.CallBackData;
import com.botmasterzzz.controller.entity.TelegramBotUserEntity;
import com.botmasterzzz.controller.service.ControllerProcessService;
import com.botmasterzzz.controller.service.TelegramBotStatisticService;
import com.botmasterzzz.controller.service.TelegramUserService;
import com.botmasterzzz.controller.service.UserContextService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaTelegramConsumerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTelegramConsumerImpl.class);

    private static final BotApiMethodContainer container = BotApiMethodContainer.getInstance();

    private final Gson gson;
    private final TelegramBotStatisticService telegramBotStatisticService;
    private final UserContextService redisUserContextService;
    private final TelegramUserService telegramUserService;
    private final ControllerProcessService controllerProcessService;


    @Autowired
    public KafkaTelegramConsumerImpl(Gson gson, TelegramBotStatisticService telegramBotStatisticService, UserContextService redisUserContextService, TelegramUserService telegramUserService, ControllerProcessService controllerProcessService) {
        this.gson = gson;
        this.telegramBotStatisticService = telegramBotStatisticService;
        this.redisUserContextService = redisUserContextService;
        this.telegramUserService = telegramUserService;
        this.controllerProcessService = controllerProcessService;
    }

    @KafkaListener(id = "telegram-instance-service", topics = {"tg-income-message"}, containerFactory = "singleFactory")
    public void consume(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long kafkaKeyDTO, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Update update) {
        if (null == update) {
            return;
        }
        LOGGER.info("{} => consumed {}", kafkaKeyDTO, update.toString());
        Long requestedUserId = BotMessageHelperUtil.invokeTelegramUserId(update);
        redisUserContextService.setupUserContext(update);
        redisUserContextService.setTelegramInstanceId(requestedUserId, kafkaKeyDTO);
        BotApiMethodController methodController;
        if (update.hasMessage() && null != update.getMessage().getText() && update.getMessage().getText().contains("/start") && update.getMessage().getText().split(" ").length > 1) {
            Long referralId = Long.valueOf(update.getMessage().getText().split(" ")[1]);
            redisUserContextService.setReferralId(requestedUserId, referralId);
            redisUserContextService.setMediaLinkId(requestedUserId, referralId);
            dataWrite(kafkaKeyDTO, update, kafkaKeyDTO);
            BotApiMethodController linkedMedia = container.getControllerMap().get("tiktok-linked-media");
            BotApiMethodController linkedMediaReload = container.getControllerMap().get("tiktok-lnkrld");
            controllerProcessService.process(linkedMedia, update, kafkaKeyDTO);
            controllerProcessService.process(linkedMediaReload, update, kafkaKeyDTO);
            return;
        }
        dataWrite(kafkaKeyDTO, update, kafkaKeyDTO);

        if (update.hasCallbackQuery()) {
            requestedUserId = Long.valueOf(update.getCallbackQuery().getFrom().getId());
        } else if (update.hasInlineQuery()) {
            requestedUserId = Long.valueOf(update.getInlineQuery().getFrom().getId());
        } else if (update.hasChosenInlineQuery()) {
            requestedUserId = Long.valueOf(update.getChosenInlineQuery().getFrom().getId());
            redisUserContextService.setChosenInlineMessageId(requestedUserId, update.getChosenInlineQuery().getInlineMessageId());
        } else {
            requestedUserId = Long.valueOf(update.getMessage().getFrom().getId());
        }
        TelegramBotUserEntity telegramBotUserEntity = telegramUserService.getTelegramUser(requestedUserId);
        String languageCode = null == telegramBotUserEntity.getLanguageCode() ? "ru" : telegramBotUserEntity.getLanguageCode();
        if (telegramBotUserEntity.isBlocked()) {
            methodController = container.getControllerMap().get("command-user-banned");
        } else {
            methodController = getHandle(update);
        }
        redisUserContextService.setLanguage(requestedUserId, languageCode);
        if (update.hasInlineQuery()) {
            controllerProcessService.processInline(methodController, update, kafkaKeyDTO);
            return;
        }
        boolean tiktokFetchLoading = redisUserContextService.isLoading(requestedUserId);
        boolean instagramFetchLoading = redisUserContextService.isInstLoading(requestedUserId);
        boolean youTubeFetchLoading = redisUserContextService.isYouTubeLoading(requestedUserId);
        String message = BotMessageHelperUtil.invokeMessage(update);
        if (tiktokFetchLoading && (!StringUtils.isEmpty(message) && !message.contains("tktk-close"))) {
            BotApiMethodController loadingController = container.getControllerMap().get("tiktok-loading");
            controllerProcessService.process(loadingController, update, kafkaKeyDTO);
        }
        if (instagramFetchLoading && (!StringUtils.isEmpty(message) && !message.contains("instcls"))) {
            BotApiMethodController loadingController = container.getControllerMap().get("tiktok-iloading");
            controllerProcessService.process(loadingController, update, kafkaKeyDTO);
        }
        if (youTubeFetchLoading && (!StringUtils.isEmpty(message) && !message.contains("youcls"))) {
            BotApiMethodController loadingController = container.getControllerMap().get("tiktok-yloading");
            controllerProcessService.process(loadingController, update, kafkaKeyDTO);
        }
        controllerProcessService.process(methodController, update, kafkaKeyDTO);
    }

//    @KafkaListener(id = "telegram-message-service", topics = {"telegram-callback-message"}, containerFactory = "singleMessageFactory")
//    public void consumeMessage(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Message message) {
//        LOGGER.info("{} => consumed {}", key, message.toString());
//        String mediaFileId = message.getVideo().getFileId();
//        telegramMediaService.telegramUserMediaUpdate(key, mediaFileId);
//    }


    private BotApiMethodController getHandle(Update update) {
        //Long chatId = BotMessageHelperUtil.invokeChatId(update);
        Long requestedUserId = BotMessageHelperUtil.invokeTelegramUserId(update);
        CallBackData callBackData;
        BotApiMethodController controller;
        boolean remain = redisUserContextService.isRemain(requestedUserId);
        boolean nameEditState = redisUserContextService.isNameEdit(requestedUserId);
        boolean captionEditState = redisUserContextService.isCaptionEdit(requestedUserId);
        boolean postEditState = redisUserContextService.isPostEdit(requestedUserId);
        boolean commentAwait = redisUserContextService.isCommentRemain(requestedUserId);
        boolean tagRemain = redisUserContextService.isTagRemain(requestedUserId);
        boolean instTagRemain = redisUserContextService.isInstTagRemain(requestedUserId);
        boolean youTubeQueryRemain = redisUserContextService.isYouTubeQueryRemain(requestedUserId);
        boolean kinoRemain = redisUserContextService.isKinoRemain(requestedUserId);
        boolean userSearchRemain = redisUserContextService.isUserBanList(requestedUserId);
        //boolean isTikTokUrlRemain = redisUserContextService.isTiktokUrlRemain(requestedUserId);
        int instanceId = Math.toIntExact(redisUserContextService.getTelegramInstanceId(requestedUserId));
        String message;
        if (update.hasInlineQuery()) {
            message = update.getInlineQuery().getQuery();
        } else if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getData();
        } else if (update.hasChosenInlineQuery()) {
            message = update.getChosenInlineQuery().getQuery();
        } else {
            message = update.getMessage().getText();
        }
        if (null != message && message.contains("/start") && message.split(" ").length > 1) {
            message = message.split(" ")[0];
        }
        if (null != message && message.contains("/kino") && message.split(" ").length > 1) {
            String query = StringUtils.substringAfter(message, " ");
            redisUserContextService.setLastQuery(requestedUserId, query);
            controller = container.getControllerMap().get("tiktok-kino-search");
            return controller;
        }
        if (null != message && message.contains("/soundcloud") && message.split(" ").length > 1) {
            String query = StringUtils.substringAfter(message, " ");
            redisUserContextService.setLastQuery(requestedUserId, query);
            controller = container.getControllerMap().get("tiktok-soundcloud");
            return controller;
        }
        if (null != message && message.contains("/ban") && message.split(" ").length > 1) {
            String query = StringUtils.substringAfter(message, " ");
            redisUserContextService.setUserForBan(requestedUserId, query);
            controller = container.getControllerMap().get("tiktok-user-ban");
            return controller;
        }
        if (null != message && message.contains("/unban") && message.split(" ").length > 1) {
            String query = StringUtils.substringAfter(message, " ");
            redisUserContextService.setUserForBan(requestedUserId, query);
            controller = container.getControllerMap().get("tiktok-user-unban");
            return controller;
        }
        if (null != message && message.contains("/givepremium") && message.split(" ").length > 1) {
            String query = StringUtils.substringAfter(message, " ");
            redisUserContextService.setUserForBan(requestedUserId, query);
            controller = container.getControllerMap().get("tiktok-user-givepremium");
            return controller;
        }
        if (null != message && message.contains("/unpremium") && message.split(" ").length > 1) {
            String query = StringUtils.substringAfter(message, " ");
            redisUserContextService.setUserForBan(requestedUserId, query);
            controller = container.getControllerMap().get("tiktok-user-unpremium");
            return controller;
        }
        if (null != message && message.contains("/searchuser") && message.split(" ").length > 1) {
            String query = StringUtils.substringAfter(message, " ");
            redisUserContextService.setUserBanList(requestedUserId, true);
            controller = container.getControllerMap().get("tiktok-/searchuser");
            return controller;
        }
        if (update.hasCallbackQuery()) {
            callBackData = gson.fromJson(update.getCallbackQuery().getData(), CallBackData.class);
            redisUserContextService.setCallBackData(requestedUserId, callBackData);
            message = callBackData.getPath();
            if (commentAwait && !message.equals("cncl")) {
                controller = container.getControllerMap().get("tiktok-comment-write");
                return controller;
            }
            if (remain && message.equals("close")) {
                controller = container.getControllerMap().get("tiktok-close");
                return controller;
            }
            if (remain && message.equals("timecnsl")) {
                controller = container.getControllerMap().get("tiktok-timecnsl");
                return controller;
            }
        }
        switch (instanceId) {
            case 38:
                controller = container.getControllerMap().get("rune-" + message);
                break;
            case 41:
                controller = container.getControllerMap().get("lady-" + message);
                break;
            case 42:
                boolean searchRemain = redisUserContextService.isQueryAwait(requestedUserId);
                controller = !searchRemain ? container.getControllerMap().get("search-" + message) : container.getControllerMap().get("search-search");
                if (update.hasInlineQuery()) {
                    controller = container.getControllerMap().get("search-inline-query");
                }
                return null != controller ? controller : container.getControllerMap().get("search-" + message);
            case 1:
                if (update.hasCallbackQuery()) {
                    callBackData = gson.fromJson(update.getCallbackQuery().getData(), CallBackData.class);
                    redisUserContextService.setCallBackData(requestedUserId, callBackData);
                    message = callBackData.getPath();
                }
                remain = redisUserContextService.isRemain(requestedUserId);
                controller = !remain ? container.getControllerMap().get("getparts-" + message) : container.getControllerMap().get("getparts-SECRET-FIND");
                break;
            case 33:
                controller = container.getControllerMap().get("taxi-" + message);
                break;
            case 35:
                controller = container.getControllerMap().get("parkon-" + message);
                break;
            case 37:
                controller = !remain ? container.getControllerMap().get("portfolio-" + message) : container.getControllerMap().get("portfolio-media-upload");
                if (remain && null != message && message.equals("❌Отмена")) {
                    controller = container.getControllerMap().get("portfolio-" + message);
                } else if (remain && null != message && (message.contains("/watch?v=") || message.contains("https://youtu.be/"))) {
                    controller = container.getControllerMap().get("portfolio-media-upload-link");
                    break;
                } else if (remain && !(update.getMessage().hasPhoto() || update.getMessage().hasVideo() || update.getMessage().hasDocument())) {
                    controller = container.getControllerMap().get("portfolio-media-upload-error");
                } else if (commentAwait) {
                    controller = container.getControllerMap().get("portfolio-comment-upload");
                }
                break;
            default:
                controller = !remain ? container.getControllerMap().get("tiktok-" + message) : container.getControllerMap().get("tiktok-media-upload");
                if (update.hasMessage() && null != update.getMessage().getForwardFromChat() && update.getMessage().getForwardFromChat().isChannelChat()) {
                    Long targetChannelId = update.getMessage().getForwardFromChat().getId();
                    redisUserContextService.setTargetChannelIdForPosting(requestedUserId, targetChannelId);
                    controller = container.getControllerMap().get("tiktok-target-channel-apply");
                    break;
                }
                if (remain && null != message && (message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar"))) {
                    controller = container.getControllerMap().get("tiktok-" + message);
                } else if ((youTubeQueryRemain && !instTagRemain && !tagRemain) && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("youcls")) {
                    controller = container.getControllerMap().get("youtube-tag-apply");
                    break;
                } else if ((tagRemain && !instTagRemain && !youTubeQueryRemain) && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("tktk-close")) {
                    controller = container.getControllerMap().get("tiktok-tag-apply");
                    break;
                } else if ((!tagRemain && instTagRemain && !youTubeQueryRemain) && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("instcls")) {
                    controller = container.getControllerMap().get("instagram-tag-apply");
                    break;
                } else if (kinoRemain && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("tiktok-kinocnsl")) {
                    controller = container.getControllerMap().get("tiktok-kino-search");
                    break;
                } else if (userSearchRemain && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("cncl")) {
                    controller = container.getControllerMap().get("tiktok-/unban");
                    break;
                } else if (remain && update.hasMessage()) {
                    if (update.hasMessage() && !(update.getMessage().hasPhoto() || update.getMessage().hasVideo() || update.getMessage().hasDocument())) {
                        controller = container.getControllerMap().get("tiktok-media-upload-error");
                    }
                } else if (commentAwait) {
                    if (!container.getControllerMap().containsKey("tiktok-" + message)) {
                        controller = container.getControllerMap().get("tiktok-comment-upload");
                    }
                } else if (postEditState && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("tktk-close")) {
                    controller = container.getControllerMap().get("tiktok-post-edit");
                } else if (nameEditState && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("tktk-close")) {
                    controller = container.getControllerMap().get("tiktok-name-update");
                } else if (captionEditState && null != message && !(message.equals("❌Отмена") || message.equals("❌Cancel") || message.equals("❌取消") || message.equals("❌Cancelar")) && !message.equals("tktk-close")) {
                    controller = container.getControllerMap().get("tiktok-caption-update");
                }
                if (update.hasMessage() && update.getMessage().hasLocation()) {
                    controller = container.getControllerMap().get("tiktok-location-processor");
                }
                if (update.hasInlineQuery()) {
                    controller = container.getControllerMap().get("tiktok-inline-query");
                }
                if (null != message && message.equals("tag-next")) {
                    controller = container.getControllerMap().get("tiktok-tag-next");
                }
        }
        if (null != controller) {
            return controller;
        } else {
            if (null != message && (message.contains("instagram-code-") || message.contains("instagram-proxy-"))) {
                return container.getControllerMap().get("tiktok-instagram-code");
            }
            if (null != message && (message.contains("m.instagram.com") || message.contains("instagram.com"))) {
                return container.getControllerMap().get("instagram-tag-apply");
            }
            if (null != message && (message.contains("m.tiktok.com") || message.contains("https://vm.tiktok.com") || message.contains("tiktok.com") || message.contains("https://www.tiktok.com") || (message.length() > 2 && message.substring(0, 1).trim().equals("#")))) {
                return container.getControllerMap().get("tiktok-tag-apply");
            }
            if (null != message && (message.contains("youtube.com") || message.contains("youtu.be"))) {
                return container.getControllerMap().get("youtube-tag-apply");
            }
            if (null != message && (message.contains("soundcloud.com"))) {
                return container.getControllerMap().get("tiktok-soundcloud");
            }
            return container.getControllerMap().get("command-unknown-error");
        }
    }

    private void dataWrite(Long instanceId, Update update, Long kafkaKey) {
        controllerProcessService.processProfilePhotosMethod(container.getControllerMap().get("tiktok-profile-photos"), update, kafkaKey);
        if (update.hasCallbackQuery()) {
            boolean userExists = telegramBotStatisticService.telegramUserExists(update.getCallbackQuery().getFrom().getId());
            if (!userExists) {
                LOGGER.info("User does not exists: {}", update.getCallbackQuery().getFrom().getId());
                telegramBotStatisticService.telegramUserAdd(update.getCallbackQuery().getFrom());
                LOGGER.info("User added: {}", update.getCallbackQuery().getFrom().getId());
            }
            LOGGER.info("User exists statistic add {}", update.getCallbackQuery().getFrom().getId());
            telegramBotStatisticService.telegramStatisticAdd(update.getCallbackQuery().getMessage(), instanceId, update.getCallbackQuery().getFrom().getId(), update.getCallbackQuery().getData());
        } else if (update.hasInlineQuery()) {
            boolean userExists = telegramBotStatisticService.telegramUserExists(update.getInlineQuery().getFrom().getId());
            if (!userExists) {
                LOGGER.info("User does not exists: {}", update.getInlineQuery().getFrom().getId());
                telegramBotStatisticService.telegramUserAdd(update.getInlineQuery().getFrom());
                LOGGER.info("User added: {}", update.getInlineQuery().getFrom().getId());
            }
            LOGGER.info("User exists statistic add {}", update.getInlineQuery().getFrom().getId());
            telegramBotStatisticService.telegramStatisticAdd(update.getInlineQuery().getQuery(), update.getInlineQuery().getOffset(), instanceId, Long.valueOf(update.getInlineQuery().getFrom().getId()));
        } else if (update.hasChosenInlineQuery()) {
            boolean userExists = telegramBotStatisticService.telegramUserExists(update.getChosenInlineQuery().getFrom().getId());
            if (!userExists) {
                LOGGER.info("User does not exists: {}", update.getChosenInlineQuery().getFrom().getId());
                telegramBotStatisticService.telegramUserAdd(update.getChosenInlineQuery().getFrom());
                LOGGER.info("User added: {}", update.getChosenInlineQuery().getFrom().getId());
            }
            LOGGER.info("User exists statistic add {}", update.getChosenInlineQuery().getFrom().getId());
            telegramBotStatisticService.telegramStatisticAdd(update.getChosenInlineQuery().getQuery(), update.getChosenInlineQuery().getResultId(), instanceId, Long.valueOf(update.getChosenInlineQuery().getFrom().getId()));
        } else {
            Long requestedUserId = BotMessageHelperUtil.invokeTelegramUserId(update);
            boolean userExists = telegramBotStatisticService.telegramUserExists(requestedUserId);
            if (!userExists) {
                Long referralId = redisUserContextService.getReferralId(requestedUserId);
                LOGGER.info("User does not exists: {} and referralId {}", update.getMessage().getFrom().getId(), referralId);
                telegramBotStatisticService.telegramUserAdd(update.getMessage().getFrom(), referralId);
                LOGGER.info("User added: {}", update.getMessage().getFrom().getId());
            }
            LOGGER.info("User exists statistic add: {}", update.getMessage().getFrom().getId());
            telegramBotStatisticService.telegramStatisticAdd(update.getMessage(), instanceId, requestedUserId);
        }
    }

}
