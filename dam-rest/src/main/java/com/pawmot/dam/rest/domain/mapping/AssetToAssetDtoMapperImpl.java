package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.NewAssetDto;
import org.springframework.stereotype.Component;

@Component
class AssetToAssetDtoMapperImpl implements Mapper<Asset, NewAssetDto> {
    public NewAssetDto map(Asset entity) {
        NewAssetDto dto = new NewAssetDto();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setUrl(entity.getUrl());
        return dto;
    }
}
