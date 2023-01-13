package com.gustavo.demo.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gustavo.demo.serializationConverter.YamlJackson2HttpMessageConverter;
import com.gustavo.demo.util.CustomMediaType;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf(CustomMediaType.APPLICATION_YML);

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        WebMvcConfigurer.super.configureContentNegotiation(configurer);

        // Via QUERY PARAM. http://localhost:8080/api/person/v1?mediaType=xml
        // configurer.favorParameter(true)
        //     .parameterName("mediaType")
        //     .ignoreAcceptHeader(true)
        //     .useRegisteredExtensionsOnly(false)
        //     .defaultContentType(MediaType.APPLICATION_JSON)
        //         .mediaType("json", MediaType.APPLICATION_JSON)
        //         .mediaType("xml", MediaType.APPLICATION_XML);

        // VIA HEADER PARAM http://localhost:8080/api/person/v1
        configurer.favorParameter(true)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMessageConverter(null));
    }
    
}
