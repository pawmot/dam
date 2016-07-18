package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.domain.mapping.Mapper;
import com.pawmot.dam.rest.dto.AssetDto;
import com.pawmot.dam.rest.routes.endpoints.RestEndpointFactory;
import org.apache.camel.Processor;
import org.apache.camel.converter.stream.InputStreamCache;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.pawmot.dam.rest.routes.CreateAssetRoute.CREATE_ASSET_URL;
import static com.pawmot.dam.rest.routes.UpdateAssetRoute.UPDATE_ASSET_URL;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
class AssetPostRestRoute extends SpringRouteBuilder {
    private final RestEndpointFactory restEndpointFactory;
    private final Mapper<Asset, AssetDto> assetToAssetDtoMapper;

    @Autowired
    public AssetPostRestRoute(
            RestEndpointFactory restEndpointFactory,
            Mapper<Asset, AssetDto> assetToAssetDtoMapper) {
        this.restEndpointFactory = restEndpointFactory;
        this.assetToAssetDtoMapper = assetToAssetDtoMapper;
    }

    @Override
    public void configure() throws Exception {
        Processor resetBuffer = ex -> {
            InputStreamCache isc = ex.getIn().getBody(InputStreamCache.class);
            isc.reset();
        }; // this sets the InputStreamCache's buffer position back to 0 after jsonpath. Without this the Gson lib ignores the body (buffer is exhausted).

        from(restEndpointFactory.create("asset", "POST"))
                .startupOrder(4)
                .choice()
                .when().jsonpath("id", true)
                    .process(resetBuffer) // TODO: Find a better solution.
                    .to(UPDATE_ASSET_URL)
                    .endChoice()
                .otherwise()
                    .process(resetBuffer) // TODO: Find a better solution.
                    .to(CREATE_ASSET_URL)
                .end()
                .bean(assetToAssetDtoMapper, "map")
                .marshal().json(Gson);
    }
}
