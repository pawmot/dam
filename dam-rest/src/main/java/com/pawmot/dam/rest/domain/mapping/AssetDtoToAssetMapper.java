package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.AssetDto;

public interface AssetDtoToAssetMapper {
    Asset map(AssetDto dto);
}
