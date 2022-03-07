package com.donata.pages;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SelenideAmazonMainPage extends SelenideCommonPageActions {

    public SelenideAmazonMainPage verifyAmazonMainPage() {
        $("#twotabsearchtextbox").shouldBe(visible, Duration.ofSeconds(5));
        return page(SelenideAmazonMainPage.class);
    }

    public SelenideShoppingCartPage navigateToShoppingCart() {
        $("#nav-cart").click();
        return page(SelenideShoppingCartPage.class);
    }

    public SelenideAmazonMainPage selectSortValue(String text) {
        $("#s-result-sort-select").selectOptionContainingText(text);
        return page(SelenideAmazonMainPage.class);
    }

    public SelenideProductPage openProductInfo(Integer index) {
        $("div[data-cel-widget='search_result_" + index.toString() + "']").find("a").click();
        return page(SelenideProductPage.class);
    }
}
