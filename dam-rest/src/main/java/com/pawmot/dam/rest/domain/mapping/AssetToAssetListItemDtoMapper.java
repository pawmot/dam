package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetListItemDto;
import org.springframework.stereotype.Component;

@Component
class AssetToAssetListItemDtoMapper implements Mapper<Asset, AssetListItemDto> {
    @Override
    public AssetListItemDto map(Asset asset) {
        AssetListItemDto dto = new AssetListItemDto();
        dto.setId(asset.getId().toString());
        dto.setName(asset.getName());
        return dto;
    }
}
