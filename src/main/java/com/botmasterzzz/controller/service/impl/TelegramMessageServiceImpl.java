package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.dao.MessagesDAO;
import com.botmasterzzz.controller.dao.TelegramUserDAO;
import com.botmasterzzz.controller.dto.MessageDTO;
import com.botmasterzzz.controller.entity.TelegramBotUserEntity;
import com.botmasterzzz.controller.entity.TelegramMessagesEntity;
import com.botmasterzzz.controller.service.MessageService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TelegramMessageServiceImpl implements MessageService {

    private final MessagesDAO messagesDAO;

    private final TelegramUserDAO telegramUserDAO;

    public TelegramMessageServiceImpl(MessagesDAO messagesDAO, TelegramUserDAO telegramUserDAO) {
        this.messagesDAO = messagesDAO;
        this.telegramUserDAO = telegramUserDAO;
    }

    @Async
    @Override
    @Caching(evict = {
            @CacheEvict(value = "messages-data", allEntries = true)
    })
    public void messageAdd(MessageDTO messageDTO) {
        Long telegramUserId = messageDTO.getTelegramUserId();
        TelegramBotUserEntity telegramBotUserEntity = telegramUserDAO.telegramUserGetTelegramId(telegramUserId);

        TelegramMessagesEntity telegramMessagesEntity = new TelegramMessagesEntity();
        telegramMessagesEntity.setReplyMarkup(messageDTO.getReplyMarkup());
        telegramMessagesEntity.setMessageId(messageDTO.getMessageId());
        telegramMessagesEntity.setMessageDate(new Timestamp(TimeUnit.SECONDS.toMillis(messageDTO.getMessageDate())));
        telegramMessagesEntity.setTelegramBotUserEntity(telegramBotUserEntity);
        telegramMessagesEntity.setChatId(messageDTO.getChatId());
        telegramMessagesEntity.setText(messageDTO.getText());
        telegramMessagesEntity.setCaption(messageDTO.getCaption());
        messagesDAO.messageAdd(telegramMessagesEntity);
    }

    @Override
    @Cacheable(value = "messages-data", key = "'messages-data'", unless = "#result == null")
    public List<MessageDTO> getMessageList() {
        List<MessageDTO> telegramMessageList = new ArrayList<>();
        List<TelegramMessagesEntity> telegramMessagesEntityList = messagesDAO.getMessageList();
        for (TelegramMessagesEntity telegramMessagesEntity : telegramMessagesEntityList) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(telegramMessagesEntity.getId());
            messageDTO.setChatId(telegramMessagesEntity.getChatId());
            messageDTO.setCaption(telegramMessagesEntity.getCaption());
            messageDTO.setMessageDate(telegramMessagesEntity.getMessageDate().getTime());
            messageDTO.setMessageId(telegramMessagesEntity.getMessageId());
            messageDTO.setReplyMarkup(telegramMessagesEntity.getReplyMarkup());
            messageDTO.setTelegramUserId(telegramMessagesEntity.getTelegramBotUserEntity().getTelegramId());
            messageDTO.setText(telegramMessagesEntity.getText());
            telegramMessageList.add(messageDTO);
        }
        return telegramMessageList;
    }

    @Override
    @Cacheable(value = "messages-criteria-data", key = "#icriteria", unless = "#result == null")
    public List<MessageDTO> getMessageWithCriteriaList(String icriteria) {
        List<MessageDTO> telegramMessageList = new ArrayList<>();
        List<TelegramMessagesEntity> telegramMessagesEntityList = messagesDAO.getMessageList(icriteria);
        for (TelegramMessagesEntity telegramMessagesEntity : telegramMessagesEntityList) {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(telegramMessagesEntity.getId());
            messageDTO.setChatId(telegramMessagesEntity.getChatId());
            messageDTO.setCaption(telegramMessagesEntity.getCaption());
            messageDTO.setMessageDate(telegramMessagesEntity.getMessageDate().getTime());
            messageDTO.setMessageId(telegramMessagesEntity.getMessageId());
            messageDTO.setReplyMarkup(telegramMessagesEntity.getReplyMarkup());
            messageDTO.setTelegramUserId(telegramMessagesEntity.getTelegramBotUserEntity().getTelegramId());
            messageDTO.setText(telegramMessagesEntity.getText());
            telegramMessageList.add(messageDTO);
        }
        return telegramMessageList;
    }
}
