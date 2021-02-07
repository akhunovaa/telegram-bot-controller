package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.controller.tiktok.BotMessageHelperUtil;
import com.botmasterzzz.controller.core.Language;
import com.botmasterzzz.controller.dao.TelegramUserDAO;
import com.botmasterzzz.controller.entity.TelegramBotUserCaptionEntity;
import com.botmasterzzz.controller.entity.TelegramBotUserEntity;
import com.botmasterzzz.controller.entity.TelegramMediaLogEntity;
import com.botmasterzzz.controller.exception.UnknownUserStatusException;
import com.botmasterzzz.controller.service.DatabaseService;
import com.botmasterzzz.controller.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserDAO telegramUserDAO;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserDAO telegramUserDAO) {
        this.telegramUserDAO = telegramUserDAO;
    }


    @Override
    @Cacheable(value = "user-data", key = "#telegramUserId", unless = "#result == null")
    public TelegramBotUserEntity getTelegramUser(Long telegramUserId) {
        return telegramUserDAO.telegramUserGetTelegramId(telegramUserId);
    }

    @Override
    public TelegramBotUserEntity getTelegramUser(String queryTerm) {
        return null;
    }

    @Async
    @Override
    @CacheEvict(value = "user-data", key = "#telegramBotUserEntity.telegramId")
    public void telegramUserUpdate(TelegramBotUserEntity telegramBotUserEntity) {
        telegramUserDAO.telegramUserUpdate(telegramBotUserEntity);
    }

    @Override
    public List<TelegramBotUserEntity> getTelegramUserList() {
        return telegramUserDAO.getTelegramUserList();
    }

    @Override
    public List<TelegramBotUserEntity> searchTelegramUser(String searchTerm) {
        return telegramUserDAO.searchTelegramUser(searchTerm.trim().toLowerCase());
    }

    @Override
    public List<TelegramBotUserEntity> getTelegramUserList(Long telegramUserId) {
        return telegramUserDAO.getTelegramUserList(telegramUserId);
    }

    @Override
    @Cacheable(value = "user-caption", key = "#telegramUserId", unless = "#result == null")
    public TelegramBotUserCaptionEntity getTelegramUserCaption(Long telegramUserId) {
        return telegramUserDAO.getTelegramUserCaption(telegramUserId);
    }

    @Async
    @Override
    @CacheEvict(value = "user-caption", key = "#telegramUserId")
    public void telegramUserCaptionCreate(Long telegramUserId, String caption) {
        TelegramBotUserEntity telegramBotUserEntity = telegramUserDAO.telegramUserGetTelegramId(telegramUserId);

        TelegramBotUserCaptionEntity telegramBotUserCaptionEntity = new TelegramBotUserCaptionEntity();
        telegramBotUserCaptionEntity.setTelegramBotUserEntity(telegramBotUserEntity);
        telegramBotUserCaptionEntity.setCaption(caption);
        telegramUserDAO.telegramUserCaptionUpdate(telegramBotUserCaptionEntity);
    }

    @Async
    @Override
    public void makeUserBan(String queryTerm) {
        long userId;
        TelegramBotUserEntity telegramBotUserEntity;
        try {
            userId = Long.parseLong(queryTerm.trim());
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(userId);
        } catch (NumberFormatException numberFormatException) {
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(queryTerm);
        }
        telegramBotUserEntity.setBlocked(true);
        telegramUserDAO.telegramUserUpdate(telegramBotUserEntity);
    }

    @Async
    @Override
    public void makeUserUnBan(String queryTerm) {
        long userId;
        TelegramBotUserEntity telegramBotUserEntity;
        try {
            userId = Long.parseLong(queryTerm);
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(userId);
        } catch (NumberFormatException numberFormatException) {
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(queryTerm);
        }
        telegramBotUserEntity.setBlocked(false);
        telegramUserDAO.telegramUserUpdate(telegramBotUserEntity);
    }

    @Override
    public void makeUserPremiumed(String queryTerm) {
        long userId;
        TelegramBotUserEntity telegramBotUserEntity;
        try {
            userId = Long.parseLong(queryTerm);
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(userId);
        } catch (NumberFormatException numberFormatException) {
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(queryTerm);
        }
        telegramBotUserEntity.setPremium(true);
        telegramUserDAO.telegramUserUpdate(telegramBotUserEntity);
    }

    @Override
    public void makeUserUnPremiumed(String queryTerm) {
        long userId;
        TelegramBotUserEntity telegramBotUserEntity;
        try {
            userId = Long.parseLong(queryTerm);
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(userId);
        } catch (NumberFormatException numberFormatException) {
            telegramBotUserEntity = telegramUserDAO.telegramUserGet(queryTerm);
        }
        telegramBotUserEntity.setPremium(false);
        telegramUserDAO.telegramUserUpdate(telegramBotUserEntity);
    }

    @Async
    @Override
    @CacheEvict(value = "user-caption", key = "#requestedUserId")
    public void telegramUserCaptionUpdate(TelegramBotUserCaptionEntity telegramBotUserCaptionEntity, Long requestedUserId) {
        telegramUserDAO.telegramUserCaptionUpdate(telegramBotUserCaptionEntity);
    }

    @Override
    public String getTelegramUserNetworkStatus(Long telegramUserId, Language language) throws UnknownUserStatusException {
        String telegramUserNetworkStatus;

        String onlineStatusText = BotMessageHelperUtil.onlineStatusTextBuild(language.getShortCode());
        String awayStatusText = BotMessageHelperUtil.awayStatusTextBuild(language.getShortCode());
        String offlineStatusText = BotMessageHelperUtil.offlineStatusTextBuild(language.getShortCode());

        TelegramMediaLogEntity telegramMediaLogEntity = databaseService.getUsersLastMediaLog(telegramUserId);
        if (null != telegramMediaLogEntity) {
            Timestamp usersMediaLoggedTimestamp = null != telegramMediaLogEntity.getAudWhenCreate() ? telegramMediaLogEntity.getAudWhenCreate() : telegramMediaLogEntity.getAudWhenUpdate();
            String userMediaLoggedTime = new SimpleDateFormat("HH:mm:ss").format(usersMediaLoggedTimestamp.getTime() + TimeUnit.HOURS.toMillis(3));

            if (usersMediaLoggedTimestamp.after(new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(10)))) {
                telegramUserNetworkStatus = " [" + onlineStatusText + "]";
            } else if (usersMediaLoggedTimestamp.after(new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(45)))) {
                telegramUserNetworkStatus = " [" + awayStatusText + " | " + userMediaLoggedTime + "]";
            } else {
                telegramUserNetworkStatus = " [" + offlineStatusText + "]";
            }
        } else {
            throw new UnknownUserStatusException();
        }
        return telegramUserNetworkStatus;
    }
}
