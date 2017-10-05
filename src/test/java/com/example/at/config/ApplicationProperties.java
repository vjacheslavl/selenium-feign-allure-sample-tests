package com.example.at.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Properties;

import static com.example.at.config.ApplicationProperties.ApplicationProperty.APP_ENV;


public class ApplicationProperties {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);
    private static HashMap<String, Properties> DEFAULT_VALUES = new HashMap<String, Properties>() {
        {
            put("default", new Properties() {
                {
                    //timeout and wait time properties
                    setProperty(ApplicationProperty.WAIT_TIMEOUT_SHT.name, "3");
                    setProperty(ApplicationProperty.WAIT_TIMEOUT.name, "10");
                    setProperty(ApplicationProperty.WAIT_TIMEOUT_LNG.name, "30");

                    setProperty(ApplicationProperty.TARGET_BROWSER.name, "FIREFOX"); //PHANTOMJS, OPERA, SAFARI, EDGE, IE, CHROME, FIREFOX

                    //application URL's
                    setProperty(ApplicationProperty.APP_URL.name, "http://google.com");
                    setProperty(ApplicationProperty.SERVICE_URL.name, "http://google.com/rest");

                    //User settings
                    setProperty(ApplicationProperty.INTERNAL_USER.name, "some_user@ctco.com");
                    setProperty(ApplicationProperty.EXTERNAL_USER.name, "some_external_user@ctco.com");

                    //Proxy settings
                    setProperty(ApplicationProperty.REST_PROXY_ENABLED.name, "true");
                    setProperty(ApplicationProperty.BROWSER_PROXY_ENABLED.name, "false");
                    setProperty(ApplicationProperty.PROXY_HOST.name, "172.22.1.4");
                    setProperty(ApplicationProperty.PROXY_PORT.name, "8080");

                    //Selenium grid settings
                    setProperty(ApplicationProperty.REMOTE_DRIVER.name, "false");
                    setProperty(ApplicationProperty.SELENIUM_GRID_URL.name, "http://selenium-hub:8080/wd/hub");
                    setProperty(ApplicationProperty.SELENIUM_GRID_VIDEO_CAPTURE_URL.name, "http://selenium-hub:8080/grid/resources/remote?session=");
                    setProperty(ApplicationProperty.SELENIUM_GRID_VIDEO_CAPTURE_ENABLED.name, "true");
                    setProperty(ApplicationProperty.DESIRED_BROWSER_VERSION.name, "");
                    setProperty(ApplicationProperty.DESIRED_PLATFORM.name, "");

                    //project settings
                    setProperty(ApplicationProperty.PROJECT_NAME.name, "some project");
                    setProperty(ApplicationProperty.APM_ID.name, "ProjectId");
                }
            });
            put("other-dev", new Properties() {
                {
                    setProperty(ApplicationProperty.APP_URL.name, "http://other-env.com");
                }
            });
            put("local", new Properties() {
                {
                    setProperty(ApplicationProperty.APP_URL.name, "http://localhost:1337/#");
                    setProperty(ApplicationProperty.SERVICE_URL.name, "http:///localhost:1337/rest");
                }
            });
            put("dev-ci", new Properties() {
                {
                    setProperty(ApplicationProperty.APP_URL.name, "http://ci_url/#");
                    setProperty(ApplicationProperty.SERVICE_URL.name, "http://ci_url/rest");
                    setProperty(ApplicationProperty.REST_PROXY_ENABLED.name, "false");
                    setProperty(ApplicationProperty.BROWSER_PROXY_ENABLED.name, "false");
                    setProperty(ApplicationProperty.REMOTE_DRIVER.name, "true");
                }
            });
        }

    };

    private static String getString(String propertyName) {
        String currentEnv = System.getProperties().getProperty(
                APP_ENV.name, System.getenv(APP_ENV.name.toUpperCase().replace('.', '_')));

        if (System.getProperties().containsKey(propertyName)) {
            return System.getProperties().getProperty(propertyName);
        }
        if (currentEnv != null) {
            if (DEFAULT_VALUES.get(currentEnv).containsKey(propertyName)) {
                return DEFAULT_VALUES.get(currentEnv).getProperty(propertyName);
            }
        }
        if (DEFAULT_VALUES.get("default").containsKey(propertyName)) {
            return DEFAULT_VALUES.get("default").getProperty(propertyName);
        }

        logger.warn("Unknown application property: " + propertyName);
        throw new RuntimeException("Unknown application property: " + propertyName);
    }

    public static String getString(ApplicationProperty property) {
        return getString(property.name);
    }

    public static Integer getInteger(ApplicationProperty property) {
        return Integer.valueOf(getString(property));
    }


    public static boolean getBoolean(ApplicationProperty property) {
        return Boolean.valueOf(getString(property));
    }

    public enum ApplicationProperty {

        APP_ENV("application.env"), APP_URL("application.appUrl"), SERVICE_URL("application.serviceUrl"), TARGET_BROWSER("application.targetBrowser"),
        WAIT_TIMEOUT_SHT("application.timeoutShort"), WAIT_TIMEOUT("application.timeoutRegular"), WAIT_TIMEOUT_LNG("application.timeoutLong"),
        INTERNAL_USER("application.internalUser"), EXTERNAL_USER("application.externalUser"),
        REST_PROXY_ENABLED("proxy.restProxyEnabled"), BROWSER_PROXY_ENABLED("proxy.browserProxyEnabled"),
        PROXY_HOST("proxy.proxyHost"), PROXY_PORT("proxy.proxyPort"),
        REMOTE_DRIVER("selenium.remoteDriver"),
        SELENIUM_GRID_VIDEO_CAPTURE_URL("selenium.videoUrl"),
        SELENIUM_GRID_VIDEO_CAPTURE_ENABLED("selenium.videoCapture"),
        SELENIUM_GRID_URL("selenium.seleniumGridURL"),
        DESIRED_BROWSER_VERSION("selenium.desiredBrowserVersion"),
        PROJECT_NAME("project.name"),
        APM_ID("project.apn"),
        DESIRED_PLATFORM("selenium.desiredPlatform");

        private String name;

        ApplicationProperty(String name) {
            this.name = name;
        }
    }
}