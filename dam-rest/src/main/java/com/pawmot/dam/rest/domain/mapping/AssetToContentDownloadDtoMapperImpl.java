package com.pawmot.dam.rest.domain.mapping;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.dto.ContentDownloadDto;
import org.springframework.stereotype.Component;

@Component
public class AssetToContentDownloadDtoMapperImpl implements AssetToContentDownloadDtoMapper {
    @Override
    public ContentDownloadDto map(Asset entity) {
        ContentDownloadDto dto = new ContentDownloadDto();
        dto.setLink(entity.getLink());
        dto.setId(entity.getId());
        return dto;
    }
}
