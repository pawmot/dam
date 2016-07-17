package com.pawmot.dam.contentDownload.converters;

import com.pawmot.dam.common.dto.ContentDownloadDto;
import org.apache.camel.CamelContext;
import org.apache.camel.Converter;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.TypeConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Component
public class ContentDownloadDtoToInputStream implements TypeConverters {
    @Autowired
    public ContentDownloadDtoToInputStream(CamelContext ctx) {
        ctx.getTypeConverterRegistry().addTypeConverters(this);
    }

    @Converter
    public InputStream convertTo(ContentDownloadDto dto) {
        try {
            return new URL(dto.getLink()).openStream();
        } catch (IOException ex) {
            throw new RuntimeCamelException(ex);
        }
    }
}
