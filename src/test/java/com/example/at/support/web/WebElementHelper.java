package com.example.at.support.web;

import com.google.common.util.concurrent.Uninterruptibles;
import com.example.at.config.webdriver.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.example.at.config.Constants.WAIT_SMALL;
import static org.junit.Assert.fail;


public class WebElementHelper {
    private static final Logger logger = LoggerFactory.getLogger(WebElementHelper.class);

    public static boolean isElementDisplayed(WebElement webElement) {
        return isElementDisplayed(webElement, WAIT_SMALL);
    }

    public static boolean isElementDisplayed(WebElement webElement, int timeOut) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(DriverBase.getDriver(), timeOut);
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.isDisplayed();
        } catch (NoSuchElementException | TimeoutException ne) {
            return false;
        } catch (StaleElementReferenceException ex) {
            return isElementDisplayed(webElement, timeOut);
        }
    }


    public static void waitForVisibility(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverBase.getDriver(), WAIT_SMALL);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException te) {
            logger.error(te.getMessage());
            fail("Element '" + element + "' not found after waiting for it's visibility");
        } catch (NoSuchElementException ne) {
            logger.error(ne.getMessage());
            fail("Element '" + element + "' not found, unable to locate it");
        } catch (Exception e) {
            logger.error(e.getMessage());
            fail("Element '" + element + "' not found");
        }
    }

    public static void sendKeys(WebElement webElement, String text) {
        logger.info("Setting textbox value - " + text);
        WebDriverWait webDriverWait = new WebDriverWait(DriverBase.getDriver(), WAIT_SMALL);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(text);
    }

    public static void click(WebElement webElement) {
        isElementDisplayed(webElement);
        waitForElementToBeClickable(webElement);
        webElement.click();
    }


    public static void waitForElementToBeClickable(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverBase.getDriver(), WAIT_SMALL);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));

    }

    public static String getText(WebElement webElement) {
        waitForVisibility(webElement);
        return webElement.getText();
    }


    public static String getValue(WebElement webElement) {
        waitForVisibility(webElement);
        return webElement.getAttribute("value");
    }

    public static void navigateToPage(String url) {
        logger.info("Navigating to: " + url);
        DriverBase.getDriver().get(url);
    }


    public static void scrollToCenterOfScreen(WebElement webElement) {
        waitForVisibility(webElement);
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) DriverBase.getDriver()).executeScript(scrollElementIntoMiddle, webElement);
    }

    public static void selectCustomComboBoxValue(WebDriver driver, WebElement combobox, String value) {
        logger.info("Selecting combobox value - " + value);
        WebElementHelper.click(combobox);

        By drpdwnItemLocator = By.xpath(String.format("//span/div/div/div[text()=\"%s\"]", value));
        new WebDriverWait(driver, WAIT_SMALL).until(d -> d.findElements(drpdwnItemLocator).size() != 0);
        //to handle animation of combobox items appearing
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);

        WebElement dropdownIntem = driver.findElement(drpdwnItemLocator);
        WebElementHelper.click(dropdownIntem);
        //to handle animation of combobox items appearing
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }

    private static Callable<Boolean> itemAppeared(WebDriver driver, By elementLocator) {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return driver.findElements(elementLocator).size() != 0;
            }
        };
    }

    public static void waitForLoadingIndicatorToDisappear() {
        //toDo - implement logic
    }
}

