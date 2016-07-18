package com.pawmot.dam.rest.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import static com.pawmot.dam.rest.routes.CreateAssetRoute.CREATE_ASSET_URL;

@Component
class CreateAssetJmsRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        int coresCount = Runtime.getRuntime().availableProcessors();
        int poolSize = coresCount < 2 ? coresCount : coresCount/2;
        from("jms:queue:create-asset")
                .startupOrder(5)
                .threads(poolSize)
                .to(CREATE_ASSET_URL)
                .end();
    }
}
