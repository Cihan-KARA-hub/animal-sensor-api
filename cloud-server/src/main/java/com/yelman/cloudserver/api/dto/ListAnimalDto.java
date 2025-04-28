package com.yelman.cloudserver.api.dto;

public class ListAnimalDto {
    String idTag;
    long id;
    String species;

    public ListAnimalDto(String idTag, long id, String species) {
        this.idTag = idTag;
        this.id = id;
        this.species = species;
    }

    public ListAnimalDto() {

    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdTag() {
        return idTag;
    }

    public void setIdTag(String idTag) {
        this.idTag = idTag;
    }

}
