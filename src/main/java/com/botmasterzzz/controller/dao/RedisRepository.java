package com.botmasterzzz.controller.dao;

import com.botmasterzzz.controller.config.context.UserContext;

import java.util.Map;

public interface RedisRepository {

    void addUserContext(final String chatId, final UserContext userContext);

    void saveTemporaryData(final String telegramUserId, final String key, final Object data);

    Boolean containsTemporaryData(final String telegramUserId, final String key);

    Object retrieveTemporaryData(final String telegramUserId, final String key);

    void removeTemporaryData(final String telegramUserId, final String key);

    void deleteUserContext(final String chatId);

    UserContext findUserContext(final String chatId);

    Boolean containsUserContext(final String chatId);

    void flushAll();

    Map<Object, Object> findAllUserContexts();

}
