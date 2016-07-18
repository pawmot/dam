package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.domain.mapping.Mapper;
import com.pawmot.dam.rest.dto.AssetDto;
import com.pawmot.dam.rest.routes.endpoints.RestEndpointFactory;
import com.pawmot.dam.rest.services.AssetService;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AssetGetRestRoute extends SpringRouteBuilder {
    private final RestEndpointFactory restEndpointFactory;
    private final Mapper<Asset, AssetDto> assetToAssetDtoMapper;
    private final AssetService assetService;

    public AssetGetRestRoute(RestEndpointFactory restEndpointFactory,
                             Mapper<Asset, AssetDto> assetToAssetDtoMapper,
                             AssetService assetService) {
        this.restEndpointFactory = restEndpointFactory;
        this.assetToAssetDtoMapper = assetToAssetDtoMapper;
        this.assetService = assetService;
    }

    @Override
    public void configure() throws Exception {
        Processor setIdHeaderAsBody = ex -> {
            String idHeader = ex.getIn().getHeader("id", String.class);
            UUID id = UUID.fromString(idHeader);
            ex.getIn().setBody(id);
        };

        from(restEndpointFactory.create("asset", "GET"))
                .startupOrder(7)
                .process(setIdHeaderAsBody) // TODO: Find out a way to use route params instead of query string.
                .bean(assetService, "getById")
                .bean(assetToAssetDtoMapper, "map")
                .marshal().json(Gson);
    }
}
