package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.domain.mapping.Mapper;
import com.pawmot.dam.rest.dto.NewAssetDto;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.pawmot.dam.rest.routes.CreateAssetRoute.CREATE_ASSET_URL;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class CreateAssetRestRoute extends SpringRouteBuilder {
    private final String jettyAddress;
    private final Mapper<Asset, NewAssetDto> assetToAssetDtoMapper;

    @Autowired
    public CreateAssetRestRoute(
            @Qualifier("jettyAddress") String jettyAddress,
            Mapper<Asset, NewAssetDto> assetToAssetDtoMapper) {
        this.jettyAddress = jettyAddress;
        this.assetToAssetDtoMapper = assetToAssetDtoMapper;
    }

    @Override
    public void configure() throws Exception {
        from(String.format("jetty:%1$s/asset?httpMethodRestrict=POST", jettyAddress))
                .startupOrder(3)
                .to(CREATE_ASSET_URL)
                .bean(assetToAssetDtoMapper, "map")
                .marshal().json(Gson);
    }
}
