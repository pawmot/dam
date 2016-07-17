package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.mapping.AssetMapper;
import com.pawmot.dam.rest.dto.AssetDto;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AssetRestRoute extends SpringRouteBuilder {
    private final String jettyAddress;
    private final AssetMapper assetMapper;

    @Autowired
    public AssetRestRoute(@Qualifier("jettyAddress") String jettyAddress, AssetMapper assetMapper) {
        this.jettyAddress = jettyAddress;
        this.assetMapper = assetMapper;
    }

    @Override
    public void configure() throws Exception {
        from(String.format("jetty:%1$s/asset?httpMethodRestrict=POST", jettyAddress))
                .unmarshal().json(Gson, AssetDto.class)
                .bean(assetMapper, "mapDtoToEntity")
                .to("jpa:Asset")
                .bean(assetMapper, "mapEntityToDto")
                .marshal().json(Gson);
    }
}
