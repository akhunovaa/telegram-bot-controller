package com.botmasterzzz.controller.deserializer;

import com.botmasterzzz.controller.dto.KafkaKeyDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class KafkaKeyDeserializer implements Deserializer<KafkaKeyDTO> {

    @Override
    public KafkaKeyDTO deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        KafkaKeyDTO data = null;
        try {
            data = mapper.readValue(arg1, KafkaKeyDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
