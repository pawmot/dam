package com.pawmot.dam.rest.routes.endpoints;

public interface RestEndpointFactory {
    String create(String resource, String httpMethod);
}
