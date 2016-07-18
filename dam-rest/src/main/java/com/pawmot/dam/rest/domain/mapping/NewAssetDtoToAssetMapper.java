package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.NewAssetDto;
import org.springframework.stereotype.Component;

@Component
class NewAssetDtoToAssetMapper implements Mapper<NewAssetDto, Asset> {
    public Asset map(NewAssetDto dto) {
        Asset asset = new Asset();
        asset.setName(dto.getName());
        asset.setDescription(dto.getDescription());
        asset.setUrl(dto.getUrl());
        return asset;
    }

}
