package com.donata.amazon;

import com.donata.pages.*;
import com.donata.utils.fileutils.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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

    @Test(priority = 1)
    public void login() {
        amazonMainPage.clickAccountListNav();
        amazonLoginPage.enterEmailOrMPhone("asdfasdfasvxcvxcvx");
        amazonLoginPage.clickContinue();
        amazonLoginPage.enterPassword("asdfasdfasdfasdfsad");
        amazonLoginPage.clickSignIn();
        amazonMainPage.verifyAmazonMainPage();
    }

    @Test(priority = 2)
    public void cleanCart() {
        Integer count = commonPageActions.getItemsInCartCount();
        if (count > 0) {
            amazonMainPage.navigateToCart();
            productPage.deleteAllProductsInCart();
            commonPageActions.verifyCartItemsCount(0);
        }
    }

    @Test(priority = 3)
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
            driver.close();
        }
    }
}
