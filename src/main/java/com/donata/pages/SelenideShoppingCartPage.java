package com.donata.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.donata.utils.wait.Wait;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.*;

public class SelenideShoppingCartPage extends SelenideCommonPageActions {

    public SelenideShoppingCartPage deleteAllProductsInCart() {
        int initialSize = products().size();

        if (initialSize > 0) {
            SelenideElement product = products().get(0);
            product.find("[value='Delete']").click();
            product.shouldBe(hidden);
        }

        if (products().size() > 1) {
            Wait.until(10, () -> products().size() == (initialSize - 1));
            deleteAllProductsInCart();
        }
        return this;
    }

    public Double getSubtotalAmount() {
        return Double.parseDouble($("#sc-subtotal-amount-buybox").text().trim().substring(1));
    }

    public SelenideShoppingCartPage deleteLastProductFromCart() {
        products().get(products().size() - 1).find("[value='Delete']").click();
        return page(SelenideShoppingCartPage.class);
    }

    private ElementsCollection products() {
        return $$(".sc-list-item-content");
    }

    public void proceedToCheckout() {
        $("[data-feature-id='proceed-to-checkout-action']").click();
    }
}
