package com.donata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends AbstractPage {

    private final By checkoutPage = By.id("checkoutDisplayPage");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCheckoutPageIsDisplayed() {
        waitForElementVisible(10, checkoutPage);
    }
}
