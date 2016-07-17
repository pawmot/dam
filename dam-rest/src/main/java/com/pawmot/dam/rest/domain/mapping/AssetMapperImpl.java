package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class AssetMapperImpl implements AssetMapper {
    public Asset mapDtoToEntity(AssetDto dto) {
        Asset asset = new Asset();

        if(dto.getId() != null && !dto.getId().equals("")) {
            asset.setId(UUID.fromString(dto.getId()));
        }

        asset.setName(dto.getName());
        asset.setDescription(dto.getDescription());
        asset.setLink(dto.getLink());
        return asset;
    }

    public AssetDto mapEntityToDto(Asset entity) {
        AssetDto dto = new AssetDto();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLink(entity.getLink());
        return dto;
    }
}
