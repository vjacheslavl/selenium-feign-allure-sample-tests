package com.example.at.support.rest;


import com.example.at.config.ApplicationProperties;
import com.example.at.config.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import okhttp3.OkHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;


public class RestClient {

    private boolean isProxyEnabled;

    private String proxyHost;

    private int proxyPort;


    public ApplicationEndpoints createClient() {
        ObjectMapper mapper = createObjectMapper();

        Feign.Builder feignBuilder = Feign.builder()
                .decoder(new JacksonDecoder(mapper))
                .encoder(new JacksonEncoder(mapper))
                .requestInterceptor(new CustomRequestInterceptor()); // Interceptors allow to modify headers of REST requests

        if (ApplicationProperties.getBoolean(ApplicationProperties.ApplicationProperty.REST_PROXY_ENABLED)) {
            OkHttpClient proxyClient = createProxyClient();
            feignBuilder.client(new feign.okhttp.OkHttpClient(proxyClient));
        }

        return feignBuilder
                .target(ApplicationEndpoints.class, ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.SERVICE_URL));
    }

    private ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    private OkHttpClient createProxyClient() {
        proxyHost = ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.PROXY_HOST);
        proxyPort = ApplicationProperties.getInteger(ApplicationProperties.ApplicationProperty.PROXY_PORT);

        return new OkHttpClient.Builder()
                .connectTimeout(Constants.WAIT_NORMAL, TimeUnit.SECONDS)
                .writeTimeout(Constants.WAIT_NORMAL, TimeUnit.SECONDS)
                .readTimeout(Constants.WAIT_NORMAL, TimeUnit.SECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                .build();


    }
}