package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.ContentDownloadDto;

public interface AssetToContentDownloadDtoMapper {
    ContentDownloadDto map(Asset entity);
}
