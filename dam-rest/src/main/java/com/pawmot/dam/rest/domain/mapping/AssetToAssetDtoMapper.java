package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetDto;
import org.springframework.stereotype.Component;

@Component
class AssetToAssetDtoMapper implements Mapper<Asset, AssetDto> {
    public AssetDto map(Asset entity) {
        AssetDto dto = new AssetDto();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setUrl(entity.getUrl());
        dto.setAddedDateTimeISO(entity.getAddedDateTime().toString());
        return dto;
    }
}
