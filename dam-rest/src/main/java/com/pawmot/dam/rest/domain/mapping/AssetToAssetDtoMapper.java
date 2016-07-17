package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetDto;

public interface AssetToAssetDtoMapper {
    AssetDto map(Asset entity);
}
