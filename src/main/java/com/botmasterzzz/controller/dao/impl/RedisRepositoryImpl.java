package com.botmasterzzz.controller.dao.impl;

import com.botmasterzzz.controller.config.context.UserContext;
import com.botmasterzzz.controller.dao.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private static final String KEY = "UserContext";
    private static final long DEFAULT_CACHE_EXPIRATION = 3600;
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void addUserContext(final String chatId, final UserContext userContext) {
        hashOperations.put(KEY, chatId, userContext);
    }

    @Override
    public void saveTemporaryData(final String telegramUserId, final String key, final Object data) {
        hashOperations.put(key, telegramUserId, data);
    }

    @Override
    public Object retrieveTemporaryData(final String telegramUserId, final String key) {
        return hashOperations.get(key, telegramUserId);
    }

    @Override
    public void removeTemporaryData(String telegramUserId, String key) {
        hashOperations.delete(key, telegramUserId);
    }

    @Override
    public Boolean containsTemporaryData(final String telegramUserId, final String key) {
        return hashOperations.hasKey(key, telegramUserId);
    }

    @Override
    public void deleteUserContext(final String chatId) {
        hashOperations.delete(KEY, chatId);
    }

    @Override
    public UserContext findUserContext(final String chatId) {
        return (UserContext) hashOperations.get(KEY, chatId);
    }

    @Override
    public Boolean containsUserContext(final String chatId) {
        return hashOperations.hasKey(KEY, chatId);
    }

    @Override
    public Map<Object, Object> findAllUserContexts() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void flushAll() {
        redisTemplate.getRequiredConnectionFactory().getConnection().flushAll();
    }
}
