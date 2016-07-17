package com.pawmot.dam.rest.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import static com.pawmot.dam.rest.routes.CreateAssetRoute.CREATE_ASSET_URL;

@Component
public class CreateAssetJmsRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        int coresCount = Runtime.getRuntime().availableProcessors();
        int poolSize = coresCount < 2 ? coresCount : coresCount/2;
        from("jms:queue:create-asset") // TODO: move queue name to common place
                .startupOrder(4)
                .threads(poolSize)
                .to(CREATE_ASSET_URL)
                .end();
    }
}
