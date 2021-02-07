package com.botmasterzzz.controller.config.telegram;

import com.botmasterzzz.bot.api.impl.objects.Message;
import com.botmasterzzz.bot.api.impl.objects.ProfileInfoResponse;
import com.botmasterzzz.bot.api.impl.objects.Update;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
public class KafkaTelegramConsumerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;

    @Value("${telegram.group.id}")
    private String groupId;

    @Value("${telegram.callback.group.id}")
    private String callbackGroupId;

    @Bean
    public KafkaListenerContainerFactory<?> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Update> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        factory.setMessageConverter(new BatchMessagingMessageConverter(converter()));
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> singleFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Update> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(false);
        factory.setConcurrency(5);
        factory.setRetryTemplate(kafkaRetry());
        factory.setRecoveryCallback(retryContext -> Optional.empty());
        factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> responseMessageFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Update> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(false);
        factory.setConcurrency(5);
        factory.setRetryTemplate(kafkaRetry());
        factory.setRecoveryCallback(retryContext -> Optional.empty());
        factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<?> profileInfoResponseMessageFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, ProfileInfoResponse> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(profileInfoResponseConsumerFactory());
        factory.setBatchListener(false);
        factory.setConcurrency(5);
        factory.setRetryTemplate(kafkaRetry());
        factory.setRecoveryCallback(retryContext -> Optional.empty());
        factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public ConsumerFactory<Long, Update> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public ConsumerFactory<Long, Message> messageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(responseMessagesConsumerConfigs());
    }

    @Bean
    public ConsumerFactory<Long, ProfileInfoResponse> profileInfoResponseConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(responseMessagesConsumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
        return new ConcurrentKafkaListenerContainerFactory<>();
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        //props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RoundRobinAssignor");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    @Bean
    public Map<String, Object> responseMessagesConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        //props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RoundRobinAssignor");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    @Bean
    public StringJsonMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    private RetryTemplate kafkaRetry() {
        RetryTemplate retryTemplate = new RetryTemplate();
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100 * 10000L);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        RetryPolicy retryPolicy = new NeverRetryPolicy();
        retryTemplate.setRetryPolicy(retryPolicy);
        return retryTemplate;
    }
}
