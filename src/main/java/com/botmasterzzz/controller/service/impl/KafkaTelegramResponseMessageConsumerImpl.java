package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.bot.api.impl.objects.Message;
import com.botmasterzzz.bot.api.impl.objects.ProfileInfoResponse;
import com.botmasterzzz.bot.api.impl.objects.UserProfilePhotos;
import com.botmasterzzz.controller.dto.MessageDTO;
import com.botmasterzzz.controller.service.DatabaseService;
import com.botmasterzzz.controller.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaTelegramResponseMessageConsumerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTelegramResponseMessageConsumerImpl.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(id = "telegram-response-message-service", topics = {"tg-response-message"}, containerFactory = "responseMessageFactory")
    public void consume(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long kafkaKey, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Message message) {
        if (null == message) {
            return;
        }
        LOGGER.info("{} => consumed {}", kafkaKey, message.toString());
        Long fromTelegramUserId = Long.valueOf(message.getFrom().getId());
        String replyKeyboardMarkup = null;
        try {
            replyKeyboardMarkup = null != message.getReplyMarkup() ? objectMapper.writeValueAsString(message.getReplyMarkup()) : null;
        } catch (JsonProcessingException exception) {
            LOGGER.error("Message response replyKeyboardMarkup error", exception);
        }
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTelegramUserId(message.getChatId());
        messageDTO.setText(message.getText());
        messageDTO.setReplyMarkup(replyKeyboardMarkup);
        messageDTO.setMessageId(message.getMessageId());
        messageDTO.setMessageDate(Long.valueOf(message.getDate()));
        messageDTO.setChatId(fromTelegramUserId);
        messageDTO.setCaption(message.getCaption());
        messageService.messageAdd(messageDTO);
        LOGGER.info("Message added {}", messageDTO);
        if (message.hasReplyMarkup() && !StringUtils.isEmpty(message.getCaption())) {
            databaseService.telegramMediaLogAdd(message, message.getChatId());
        }
    }

    @KafkaListener(id = "telegram-profile-info-service", topics = {"tg-response-photos-message"}, containerFactory = "profileInfoResponseMessageFactory")
    public void consume(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long kafkaKey, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, ProfileInfoResponse profileInfoResponse) {
        if (null == profileInfoResponse) {
            return;
        }
        LOGGER.info("{} => TOPIC {} => consumed {}", topic, kafkaKey, profileInfoResponse.toString());
        Long fromTelegramUserId = profileInfoResponse.getUsersTelegramIdentifier();
        UserProfilePhotos userProfilePhotos = profileInfoResponse.getUserProfilePhotos();
        databaseService.profilePhotosAdd(userProfilePhotos.getPhotos(), fromTelegramUserId);
    }

}
