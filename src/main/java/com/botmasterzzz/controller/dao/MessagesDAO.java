package com.botmasterzzz.controller.dao;

import com.botmasterzzz.controller.entity.TelegramMessagesEntity;

import java.util.List;

public interface MessagesDAO {

    void messageAdd(TelegramMessagesEntity telegramMessagesEntity);

    List<TelegramMessagesEntity> getMessageList();

    List<TelegramMessagesEntity> getMessageList(String icriteria);

}
