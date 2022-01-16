package com.donata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class CommonPageActions extends AbstractPage {

    private final By cartCount = By.id("nav-cart-count");

    public CommonPageActions(WebDriver driver) {
        super(driver);
    }

    public void verifyCartItemsCount(Integer expectedCount) {
        assertEquals(getItemsInCartCount(), expectedCount);
    }

    public Integer getItemsInCartCount() {
        waitForElementVisible(10, cartCount);
        return Integer.parseInt(getElementText(cartCount));
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}
