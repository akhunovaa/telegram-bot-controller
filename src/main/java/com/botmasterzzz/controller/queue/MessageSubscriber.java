package com.botmasterzzz.controller.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;

//@Service
public class MessageSubscriber implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSubscriber.class);

    public static List<String> messageList = new ArrayList<>();

    public void onMessage(final Message message, final byte[] pattern) {
        messageList.add(message.toString());
        LOGGER.info("Message received: {}", new String(message.getBody()));
    }

}
