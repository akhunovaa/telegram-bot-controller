package com.botmasterzzz.controller.service;

import com.botmasterzzz.controller.dto.TelegramChannelDTO;
import com.botmasterzzz.controller.dto.TelegramMessageDTO;

import java.util.List;

public interface TelegramMessagingService {

    List<TelegramChannelDTO> searchChannels(String query, int limit);

    TelegramChannelDTO getChannelMessageLink(long channelId, long messageId);

    List<TelegramMessageDTO> searchMessages(String query, long[] channelsId, int limit);

}
