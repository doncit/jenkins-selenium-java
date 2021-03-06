package com.donata.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    public enum Type {
        REMOTE,
        LOCAL
    }

    public enum Browser {
        CHROME,
        FIREFOX
    }

    public static WebDriver get(Type type, Browser browser) {
        if (type == Type.REMOTE) {
            try {
                return new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        if (browser == Browser.CHROME) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
