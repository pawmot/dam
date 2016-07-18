package com.pawmot.dam.rest.dto;

public class AssetMetadataDto {
    private String id;

    private String name;

    private String description;

    private String addedDateTimeISO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddedDateTimeISO() {
        return addedDateTimeISO;
    }

    public void setAddedDateTimeISO(String addedDateTimeISO) {
        this.addedDateTimeISO = addedDateTimeISO;
    }
}
