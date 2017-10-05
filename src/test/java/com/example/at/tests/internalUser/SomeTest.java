package com.example.at.tests.internalUser;

import com.example.at.config.ApplicationProperties;
import com.example.at.pageObjects.SomePage;
import com.example.at.support.rest.dto.TermsOfUseDTO;
import com.example.at.support.web.BrowserNavigation;
import com.example.at.tests.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.example.at.config.ApplicationProperties.ApplicationProperty.INTERNAL_USER;
import static org.assertj.core.api.Assertions.assertThat;

public class SomeTest extends BaseTest {

    @Tag("DEV")
    @Tag("E2E")
    @DisplayName("Product screen - save new quote")
    @Test
    public void saveQuoteForInternalUser() {
        String currentUser = ApplicationProperties.getString(INTERNAL_USER);

        //REST endpoints usage examples
        applicationRestClient.setCurrentUser(currentUser); //GET

        TermsOfUseDTO termOfUseResponse = TermsOfUseDTO.builder().username(currentUser).termsOfUse("true").build();
        applicationRestClient.acceptTermOfUse(termOfUseResponse); //PUT

        //WebDriver part example
        BrowserNavigation.openBlankQuote(); //navigation

        SomePage page = new SomePage(); //init page object

        assertThat(page.isPageDisplayed()).as("page is not displayed").isTrue(); //AssertJ example

        page.enterCompanyId("1003");
        page.clickNext();




    }
}