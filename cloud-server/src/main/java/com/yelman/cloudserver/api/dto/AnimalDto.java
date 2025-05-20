package com.yelman.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AnimalDto {
    private Long id;
    private String tagId;
    private Long company;
    private String species;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    public AnimalDto() {
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
