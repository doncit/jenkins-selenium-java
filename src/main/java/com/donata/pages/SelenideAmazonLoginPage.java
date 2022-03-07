package com.donata.pages;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SelenideAmazonLoginPage extends SelenideCommonPageActions {

    public SelenideAmazonLoginPage enterEmailOrMPhone(String emailOrMPhone) {
        $("#ap_email").setValue(emailOrMPhone);
        return page(SelenideAmazonLoginPage.class);
    }

    public SelenideAmazonLoginPage verifyEmailOrMPhone(String emailOrMPhone) {
        $("#ap_email").shouldHave(attribute("value", emailOrMPhone));
        return page(SelenideAmazonLoginPage.class);
    }

    public SelenideAmazonLoginPage clickContinue() {
        $("#continue").click();
        return page(SelenideAmazonLoginPage.class);
    }

    public SelenideAmazonLoginPage enterPassword(String password) {
        $("#ap_password").setValue(password);
        return page(SelenideAmazonLoginPage.class);
    }

    public SelenideAmazonLoginPage verifyPassword(String password) {
        $("#ap_password").shouldHave(attribute("value", password));
        return page(SelenideAmazonLoginPage.class);
    }

    public SelenideAmazonMainPage clickSignIn() {
        $("#signInSubmit").click();
        return page(SelenideAmazonMainPage.class);
    }

    public SelenideAmazonLoginPage clickAccountListNav() {
        $("#nav-link-accountList-nav-line-1").click();
        return page(SelenideAmazonLoginPage.class);
    }

}
