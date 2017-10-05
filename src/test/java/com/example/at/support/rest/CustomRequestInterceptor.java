package com.example.at.support.rest;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomRequestInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("REST API call - " + requestTemplate.toString());
    }
}