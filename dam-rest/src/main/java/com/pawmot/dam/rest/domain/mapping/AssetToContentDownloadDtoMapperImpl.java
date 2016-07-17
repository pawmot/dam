package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.common.dto.ContentDownloadDto;
import com.pawmot.dam.rest.domain.Asset;
import org.springframework.stereotype.Component;

@Component
class AssetToContentDownloadDtoMapperImpl implements AssetToContentDownloadDtoMapper {
    @Override
    public ContentDownloadDto map(Asset entity) {
        ContentDownloadDto dto = new ContentDownloadDto();
        dto.setLink(entity.getLink());
        dto.setId(entity.getId());
        return dto;
    }
}
