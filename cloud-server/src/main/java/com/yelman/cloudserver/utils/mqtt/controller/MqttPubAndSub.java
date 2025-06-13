package com.yelman.cloudserver.utils.mqtt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelman.cloudserver.api.dto.SensorDto;
import com.yelman.cloudserver.services.impl.AnimalHealthRuntimeImpl;
import com.yelman.cloudserver.utils.mqtt.config.MqttConfig;
import com.yelman.cloudserver.utils.mqtt.exceptions.ExceptionMessages;
import com.yelman.cloudserver.utils.mqtt.model.MqttPublishModel;
import com.yelman.cloudserver.utils.mqtt.model.MqttSubscribeModel;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttPubAndSub {
    private final AnimalHealthRuntimeImpl animalHealthRuntime;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MqttPubAndSub(AnimalHealthRuntimeImpl animalHealthRuntime) {
        this.animalHealthRuntime = animalHealthRuntime;
    }

    // FLUTTERDA ALARM GÖNDERME TUŞU OLACAK
    @PostMapping("publish/{animalId}")
    public void publishMessage(@RequestBody MqttPublishModel messagePublishModel, @PathVariable long animalId,
                               BindingResult bindingResult) throws MqttException {
        if (bindingResult.hasErrors()) {
            throw new MqttException(Integer.parseInt(ExceptionMessages.SOME_PARAMETERS_INVALID));
        }
        byte[] pubMessage = new byte[]{(byte) (messagePublishModel.getMessage() ? 1 : 0)};
        MqttMessage mqttMessage = new MqttMessage(pubMessage);
        mqttMessage.setQos(messagePublishModel.getQos());
        mqttMessage.setRetained(messagePublishModel.getRetained());
        MqttConfig.getInstance().publish(messagePublishModel.getTopic() + "_" + animalId, mqttMessage);
    }

    @GetMapping("subscribe")
    public List<MqttSubscribeModel> subscribeChannel(@RequestParam(value = "topic") String topic,
                                                     @RequestParam(value = "wait_millis") Integer waitMillis)
            throws InterruptedException, MqttException {
        List<MqttSubscribeModel> messages = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        MqttConfig.getInstance().subscribeWithResponse(topic, (s, mqttMessage) -> {
            SensorDto sensorDto = convertPayloadToSensorDto(mqttMessage);
            if (sensorDto != null) {
                MqttSubscribeModel mqttSubscribeModel = new MqttSubscribeModel();
                mqttSubscribeModel.setId(mqttMessage.getId());
                mqttSubscribeModel.setMessage(sensorDto);
                mqttSubscribeModel.setQos(mqttMessage.getQos());
                messages.add(mqttSubscribeModel);
                animalHealthRuntime.addAnimalHealthHourlyRuntime(mqttSubscribeModel.getMessage());

            }
            countDownLatch.countDown();
        });

        countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);
        return messages;
    }

    @GetMapping("/sensor_data_1")
    public boolean getSensorData() throws Exception {
        List<MqttSubscribeModel> messages = subscribeChannel("sensor_data_1", 5000);
        if (messages.size() > 1) {
            System.out.println("getSensorData " + messages.get(1).getMessage());
        } else {
            System.out.println("Yeterli veri yok, alınan mesaj sayısı: " + messages.size());
        }
        return true;
    }

    private SensorDto convertPayloadToSensorDto(MqttMessage mqttMessage) {
        try {
            String json = new String(mqttMessage.getPayload());
            return objectMapper.readValue(json, SensorDto.class);
        } catch (Exception e) {
            System.err.println("MQTT mesaj parse hatası: " + e.getMessage());
            return null;
        }
    }
}
