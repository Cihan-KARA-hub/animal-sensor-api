package com.yelman.cloudserver.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "temperature_humidity")

public class TemperatureHumidity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_temp_animal"))
    private Animal animal;

    @Column(name = "temperature", nullable = false, precision = 5)
    private Double temperature;

    @Column(name = "humidity", precision = 5)
    private Double humidity;

    @CreationTimestamp
    @Column(name = "recorded_at")
    private OffsetDateTime recordedAt;

    public TemperatureHumidity(Long id, Animal animal, Double temperature, Double humidity, OffsetDateTime recordedAt) {
        this.id = id;
        this.animal = animal;
        this.temperature = temperature;
        this.humidity = humidity;
        this.recordedAt = recordedAt;
    }

    public TemperatureHumidity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
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

    public OffsetDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(OffsetDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}


