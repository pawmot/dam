package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class AssetDtoToAssetMapperImpl implements AssetDtoToAssetMapper {
    public Asset map(AssetDto dto) {
        Asset asset = new Asset();

        if(dto.getId() != null && !dto.getId().equals("")) {
            asset.setId(UUID.fromString(dto.getId()));
        }

        asset.setName(dto.getName());
        asset.setDescription(dto.getDescription());
        asset.setLink(dto.getLink());
        return asset;
    }

}
