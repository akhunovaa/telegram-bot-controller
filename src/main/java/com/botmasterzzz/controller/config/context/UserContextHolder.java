package com.botmasterzzz.controller.config.context;

import com.botmasterzzz.bot.api.impl.objects.Chat;
import com.botmasterzzz.bot.api.impl.objects.Update;
import com.botmasterzzz.bot.api.impl.objects.User;

import java.util.HashMap;
import java.util.Map;

public class UserContextHolder {

    private static final Map<Long, UserContext> tgUserContexts = new HashMap<>();
    private static final ThreadLocal<UserContext> tgContextThreadLocal = new ThreadLocal<>();

    private UserContextHolder() {
        throw new IllegalStateException("Utility class");
    }

    public static UserContext currentContext() {
        return tgContextThreadLocal.get();
    }

    public static void setupdContext(Update update) {
        User user;
        Chat chat = null;
        Long chatId;

        if (update.hasCallbackQuery()) {
            user = update.getCallbackQuery().getFrom();
            if (null != update.getCallbackQuery().getMessage()) {
                chat = update.getCallbackQuery().getMessage().getChat();
                chatId = update.getCallbackQuery().getMessage().getChatId();
            } else {
                chatId = Long.valueOf(update.getCallbackQuery().getFrom().getId());
            }
        } else if (update.hasInlineQuery()) {
            user = update.getInlineQuery().getFrom();
            chatId = Long.valueOf(update.getInlineQuery().getFrom().getId());
        } else if (update.hasChosenInlineQuery()) {
            user = update.getChosenInlineQuery().getFrom();
            chatId = Long.valueOf(update.getChosenInlineQuery().getFrom().getId());
        } else {
            user = update.getMessage().getFrom();
            chat = update.getMessage().getChat();
            chatId = chat.getId();
        }

        if (!tgUserContexts.containsKey(chatId)) {
            UserContext value = new UserContext();
            value.setUpdate(update);
            value.setUser(user);
            value.setChat(chat);
            tgUserContexts.put(chatId, value);
        }
        UserContext tgContext = tgUserContexts.get(chatId);
        tgContextThreadLocal.set(tgContext);
    }
}
