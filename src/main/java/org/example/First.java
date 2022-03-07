package org.example;

import com.donata.credentials.Credentials;
import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class First {

    Playwright playwright;
    BrowserType browserType;
    Browser chrome;
    Page page;
    final String URL = "https://www.amazon.com/";
    private static Credentials credentials;


    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browserType = playwright.chromium();
        chrome = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        BrowserContext context = chrome.newContext();
        page = context.newPage();
        page.navigate(URL);
        credentials = Credentials.get();
    }

    @Test
    public void test() throws InterruptedException {
        page.click("#nav-link-accountList-nav-line-1");
        page.fill("#ap_email",credentials.getUsername());
        String username = page.inputValue("#ap_email");
        Assert.assertEquals(username,credentials.getUsername());
        page.click("#continue");
        page.fill("#ap_password",credentials.getPassword());
        page.click("#signInSubmit");
        page.waitForSelector("#twotabsearchtextbox");
        boolean visible = page.isVisible("#twotabsearchtextbox");
        Assert.assertTrue(visible);
    }
}