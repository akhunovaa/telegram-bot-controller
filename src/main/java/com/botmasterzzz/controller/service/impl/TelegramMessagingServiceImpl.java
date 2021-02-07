package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.dto.TelegramChannelDTO;
import com.botmasterzzz.controller.dto.TelegramMessageDTO;
import com.botmasterzzz.controller.service.TelegramMessagingService;
import com.proto.telegram.*;
import io.grpc.ManagedChannel;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TelegramMessagingServiceImpl implements TelegramMessagingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramMessagingServiceImpl.class);

    private final ManagedChannel managedChannel;

    private SearchServiceGrpc.SearchServiceBlockingStub searchStub;

    public TelegramMessagingServiceImpl(ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
    }

    @Cacheable(value = "telegram-query-channels", key = "#query")
    @SuppressWarnings("deprecation")
    @Override
    public List<TelegramChannelDTO> searchChannels(String query, int limit) {
        LOGGER.info("Request to Telegram client for channel list with query => {} and limit {}", query, limit);
        List<TelegramChannelDTO> channelDTOList = new ArrayList<>();

        SearchChannel searchChannel = SearchChannel.newBuilder()
                .setLimit(limit)
                .setQuery(query)
                .build();

        SearchChannelRequest searchChannelsRequest = SearchChannelRequest.newBuilder()
                .setSearchChannel(searchChannel)
                .build();

        SearchChannelResponse searchChannelsResponse = searchStub.withDeadlineAfter(5, TimeUnit.SECONDS).searchChannels(searchChannelsRequest);
        for (Channel channelX : searchChannelsResponse.getChannelsList()) {
            TelegramChannelDTO telegramChannelDTO = new TelegramChannelDTO();
            telegramChannelDTO.setId(channelX.getId());
            telegramChannelDTO.setTitle(StringEscapeUtils.unescapeJava(channelX.getName()));
            channelDTOList.add(telegramChannelDTO);
        }
        LOGGER.info("Requested from Telegram client channels list => {}", channelDTOList);
        return channelDTOList;
    }

    @Cacheable(value = "telegram-query-message", key = "#query")
    @SuppressWarnings("deprecation")
    @Override
    public List<TelegramMessageDTO> searchMessages(String query, long[] channelsId, int limit) {
        LOGGER.info("Request to Telegram client for message list with query => {} and limit => {} and channel id's => {}", query, limit, Arrays.toString(channelsId));
        List<TelegramMessageDTO> telegramMessageDTOList = new ArrayList<>();

        for (long channelId : channelsId) {

            SearchMessage searchMessage = SearchMessage.newBuilder()
                    .setLimit(limit)
                    .setQuery(query)
                    .setChannel(channelId)
                    .build();


            SearchMessageRequest searchMessageRequest = SearchMessageRequest.newBuilder()
                    .setSearchMessage(searchMessage)
                    .build();

            SearchMessageResponse searchMessageResponse = searchStub.searchMessages(searchMessageRequest);
            for (Message message : searchMessageResponse.getMessagesList()) {
                TelegramMessageDTO telegramMessageDTO = new TelegramMessageDTO();
                telegramMessageDTO.setId(message.getId());
                telegramMessageDTO.setChatId(message.getChatId());
                telegramMessageDTO.setText(StringEscapeUtils.unescapeJava(message.getText()));

                telegramMessageDTOList.add(telegramMessageDTO);
            }

        }
        return telegramMessageDTOList;
    }

    @Cacheable(value = "telegram-query-channels-link", key = "#messageId")
    @Override
    public TelegramChannelDTO getChannelMessageLink(long channelId, long messageId) {
        TelegramChannelDTO telegramChannelDTO = new TelegramChannelDTO();

        SearchChannelLink searchChannelLink = SearchChannelLink.newBuilder()
                .setChannel(channelId)
                .setMessageId(messageId)
                .build();


        SearchChannelLinkRequest searchChannelLinkRequest = SearchChannelLinkRequest.newBuilder()
                .setSearchChannelLink(searchChannelLink)
                .build();

        SearchChannelLinkResponse searchChannelLinkResponse = searchStub.getPublicMessageLink(searchChannelLinkRequest);
        telegramChannelDTO.setId(messageId);
        telegramChannelDTO.setTitle(searchChannelLinkResponse.getChannel().getName());
        telegramChannelDTO.setLink(searchChannelLinkResponse.getChannel().getLink());
        telegramChannelDTO.setDescription(searchChannelLinkResponse.getChannel().getDescription());
        return telegramChannelDTO;
    }

    @PostConstruct
    private void initializeClient() {
        searchStub = SearchServiceGrpc.newBlockingStub(managedChannel);
    }
}
