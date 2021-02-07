package com.botmasterzzz.controller.service.impl;

import com.botmasterzzz.controller.service.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

//@Service
public class MessagePublisherImpl implements MessagePublisher {

    private RedisTemplate<String, Object> redisTemplate;

    private ChannelTopic topic;

    public MessagePublisherImpl() {
    }

    public MessagePublisherImpl(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Autowired
    public MessagePublisherImpl(ChannelTopic topic, RedisTemplate<String, Object> redisTemplate) {
        this.topic = topic;
        this.redisTemplate = redisTemplate;
    }

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}