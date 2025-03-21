package com.yelman.cloudserver.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.Date;


@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "tag_id", unique = true, nullable = false, length = 50)
    private String tagId;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_company"))
    private Company company;
    @Column(name = "species", nullable = false, length = 50)
    private String species;
    @Column(name = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
    @CreationTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    public Animal(Long id, String tagId, Company company, String species, Date birthDate, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
        this.id = id;
        this.tagId = tagId;
        this.company = company;
        this.species = species;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Animal() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
