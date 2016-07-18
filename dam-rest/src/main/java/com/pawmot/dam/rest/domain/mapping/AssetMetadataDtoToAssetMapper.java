package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetMetadataDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AssetMetadataDtoToAssetMapper implements Mapper<AssetMetadataDto, Asset> {
    @Override
    public Asset map(AssetMetadataDto dto) {
        Asset asset = new Asset();
        asset.setId(UUID.fromString(dto.getId()));
        asset.setName(dto.getName());
        asset.setDescription(dto.getDescription());
        return asset;
    }
}