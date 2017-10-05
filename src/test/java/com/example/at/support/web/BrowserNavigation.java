package com.example.at.support.web;

import com.example.at.config.ApplicationProperties;

public class BrowserNavigation {

    public static void openQuoteById(String quoteId) {
        WebElementHelper.navigateToPage(ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.APP_URL) + "/quote?qid=" + quoteId);
    }

    public static void openBlankQuote() {
        WebElementHelper.navigateToPage(ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.APP_URL) + "/quote");
    }

    public static void openCompanySelectionScreen() {
        WebElementHelper.navigateToPage(ApplicationProperties.getString(ApplicationProperties.ApplicationProperty.APP_URL) + "/selectCompany");
    }
}