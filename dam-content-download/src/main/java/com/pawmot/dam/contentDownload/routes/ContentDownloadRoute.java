package com.pawmot.dam.contentDownload.routes;

import com.pawmot.dam.common.dto.ContentDownloadDto;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class ContentDownloadRoute extends SpringRouteBuilder {
    private final String jettyAddress;

    @Autowired
    public ContentDownloadRoute(@Qualifier("jettyAddress") String jettyAddress) {
        this.jettyAddress = jettyAddress;
    }

    @Override
    public void configure() throws Exception {
        from(String.format("jetty:%1$s/contentDownload?httpMethodRestrict=POST", jettyAddress))
                .unmarshal().json(Gson, ContentDownloadDto.class)
                .process(ex -> {
                    ContentDownloadDto dto = ex.getIn().getBody(ContentDownloadDto.class);
                    ex.getIn().setHeader("CamelFileName", getRelativePath(dto));
                })
                .to("file:/tmp/dam-downloads/?autoCreate=true") // TODO: Investigate the possibility of using async I/O
                .end();
    }

    private String getRelativePath(ContentDownloadDto dto) {
        String[] pathSegments = dto.getLink().split("/");
        String filename = pathSegments[pathSegments.length-1];

        return String.format("%1$s/%2$s", dto.getId(), filename);
    }
}
