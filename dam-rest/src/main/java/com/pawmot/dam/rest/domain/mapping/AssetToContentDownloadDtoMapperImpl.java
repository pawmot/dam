package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.common.dto.ContentDownloadDto;
import com.pawmot.dam.rest.domain.Asset;
import org.springframework.stereotype.Component;

@Component
class AssetToContentDownloadDtoMapperImpl implements Mapper<Asset, ContentDownloadDto> {
    @Override
    public ContentDownloadDto map(Asset entity) {
        ContentDownloadDto dto = new ContentDownloadDto();
        dto.setUrl(entity.getUrl());
        dto.setId(entity.getId());
        return dto;
    }
}
