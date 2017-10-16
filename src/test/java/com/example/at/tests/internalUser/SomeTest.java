package com.example.at.tests.internalUser;

import com.example.at.pageObjects.SearchResultsPage;
import com.example.at.pageObjects.ShopHomePage;
import com.example.at.support.web.BrowserNavigation;
import com.example.at.tests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SomeTest extends BaseTest {

    @Tag("DEV")
    @Tag("E2E")
    @DisplayName("Automation practice - search screen")
    @Test
    public void saveQuoteForInternalUser() {
        BrowserNavigation.openHomePage();

        ShopHomePage page = new ShopHomePage(); //init page object
        assertThat(page.isPageDisplayed()).as("Home Page is not displayed").isTrue();

        page.enterSearch("Blouse");
        page.clickSearch();

        SearchResultsPage searchResultsPage = new SearchResultsPage();
        assertThat(searchResultsPage.isPageDisplayed()).as("Search Result page is not displayed").isTrue();
        assertThat(searchResultsPage.getHeaderText()).contains("BLOUSE");

        assertThat(searchResultsPage.getSearchResultWidgetsCount()).isEqualTo(1);

        searchResultsPage.clickOnAddToCartForProduct("Blouse");
    }
}