package com.pawmot.dam.common.dto;

import java.util.UUID;

public class ContentDownloadDto {
    private UUID id;

    private String link;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return String.format("ContentDownloadDto{ id = '%1$s', link = '%2$s' }", id, link);
    }
}
