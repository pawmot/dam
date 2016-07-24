package com.pawmot.dam.contentDownload.converters;

import com.pawmot.dam.common.dto.ContentDownloadDto;
import org.apache.camel.Converter;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.TypeConverters;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Converter
public class ContentDownloadDtoToInputStream implements TypeConverters {
    @Converter
    public InputStream convertTo(ContentDownloadDto dto) {
        try {
            return new URL(dto.getUrl()).openStream();
        } catch (IOException ex) {
            throw new RuntimeCamelException(ex);
        }
    }
}
