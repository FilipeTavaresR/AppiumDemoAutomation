package com.filipetavares.tests.tests;

import com.filipetavares.tests.core.BaseTest;

import com.filipetavares.tests.dto.CheckoutDTO;
import com.filipetavares.tests.pages.CartPage;
import com.filipetavares.tests.pages.HomePage;
import com.filipetavares.tests.pages.LoginPage;
import com.filipetavares.tests.pages.checkout.FormCheckoutPage;
import com.filipetavares.tests.pages.checkout.PaymentCheckoutPage;
import com.filipetavares.tests.pages.checkout.ReviewCheckoutPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest {

    private FormCheckoutPage formCheckoutPage;
    private PaymentCheckoutPage paymentCheckoutPage;
    private ReviewCheckoutPage reviewCheckoutPage;
    private LoginPage loginPage;
    private CartPage cartPage;
    private HomePage homePage;

    @BeforeEach
    public void setUpPages() {
        cartPage = new CartPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        formCheckoutPage = new FormCheckoutPage(driver);
        paymentCheckoutPage = new PaymentCheckoutPage(driver);
        reviewCheckoutPage = new ReviewCheckoutPage(driver);
    }

    @Test
    public void testCheckoutSuccess() {
        loginPage.login("standard_user", "secret_sauce");
        cartPage.addItemsAndCheckout();
        CheckoutDTO checkoutDTO = new CheckoutDTO(1,"Rebecca Summer","123 Main St","12345",
                "Gaspar","Brasil","Rebecca Summer",
                "4111111111111111","1222","123");
        formCheckoutPage.fillCheckoutForm(checkoutDTO);
        paymentCheckoutPage.fillPaymentCheckoutForm(checkoutDTO);
        reviewCheckoutPage.verifyCheckoutPage(checkoutDTO);
        reviewCheckoutPage.placeOrder();
        homePage.homePageIsVisible();
    }
}
