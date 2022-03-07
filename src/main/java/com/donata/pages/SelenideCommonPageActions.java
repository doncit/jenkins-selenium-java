package com.donata.pages;

import com.donata.utils.wait.Wait;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SelenideCommonPageActions {

    public static Integer getItemsInCartCount(){
        return Integer.parseInt($("#nav-cart-count").text());
    }

    public SelenideCommonPageActions verifyCartItemsCount(Integer expectedCount) {
        Wait.until(10, () -> getItemsInCartCount().equals(expectedCount));
        return page(SelenideCommonPageActions.class);
    }

    public SelenideAmazonMainPage enterSearchText(String text) {
        $("#twotabsearchtextbox").setValue(text);
        return page(SelenideAmazonMainPage.class);
    }

    public SelenideAmazonMainPage verifySearchText(String text) {
        $("#twotabsearchtextbox").shouldHave(attribute("value",text));
        return page(SelenideAmazonMainPage.class);
    }

    public SelenideAmazonMainPage clickSubmitSearchBtn() {
        $("#nav-search-submit-button").click();
        return page(SelenideAmazonMainPage.class);
    }
}
