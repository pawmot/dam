package com.pawmot.dam.rest.routes.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
class RestEndpointFactoryImpl implements RestEndpointFactory {
    private final String jettyAddress;

    @Autowired
    public RestEndpointFactoryImpl(@Qualifier("jettyAddress") String jettyAddress) {
        this.jettyAddress = jettyAddress;
    }

    @Override
    public String create(String resource, String httpMethod) {
        return String.format("jetty:%1$s/%2$s?httpMethodRestrict=%3$s", jettyAddress, resource, httpMethod);
    }
}
