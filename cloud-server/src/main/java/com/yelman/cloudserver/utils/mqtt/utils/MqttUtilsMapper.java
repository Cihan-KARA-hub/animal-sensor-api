package com.yelman.cloudserver.utils.mqtt.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelman.cloudserver.api.dto.SensorDto;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttUtilsMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static SensorDto convertPayloadToSensorDto(MqttMessage mqttMessage) {
        try {
            String jsonPayload = new String(mqttMessage.getPayload());
            return objectMapper.readValue(jsonPayload, SensorDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
