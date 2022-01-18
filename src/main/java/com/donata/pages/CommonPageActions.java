package com.donata.pages;

import com.donata.utils.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPageActions extends AbstractPage {

    private final By cartCount = By.id("nav-cart-count");

    public CommonPageActions(WebDriver driver) {
        super(driver);
    }

    public void verifyCartItemsCount(Integer expectedCount) {
        Wait.until(10, () -> getItemsInCartCount().equals(expectedCount));
    }

    public Integer getItemsInCartCount() {
        waitForElementVisible(10, cartCount);
        return Integer.parseInt(getElementText(cartCount));
    }

    public void navigateBack() {
        driver.navigate().back();
    }
}
