package com.pawmot.dam.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JettyConfig {
    @Value("${jetty.host}")
    private String host;

    @Value("${jetty.port}")
    private String port;

    @Bean(name = "jettyAddress")
    String jettyAddress() {
        return String.format("http://%1$s:%2$s", host, port);
    }
}
