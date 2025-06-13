package com.yelman.cloudserver.utils.mqtt.model;

import com.yelman.cloudserver.api.dto.SensorDto;

public class MqttSubscribeModel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }

    public SensorDto getMessage() {
        return message;
    }

    public void setMessage(SensorDto message) {
        this.message = message;
    }

    private int id;
    private int qos;
    private SensorDto message;

}
