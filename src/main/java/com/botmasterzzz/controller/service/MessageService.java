package com.botmasterzzz.controller.service;

import com.botmasterzzz.controller.dto.MessageDTO;

import java.util.List;

public interface MessageService {

    void messageAdd(MessageDTO messageDTO);

    List<MessageDTO> getMessageList();

    List<MessageDTO> getMessageWithCriteriaList(String icriteria);

}
