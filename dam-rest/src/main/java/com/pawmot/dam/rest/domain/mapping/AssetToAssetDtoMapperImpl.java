package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetDto;
import org.springframework.stereotype.Component;

@Component
class AssetToAssetDtoMapperImpl implements AssetToAssetDtoMapper {
    public AssetDto map(Asset entity) {
        AssetDto dto = new AssetDto();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLink(entity.getLink());
        return dto;
    }
}
