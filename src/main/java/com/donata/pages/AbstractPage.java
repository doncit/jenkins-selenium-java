package com.donata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractPage implements Page {

    protected final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void openUrl(String url) {
        driver.get(url);
    }

    @Override
    public void clickBtn(By selector) {
        driver.findElement(selector).click();
    }

    @Override
    public void clickBtn(String selector) {
        driver.findElement(By.cssSelector(selector)).click();
    }

    @Override
    public void enterText(By selector, String text) {
        driver.findElement(selector).sendKeys(text);
    }

    @Override
    public void waitForElementVisible(int seconds, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(selector)));
    }

    @Override
    public void waitForElementVisible(int seconds, String selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(selector))));
    }

    @Override
    public void waitForElementNotVisible(int seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    @Override
    public void waitForElementClickable(int seconds, By selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(selector)));
    }

    @Override
    public void selectDropdownValue(By selector, String text) {
        Select dropdown = new Select(driver.findElement(selector));
        dropdown.selectByVisibleText(text);
    }

    @Override
    public String getElementText(By selector) {
        return driver.findElement(selector).getText();
    }

    @Override
    public List<WebElement> getListOfWebElements(By selector) {
        return driver.findElements(selector);
    }
}
