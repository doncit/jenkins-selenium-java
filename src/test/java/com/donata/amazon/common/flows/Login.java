package com.donata.amazon.common.flows;

import com.donata.credentials.Credentials;
import com.donata.pages.SelenideAmazonLoginPage;
import com.donata.pages.SelenideAmazonMainPage;
import com.donata.utils.fileutils.TestData;

import static com.codeborne.selenide.Selenide.open;

public class Login {

    public static SelenideAmazonMainPage login() {
        var testData = TestData.get();
        var credentials = Credentials.get();

        return open(testData.getUrl(), SelenideAmazonLoginPage.class)
                .clickAccountListNav()
                .enterEmailOrMPhone(credentials.getUsername())
                .verifyEmailOrMPhone(credentials.getUsername())
                .clickContinue()
                .enterPassword(credentials.getPassword())
                .verifyPassword(credentials.getPassword())
                .clickSignIn()
                .verifyAmazonMainPage();
    }
}
