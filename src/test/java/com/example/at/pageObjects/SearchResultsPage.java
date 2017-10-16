package com.example.at.pageObjects;

import com.example.at.config.webdriver.DriverBase;
import com.example.at.support.web.WebElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.at.config.Constants.WAIT_NORMAL;

public class SearchResultsPage {

    Logger logger = LoggerFactory.getLogger(SearchResultsPage.class);
    private WebDriver driver;

    @FindBy(css = "[class='page-heading  product-listing']")
    private WebElement lblPageHeading;

    @FindBy(css = ".ajax_block_product")
    private List<WebElement> searchResultItems;

    private By addToCartSelecctor = By.cssSelector(".ajax_add_to_cart_button");

    public SearchResultsPage() {
        this.driver = DriverBase.getDriver();
        PageFactory.initElements(driver, this);

    }


    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(lblPageHeading, WAIT_NORMAL);
    }

    public String getHeaderText() {
        return WebElementHelper.getText(lblPageHeading);
    }

    public int getSearchResultWidgetsCount() {
        return searchResultItems.size();
    }

    private List<WebElement> getResultElementsByProductName(String searchName) {
        return searchResultItems.stream()
                .filter(s -> searchName.equals(s.findElement(By.cssSelector(".product-name")).getText()))
                .collect(Collectors.toList());
    }

    public void clickOnAddToCartForProduct(String productName) {
        WebElement foundProductCard = getResultElementsByProductName(productName).get(0);
        WebElement productImage = foundProductCard.findElement(By.cssSelector("img"));

        //Button add to cart displayed only when hover over product image
        WebElementHelper.hoverOverWebelement(productImage);

        WebElement addToCartButton = foundProductCard.findElement(addToCartSelecctor);
        WebElementHelper.click(addToCartButton);

    }


}