package com.yelman.cloudserver.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "alerts")

public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_alert_animal"))
    private Animal animal;
    @Column(name = "alert_type", nullable = false, length = 50)
    private String alertType;
    @Column(name = "alert_message", columnDefinition = "TEXT")
    private String alertMessage;
    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    public Alerts(Long id, Animal animal, String alertType, String alertMessage, OffsetDateTime createdAt) {
        this.id = id;
        this.animal = animal;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.createdAt = createdAt;
    }

    public Alerts() {

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

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
