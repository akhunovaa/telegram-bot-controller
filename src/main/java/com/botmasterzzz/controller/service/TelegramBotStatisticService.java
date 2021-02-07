package com.botmasterzzz.controller.service;

import com.botmasterzzz.bot.api.impl.objects.Message;
import com.botmasterzzz.bot.api.impl.objects.User;
import com.botmasterzzz.bot.api.impl.objects.replykeyboard.InlineKeyboardMarkup;
import com.botmasterzzz.controller.entity.TelegramBotUsageStatisticEntity;
import com.botmasterzzz.controller.entity.TelegramBotUserEntity;

import java.util.List;

public interface TelegramBotStatisticService {

    boolean telegramUserExists(long telegramUserId);

    void telegramUserAdd(User user);

    void telegramUserAdd(User user, Long referralId);

    List<TelegramBotUserEntity> getTelegramUserList();

    void telegramStatisticAdd(Message message, Long botInstance, Long telegramUserId);

    void telegramStatisticAdd(String message, Long telegramUserId);

    void telegramStatisticAdd(String query, String offset, Long botInstance, Long telegramUserId);

    void telegramStatisticAdd(Message message, Long botInstance, long telegramUserId, String callBackData);

    TelegramBotUsageStatisticEntity telegramStatisticGet(long id);

    List<TelegramBotUsageStatisticEntity> getTelegramStatisticList();

    InlineKeyboardMarkup getInlineKeyboardMarkupForMailingStatisticList(TelegramBotUserEntity telegramUser, Long fileId, Integer mediaType);

}
