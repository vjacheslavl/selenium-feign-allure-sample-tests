package com.example.at.pageObjects;

import com.example.at.config.webdriver.DriverBase;
import com.example.at.support.web.WebElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.at.config.Constants.WAIT_NORMAL;

public class ShopHomePage {

    Logger logger = LoggerFactory.getLogger(ShopHomePage.class);
    private WebDriver driver;

    @FindBy(css = "[id='search_query_top']")
    private WebElement tbxSearch;

    @FindBy(css = "[name='submit_search']")
    private WebElement btnSearch;

    public ShopHomePage() {
        this.driver = DriverBase.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void enterSearch(String searchString) {

        WebElementHelper.sendKeys(tbxSearch, searchString);
    }

    public void clickSearch() {
        WebElementHelper.click(btnSearch);
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(tbxSearch, WAIT_NORMAL);
    }
}