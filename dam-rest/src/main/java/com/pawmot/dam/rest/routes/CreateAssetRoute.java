package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.mapping.AssetDtoToAssetMapper;
import com.pawmot.dam.rest.dto.AssetDto;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.pawmot.dam.rest.routes.ContentDownloadRoute.CONTENT_DOWNLOAD_ENDPOINT_URL;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class CreateAssetRoute extends SpringRouteBuilder {
    static final String CREATE_ASSET_URL = "direct:create-asset";

    private final AssetDtoToAssetMapper assetDtoToAssetMapper;

    @Autowired
    public CreateAssetRoute(AssetDtoToAssetMapper assetDtoToAssetMapper) {
        this.assetDtoToAssetMapper = assetDtoToAssetMapper;
    }

    @Override
    public void configure() throws Exception {
        from(CREATE_ASSET_URL)
                .startupOrder(1)
                .unmarshal().json(Gson, AssetDto.class)
                .bean(assetDtoToAssetMapper, "map")
                .to("jpa:Asset")
                .wireTap(CONTENT_DOWNLOAD_ENDPOINT_URL);
    }
}
