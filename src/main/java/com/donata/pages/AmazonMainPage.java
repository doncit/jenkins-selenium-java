package com.donata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonMainPage extends AbstractPage {

    private final By mainPage = By.id("a-page");
    private final By accountAndListsNav = By.id("nav-link-accountList-nav-line-1");
    private final By searchTextBox = By.id("twotabsearchtextbox");
    private final By submitSearchBtn = By.id("nav-search-submit-button");
    private final By sResultSortSelect = By.id("s-result-sort-select");
    private final By navToCartBtn = By.id("nav-cart");

    public AmazonMainPage(WebDriver driver) {
        super(driver);
    }

    public void openPage(String url) {
        openUrl(url);
    }

    public void verifyAmazonMainPage() {
        waitForElementVisible(10, mainPage);
    }

    public void clickAccountListNav() {
        clickBtn(accountAndListsNav);
    }

    public void enterSearchText(String text) {
        enterText(searchTextBox, text);
    }

    public void clickSubmitSearchBtn() {
        clickBtn(submitSearchBtn);
    }

    public void selectSortValue(String text) {
        waitForElementVisible(10, sResultSortSelect);
        selectDropdownValue(sResultSortSelect, text);
    }

    public void openProductInfo(Integer index) {
        String searchResultsItemSelector = getSearchResultsItemSelector(index);
        waitForElementVisible(10, searchResultsItemSelector);
        driver.findElement(By.cssSelector(searchResultsItemSelector)).findElement(By.tagName("a")).click();
    }

    public void navigateToCart() {
        waitForElementClickable(10, navToCartBtn);
        clickBtn(navToCartBtn);
    }

    private String getSearchResultsItemSelector(Integer index) {
        return "div[data-cel-widget='search_result_" + index.toString() + "']";
    }


}
