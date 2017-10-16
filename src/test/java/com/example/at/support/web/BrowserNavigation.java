package com.example.at.support.web;

import com.example.at.config.ApplicationProperties;

public class BrowserNavigation {

    public static void openHomePage() {
        WebElementHelper.navigateToPage(ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.APP_URL));
    }
}