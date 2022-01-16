package com.donata.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    public enum Type {
        REMOTE,
        LOCAL
    }

    public static WebDriver get(Type type) {
        if (type == Type.REMOTE) {
            try {
                return new RemoteWebDriver(new URL("http://selenium-hub:4444"), new ChromeOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
