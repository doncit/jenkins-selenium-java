package com.donata.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;

public class SelenideProductPage extends SelenideCommonPageActions {

    public SelenideProductPage selectProductColor(Integer index) {
        $("#color_name_" + index.toString()).click();
        return page(SelenideProductPage.class);
    }

    public SelenideProductPage verifyColorIsSelected(Integer index) {
        SelenideElement element = $("#color_name_" + index.toString());
        String attribute = element.getAttribute("aria-checked");

        if (!Boolean.parseBoolean(attribute)) {
            attribute = element.find("span[aria-checked]")
                    .getAttribute("aria-checked");
        }
        assertTrue(Boolean.parseBoolean(attribute));

        return page(SelenideProductPage.class);
    }

    public SelenideProductPage addProductToCart() {
        $("#add-to-cart-button").click();
        return page(SelenideProductPage.class);
    }
}
