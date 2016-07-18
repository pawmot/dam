package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.domain.mapping.Mapper;
import com.pawmot.dam.rest.dto.AssetMetadataDto;
import com.pawmot.dam.rest.services.AssetService;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
class UpdateAssetRoute extends SpringRouteBuilder {
    static final String UPDATE_ASSET_URL = "direct:updateAsset";

    private final Mapper<AssetMetadataDto, Asset> assetMetadataDtoAssetMapper;
    private final AssetService assetService;

    @Autowired
    public UpdateAssetRoute(Mapper<AssetMetadataDto, Asset> assetMetadataDtoAssetMapper, AssetService assetService) {
        this.assetMetadataDtoAssetMapper = assetMetadataDtoAssetMapper;
        this.assetService = assetService;
    }

    @Override
    public void configure() throws Exception {
        from(UPDATE_ASSET_URL)
                .unmarshal().json(Gson, AssetMetadataDto.class)
                .bean(assetMetadataDtoAssetMapper, "map")
                .bean(assetService, "update");
    }
}
