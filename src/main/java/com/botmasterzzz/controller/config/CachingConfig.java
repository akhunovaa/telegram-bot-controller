package com.botmasterzzz.controller.config;

import com.botmasterzzz.controller.service.MessagePublisher;
import com.botmasterzzz.controller.service.impl.MessagePublisherImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CachingConfig extends CachingConfigurerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachingConfig.class);

    private static final long DEFAULT_CACHE_EXPIRATION = 3600;

    @Value("${redis.hostName}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

    @Value("${redis.main.max.connection}")
    private int mainMaxConnection;

    @Value("${redis.main.min.idle.connection}")
    private int mainMinIdleConnection;

    @Value("${redis.user.context.max.connection}")
    private int userContextMaxConnection;

    @Value("${redis.main.database}")
    private int redisMainDatabase;

    @Value("${redis.user.context.database}")
    private int redisUserContextDatabase;


    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setDatabase(redisMainDatabase);
        redisStandaloneConfiguration.setPort(port);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpccb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpccb.poolConfig(jedisPoolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpccb.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactoryForUserContext(JedisPoolConfig jedisPoolConfig) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setDatabase(redisUserContextDatabase);
        redisStandaloneConfiguration.setPort(port);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpccb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpccb.poolConfig(jedisPoolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpccb.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(mainMaxConnection);
        jedisPoolConfig.setMinIdle(mainMinIdleConnection);
        jedisPoolConfig.setMaxWaitMillis(timeout);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    @Bean
    public RedisCacheManager cacheManager(JedisPoolConfig jedisPoolConfig) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues().entryTtl(Duration.ofSeconds(DEFAULT_CACHE_EXPIRATION))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        defaultCacheConfiguration.usePrefix();
        cacheConfigurations.put("user-exists-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("today-media-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("yesterday-media-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("actual-media-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("personal-media-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("requested-media-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("typed-media-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("top-active-users", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("top-users", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("users-activity-statistic-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("self-activity-statistic-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("single-media-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("user-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(45)));
        cacheConfigurations.put("actual-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("full-user-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("media-comments-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("user-context", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(5)));
        cacheConfigurations.put("telegram-query-channels", defaultCacheConfiguration.entryTtl(Duration.ofHours(12)));
        cacheConfigurations.put("telegram-query-message", defaultCacheConfiguration.entryTtl(Duration.ofHours(12)));
        cacheConfigurations.put("telegram-query-channels-link", defaultCacheConfiguration.entryTtl(Duration.ofHours(12)));
        cacheConfigurations.put("search-media-list-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(15)));
        cacheConfigurations.put("search-media-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("media-touch-count", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("media-user-touch-optional-heart", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("media-user-touch-optional-like", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("media-user-touch-optional-dislike", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("inline-query-result-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(6)));
        cacheConfigurations.put("inline-query-result-data-2", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(6)));
        cacheConfigurations.put("user-report-xls", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("user-report-pdf", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("user-logged-data", defaultCacheConfiguration.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("media-mailing-data", defaultCacheConfiguration.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("messages-data", defaultCacheConfiguration.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("messages-criteria-data", defaultCacheConfiguration.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("kino-media", defaultCacheConfiguration.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("instagram-hashtag-media-list", defaultCacheConfiguration.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("instagram-code-media", defaultCacheConfiguration.entryTtl(Duration.ofHours(1)));
        cacheConfigurations.put("user-caption", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("youtube-media-data", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        cacheConfigurations.put("youtube-media-search", defaultCacheConfiguration.entryTtl(Duration.ofMinutes(30)));
        return RedisCacheManager.builder(redisConnectionFactory(jedisPoolConfig)).cacheDefaults(defaultCacheConfiguration)
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisPoolConfig jedisPoolConfig) {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactoryForUserContext(jedisPoolConfig));
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    public MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter();
    }

    @Bean
    RedisMessageListenerContainer redisContainer(JedisPoolConfig jedisPoolConfig) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactoryForUserContext(jedisPoolConfig));
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher(JedisPoolConfig jedisPoolConfig) {
        return new MessagePublisherImpl(redisTemplate(jedisPoolConfig), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }

    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                LOGGER.error("cache : {} , key : {}", cache, key);
                LOGGER.error("handleCacheGetError", exception);
                super.handleCacheGetError(exception, cache, key);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                LOGGER.error("cache : {} , key : {} , value : {} ", cache, key, value);
                LOGGER.error("handleCachePutError", exception);
                super.handleCachePutError(exception, cache, key, value);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                LOGGER.error("cache : {} , key : {}", cache, key);
                LOGGER.error("handleCacheEvictError", exception);
                super.handleCacheEvictError(exception, cache, key);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                LOGGER.error("cache : {} ", cache);
                LOGGER.error("handleCacheClearError", exception);
                super.handleCacheClearError(exception, cache);
            }
        };
    }

}
