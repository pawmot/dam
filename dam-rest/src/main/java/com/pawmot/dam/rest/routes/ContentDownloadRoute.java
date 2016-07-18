package com.pawmot.dam.rest.routes;

import com.pawmot.dam.common.dto.ContentDownloadDto;
import com.pawmot.dam.rest.domain.Asset;
import com.pawmot.dam.rest.domain.mapping.Mapper;
import org.apache.camel.Predicate;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.apache.camel.ExchangePattern.InOnly;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class ContentDownloadRoute extends SpringRouteBuilder {
    static final String CONTENT_DOWNLOAD_ENDPOINT_URL = "direct:contentDownload";
    private final Mapper<Asset, ContentDownloadDto> assetToContentDownloadDtoMapper;

    @Value("${content-download-service.host}")
    private String contentDownloadHost;

    @Value("${content-download-service.port}")
    private String contentDownloadPort;

    @Autowired
    public ContentDownloadRoute(Mapper<Asset, ContentDownloadDto> assetToContentDownloadDtoMapper) {
        this.assetToContentDownloadDtoMapper = assetToContentDownloadDtoMapper;
    }

    @Override
    public void configure() throws Exception {
        Predicate hasContentUrl = ex -> {
            Asset asset = ex.getIn().getBody(Asset.class);
            String url = asset.getUrl();
            return url != null && !url.equals("");
        };

        from(CONTENT_DOWNLOAD_ENDPOINT_URL)
                .startupOrder(2)
                .choice()
                .when(hasContentUrl)
                    .bean(assetToContentDownloadDtoMapper, "map")
                    .marshal().json(Gson)
                    .to(InOnly, String.format("http://%1$s:%2$s/contentDownload?bridgeEndpoint=true", contentDownloadHost, contentDownloadPort))
                .endChoice()
                .end();
    }
}
