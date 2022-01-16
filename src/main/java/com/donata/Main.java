package com.donata;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = null;

        try {
            driver = new ChromeDriver();
            driver.get("https://www.google.com/");
        } finally {
            if (driver != null) {
                driver.close();
            }
        }

    }
}
