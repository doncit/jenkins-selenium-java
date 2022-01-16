package com.donata.amazon;

import com.donata.driver.Driver;
import com.donata.pages.*;
import com.donata.utils.fileutils.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class AddProductToBasketTest {

    private TestData testData;
    private WebDriver driver;
    private AmazonMainPage amazonMainPage;
    private AmazonLoginPage amazonLoginPage;
    private ProductPage productPage;
    private CommonPageActions commonPageActions;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() {
        testData = TestData.get();

        driver = Driver.get(Driver.Type.REMOTE);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        amazonMainPage = new AmazonMainPage(driver);
        amazonLoginPage = new AmazonLoginPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        commonPageActions = new CommonPageActions(driver);
        amazonMainPage.openPage(testData.getUrl());
        driver.manage().window().maximize();
        amazonMainPage.verifyAmazonMainPage();
    }

    @Test
    public void login() {
        amazonMainPage.clickAccountListNav();
        amazonLoginPage.enterEmailOrMPhone("xcverasdfasdfkfkf");
        amazonLoginPage.clickContinue();
        amazonLoginPage.enterPassword("sdfaishdfioasdfhoaisd");
        amazonLoginPage.clickSignIn();
        amazonMainPage.verifyAmazonMainPage();
    }

    @Test(dependsOnMethods = "login")
    public void cleanCart() {
        Integer count = commonPageActions.getItemsInCartCount();
        if (count > 0) {
            amazonMainPage.navigateToCart();
            productPage.deleteAllProductsInCart();
            commonPageActions.verifyCartItemsCount(0);
        }
    }

    @Test(dependsOnMethods = "cleanCart")
    public void test() {
        amazonMainPage.enterSearchText(testData.getProduct());
        amazonMainPage.clickSubmitSearchBtn();
        amazonMainPage.selectSortValue("Avg. Customer Review");
        amazonMainPage.openProductInfo(3);
        productPage.selectProductColor(3);
        productPage.verifyColorIsSelected(3);
        productPage.addProductToCart();
        commonPageActions.verifyCartItemsCount(1);

        amazonMainPage.enterSearchText(testData.getProduct());
        amazonMainPage.clickSubmitSearchBtn();
        amazonMainPage.selectSortValue("Avg. Customer Review");
        amazonMainPage.openProductInfo(4);
        productPage.addProductToCart();
        commonPageActions.verifyCartItemsCount(2);

        amazonMainPage.navigateToCart();
        Double subtotalAMount1 = productPage.getSubtotalAmount();
        productPage.deleteLastProductFromCart();
        commonPageActions.verifyCartItemsCount(1);
        Double subtotalAMount2 = productPage.getSubtotalAmount();
        Assert.assertTrue(subtotalAMount2 < subtotalAMount1);
        productPage.proceedToCheckout();
        checkoutPage.verifyCheckoutPageIsDisplayed();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
