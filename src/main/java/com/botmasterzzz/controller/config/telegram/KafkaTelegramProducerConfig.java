package com.botmasterzzz.controller.config.telegram;

import com.botmasterzzz.bot.api.impl.objects.OutgoingMessage;
import com.botmasterzzz.controller.serializer.TelegramSendMessageSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTelegramProducerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;

    @Value(value = "${telegram.outgoing.messages.topic.name}")
    private String topicName;

    @Bean
    public Map<String, Object> producerSendMessageConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TelegramSendMessageSerializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        //props.put(ProducerConfig.ACKS_CONFIG, "0");
        //props.put(ProducerConfig.RETRIES_CONFIG, 3);
        return props;
    }

    @Bean
    public ProducerFactory<Long, OutgoingMessage> producerSendMessageFactory() {
        return new DefaultKafkaProducerFactory<>(producerSendMessageConfigs());
    }

    @Bean("sendMessage")
    public KafkaTemplate<Long, OutgoingMessage> kafkaMessageTemplate() {
        KafkaTemplate<Long, OutgoingMessage> template = new KafkaTemplate<>(producerSendMessageFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }
}