package com.yelman.cloudserver.api.dto;


public class PdfSensorDto {

    String tag_id;
    Double chewingActivity;
    Integer heartBeat;
    Double temperature;
    Double humidity;
    String Date;
    Double riskScore;

    public PdfSensorDto() {

    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Double riskScore) {
        this.riskScore = riskScore;
    }

    public Integer getHeartBeat() {
        return heartBeat;
    }

    public void setHeartBeat(Integer heartBeat) {
        this.heartBeat = heartBeat;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public Double getChewingActivity() {
        return chewingActivity;
    }

    public void setChewingActivity(Double chewingActivity) {
        this.chewingActivity = chewingActivity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
