package com.yelman.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yelman.cloudserver.model.Company;
import jakarta.annotation.Nullable;

import java.time.OffsetDateTime;
import java.util.Date;

public class AnimalDto {

    private String tagId;
    @Nullable
    private Long company;
    private String species;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    public AnimalDto() {}

    public AnimalDto(String tagId, Long company, String species, Date birthDate) {
        this.tagId = tagId;
        this.company = company;
        this.species = species;
        this.birthDate = birthDate;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
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
}
