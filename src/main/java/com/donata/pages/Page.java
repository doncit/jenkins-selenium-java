package com.donata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Page {

    void openUrl(String url);

    void clickBtn(By selector);

    void clickBtn(String selector);

    void enterText(By selector, String text);

    void waitForElementVisible(int seconds, By selector);

    void waitForElementVisible(int seconds, String selector);

    void waitForElementNotVisible(int seconds, WebElement element);

    void waitForElementClickable(int seconds, By selector);

    void selectDropdownValue(By selector, String text);

    String getElementText(By selector);

    List<WebElement> getListOfWebElements(By selector);
}
