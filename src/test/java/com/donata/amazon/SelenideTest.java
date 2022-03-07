package com.donata.amazon;

import com.codeborne.selenide.WebDriverRunner;
import com.donata.driver.Driver;
import com.donata.pages.SelenideAmazonMainPage;
import com.donata.pages.SelenideCommonPageActions;
import com.donata.pages.SelenideShoppingCartPage;
import com.donata.utils.fileutils.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.donata.amazon.common.flows.DeleteItemsInCart.deleteItemsInCart;
import static com.donata.amazon.common.flows.Login.login;
import static org.testng.Assert.assertTrue;

public class SelenideTest {

    private WebDriver driver;
    private static TestData testData;


    @BeforeClass
    public void setup() {
        driver = Driver.get(Driver.Type.LOCAL, Driver.Browser.CHROME);
        WebDriverRunner.setWebDriver(driver);
        testData = TestData.get();
    }

    @Test
    public void addProductsToCart(){
        login();
        driver.manage().window().maximize();
        deleteItemsInCart();

        new SelenideCommonPageActions()
                .enterSearchText(testData.getProduct())
                .verifySearchText(testData.getProduct())
                .clickSubmitSearchBtn()
                .selectSortValue("Avg. Customer Review")
                .openProductInfo(3)
                .selectProductColor(3)
                .verifyColorIsSelected(3)
                .addProductToCart()
                .verifyCartItemsCount(1)

                .enterSearchText(testData.getProduct())
                .verifySearchText(testData.getProduct())
                .clickSubmitSearchBtn()
                .selectSortValue("Avg. Customer Review")
                .openProductInfo(4)
                .addProductToCart()
                .verifyCartItemsCount(2);

        SelenideShoppingCartPage cartPage = new SelenideAmazonMainPage().navigateToShoppingCart();
        Double subtotalAmount1 = cartPage.getSubtotalAmount();
        cartPage.deleteLastProductFromCart()
                .verifyCartItemsCount(1);
        Double subtotalAmount2 = cartPage.getSubtotalAmount();
        assertTrue(subtotalAmount2 < subtotalAmount1);
        cartPage.proceedToCheckout();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
