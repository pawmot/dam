package com.pawmot.dam.rest.routes;

import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.domain.mapping.AssetToContentDownloadDtoMapper;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.camel.ExchangePattern.InOnly;

@Component
public class ContentDownloadRoute extends SpringRouteBuilder {
    static final String CONTENT_DOWNLOAD_ENDPOINT_URL = "direct:contentDownload";
    private final AssetToContentDownloadDtoMapper assetToContentDownloadDtoMapper;

    @Autowired
    public ContentDownloadRoute(AssetToContentDownloadDtoMapper assetToContentDownloadDtoMapper) {
        this.assetToContentDownloadDtoMapper = assetToContentDownloadDtoMapper;
    }

    @Override
    public void configure() throws Exception {
        from(CONTENT_DOWNLOAD_ENDPOINT_URL)
                .startupOrder(2)
                .choice()
                .when(ex -> {
                    Asset asset = ex.getIn().getBody(Asset.class);
                    String link = asset.getLink();
                    return link != null && !link.equals("");
                })
                    .bean(assetToContentDownloadDtoMapper, "map")
                    .to(InOnly, "stream:out") // TODO: replace with URL of the second service
                .endChoice()
                .end();
    }
}
