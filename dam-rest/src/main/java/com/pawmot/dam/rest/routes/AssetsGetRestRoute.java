package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.domain.mapping.Mapper;
import com.pawmot.dam.rest.dto.AssetListItemDto;
import com.pawmot.dam.rest.routes.endpoints.RestEndpointFactory;
import com.pawmot.dam.rest.services.AssetService;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AssetsGetRestRoute extends SpringRouteBuilder {
    private final RestEndpointFactory restEndpointFactory;
    private final Mapper<Asset, AssetListItemDto> assetToAssetListItemDtoMapper;
    private final AssetService assetService;

    @Autowired
    public AssetsGetRestRoute(
            RestEndpointFactory restEndpointFactory,
            Mapper<Asset, AssetListItemDto> assetToAssetListItemDtoMapper,
            AssetService assetService) {
        this.restEndpointFactory = restEndpointFactory;
        this.assetToAssetListItemDtoMapper = assetToAssetListItemDtoMapper;
        this.assetService = assetService;
    }

    @Override
    public void configure() throws Exception {
        from(restEndpointFactory.create("assets", "GET"))
                .startupOrder(6)
                .bean(assetService, "getAll")
                .bean(assetToAssetListItemDtoMapper, "mapCollection")
                .marshal().json(Gson);
    }
}
