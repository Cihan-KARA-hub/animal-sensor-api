package com.yelman.cloudserver.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yelman.cloudserver.model.ChewingActivity;
import com.yelman.cloudserver.model.HeartBeat;
import com.yelman.cloudserver.model.TemperatureHumidity;

import java.util.List;

@JsonSerialize
public class AnimalHealthDto {
    private List<HeartBeat> heartBeats;
    private List<TemperatureHumidity> temperatureHumidities;
    private List<ChewingActivity> chewingActivities;

    private int currentPage;
    private int totalPages;
    private long totalElements;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<TemperatureHumidity> getTemperatureHumidities() {
        return temperatureHumidities;
    }

    public void setTemperatureHumidities(List<TemperatureHumidity> temperatureHumidities) {
        this.temperatureHumidities = temperatureHumidities;
    }

    public List<HeartBeat> getHeartBeats() {
        return heartBeats;
    }

    public void setHeartBeats(List<HeartBeat> heartBeats) {
        this.heartBeats = heartBeats;
    }

    public List<ChewingActivity> getChewingActivities() {
        return chewingActivities;
    }

    public void setChewingActivities(List<ChewingActivity> chewingActivities) {
        this.chewingActivities = chewingActivities;
    }


}
