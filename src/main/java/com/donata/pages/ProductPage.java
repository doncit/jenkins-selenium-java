package com.donata.pages;

import com.donata.utils.wait.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ProductPage extends AbstractPage {

    private final By addToCartButton = By.id("add-to-cart-button");
    private final By subtotalAmountField = By.id("sc-subtotal-amount-buybox");
    private final By productInCart = By.cssSelector(".sc-list-item-content");
    private final By deleteBtn = By.cssSelector("[value='Delete']");
    private final By proceedToCheckoutBtn = By.cssSelector("[data-feature-id='proceed-to-checkout-action']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void selectProductColor(Integer index) {
        waitForElementVisible(10, getProductColorSelector(index));
        clickBtn(getProductColorSelector(index));
    }

    public void verifyColorIsSelected(Integer index) {
        assertTrue(getProductSelectedColorIndicator(index));
    }

    public void addProductToCart() {
        waitForElementVisible(10, addToCartButton);
        clickBtn(addToCartButton);
    }

    public Double getSubtotalAmount() {
        waitForElementVisible(10, subtotalAmountField);
        String subtotalAmount = getElementText(subtotalAmountField).trim().substring(1);
        return Double.parseDouble(subtotalAmount);
    }

    public void deleteLastProductFromCart() {
        List<WebElement> products = getListOfWebElements(productInCart);
        WebElement lastProduct = products.get(products.size() - 1);
        WebElement lasProductDeleteBtn = lastProduct.findElement(deleteBtn);
        lasProductDeleteBtn.click();
    }

    public void deleteAllProductsInCart() {
        List<WebElement> products = getListOfWebElements(productInCart);
        int initialSize = products.size();

        if (initialSize > 0) {
            WebElement product = products.get(0);
            product.findElement(deleteBtn).click();
            waitForElementNotVisible(10, product);
        }

        if (products.size() > 1) {
            Wait.Until(10, () -> getListOfWebElements(productInCart).size() == (initialSize - 1));
            deleteAllProductsInCart();
        }
    }

    public void proceedToCheckout() {
        clickBtn(proceedToCheckoutBtn);
    }

    private String getProductColorSelector(Integer index) {
        return "#color_name_" + index.toString();
    }

    private Boolean getProductSelectedColorIndicator(Integer index) {
        String val = driver.findElement(By.cssSelector(getProductColorSelector(index))).
                findElement(By.cssSelector("span[aria-checked]")).
                getAttribute("aria-checked");
        return Boolean.parseBoolean(val);
    }
}
