package com.botmasterzzz.controller.queue.impl;

import com.botmasterzzz.controller.service.MessagePublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

//@Service
public class MessagePublisherImpl implements MessagePublisher {

    //@Autowired
    private RedisTemplate<String, Object> redisTemplate;
    //@Autowired
    private ChannelTopic topic;

    public MessagePublisherImpl() {
    }

    public MessagePublisherImpl(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }

}