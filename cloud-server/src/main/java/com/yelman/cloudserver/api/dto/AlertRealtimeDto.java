package com.yelman.cloudserver.api.dto;


public class AlertRealtimeDto {
    double temp;
    int heart;
    Double humidity;

    public AlertRealtimeDto(double temp, int heart, Double humidity) {
        this.temp = temp;
        this.heart = heart;
        this.humidity = humidity;
    }

    public AlertRealtimeDto() {
    }

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
