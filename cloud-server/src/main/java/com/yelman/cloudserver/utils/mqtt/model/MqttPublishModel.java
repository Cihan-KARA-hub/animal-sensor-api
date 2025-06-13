package com.yelman.cloudserver.utils.mqtt.model;

import org.antlr.v4.runtime.misc.NotNull;

public class MqttPublishModel {
    @NotNull
    private String topic="sensor_topic";

    @NotNull
    private boolean message;

    public Boolean getRetained() {
        return retained;
    }

    public void setRetained(Boolean retained) {
        this.retained = retained;
    }

    public String getTopic() {
        return topic;
    }

    public boolean getMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    @NotNull
    private Boolean retained;

    @NotNull
    private Integer qos;

}
