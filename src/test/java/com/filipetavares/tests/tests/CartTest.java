package com.filipetavares.tests.tests;

import com.filipetavares.tests.core.BaseTest;


import com.filipetavares.tests.pages.CartPage;
import com.filipetavares.tests.pages.HomePage;
import com.filipetavares.tests.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private CartPage cartPage;
    private HomePage homePage;

    @BeforeEach
    public void setUpPages() {
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void testAddToCart() {
        loginPage.login("standard_user", "secret_sauce");
        cartPage.addItemToCart(3);
        homePage.goToCatalog();
        cartPage.addItemToCart(1);
        cartPage.goToCart();
        cartPage.verifyCheckoutTotalItems(2);
    }
}
