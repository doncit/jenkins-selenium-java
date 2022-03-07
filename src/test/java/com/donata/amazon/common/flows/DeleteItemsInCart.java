package com.donata.amazon.common.flows;

import com.donata.pages.SelenideAmazonMainPage;

import static com.donata.pages.SelenideCommonPageActions.getItemsInCartCount;

public class DeleteItemsInCart  {

    public static void deleteItemsInCart() {

        Integer count = getItemsInCartCount();

        if (count > 0) {
            SelenideAmazonMainPage page = new SelenideAmazonMainPage();
            page.navigateToShoppingCart()
                    .deleteAllProductsInCart()
                    .verifyCartItemsCount(0);
        }
    }
}
