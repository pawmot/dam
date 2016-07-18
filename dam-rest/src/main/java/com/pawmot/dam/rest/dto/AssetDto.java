package com.pawmot.dam.rest.dto;

public class AssetDto extends BaseAssetDto {
    private String id;

    private String addedDateTimeISO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddedDateTimeISO() {
        return addedDateTimeISO;
    }

    public void setAddedDateTimeISO(String addedDateTimeISO) {
        this.addedDateTimeISO = addedDateTimeISO;
    }
}
