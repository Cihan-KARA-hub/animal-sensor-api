package com.yelman.cloudserver.api.dto;


public class PdfSensorDto {

    String tag_id;
    Integer chewingActivity;
    Integer heartBeat;
    Double temperature;
    Double humidity;
    int date;
    Double riskScore;

    public PdfSensorDto() {

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

    public Integer getChewingActivity() {
        return chewingActivity;
    }

    public void setChewingActivity(Integer chewingActivity) {
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
