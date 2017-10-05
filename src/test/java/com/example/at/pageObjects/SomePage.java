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

public class SomePage {

    Logger logger = LoggerFactory.getLogger(SomePage.class);
    private WebDriver driver;
    @FindBy(css = "[id^='undefined-undefined-CompanyID']")
    private WebElement tbxCompanyId;

    @FindBy(xpath = "//div/span[text()='Next']")
    private WebElement btnNext;

    public SomePage() {
        logger.info("Instantiating Company selection page");
        this.driver = DriverBase.getDriver();
        PageFactory.initElements(driver, this);
    }

    public String getCompanyId() {

        return WebElementHelper.getValue(tbxCompanyId);
    }

    public void enterCompanyId(String textValue) {

        WebElementHelper.sendKeys(tbxCompanyId, textValue);
    }

    public void clickNext() {
        WebElementHelper.click(btnNext);
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(tbxCompanyId, WAIT_NORMAL);
    }
}