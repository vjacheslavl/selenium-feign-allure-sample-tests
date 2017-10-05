package com.example.at.tests;

import com.example.at.support.rest.RestClient;
import com.example.at.config.webdriver.DriverBase;
import com.example.at.support.rest.ApplicationEndpoints;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseTest {
    Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    private RestClient restClient = new RestClient();
    protected ApplicationEndpoints applicationRestClient = restClient.createClient();


    @BeforeAll
    public static void instantiateDriverObject() {
        DriverBase.instantiateDriverObject();
        DriverBase.getDriver().manage().window().maximize();
    }

    @AfterAll
    public static void closeDriverObjects() {
        DriverBase.closeDriverObjects();
    }

    @AfterEach
    public void clearCookies() throws Exception {
        DriverBase.clearCookies();
    }
}