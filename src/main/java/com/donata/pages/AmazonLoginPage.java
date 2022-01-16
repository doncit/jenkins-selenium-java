package com.donata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonLoginPage extends AbstractPage {

    private final By emailOrMPhoneInput = By.id("ap_email");
    private final By continueBtn = By.id("continue");
    private final By passwordInput = By.id("ap_password");
    private final By signInBtn = By.id("signInSubmit");

    public AmazonLoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmailOrMPhone(String emailOrMPhone) {
        enterText(emailOrMPhoneInput, emailOrMPhone);
    }

    public void clickContinue() {
        clickBtn(continueBtn);
    }

    public void enterPassword(String password) {
        enterText(passwordInput, password);
    }

    public void clickSignIn() {
        clickBtn(signInBtn);
    }
}
