package com.filipetavares.tests.pages.checkout;

import com.filipetavares.tests.core.BasePage;
import com.filipetavares.tests.dto.CheckoutDTO;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.filipetavares.tests.core.Utils.expirationDateConverter;


public class ReviewCheckoutPage extends BasePage {

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public static final By paymentTitle = AppiumBy.id("com.saucelabs.mydemoapp.android:id/enterShippingAddressTV");
    public static final By totalItems = AppiumBy.id("com.saucelabs.mydemoapp.android:id/itemNumberTV");
    public static final By deliverAddressTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"Deliver Address\"]");
    public static final By fullNameTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/fullNameTV");
    public static final By addressNameTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/addressTV");
    public static final By cityNameTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cityTV");
    public static final By countryNameTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/countryTV");
    public static final By paymentMethodTitle = AppiumBy.xpath("//android.widget.TextView[@text=\"Payment Method\"]");
    public static final By cardHolderTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cardHolderTV");
    public static final By cardNumberTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cardNumberTV");
    public static final By expirationDateTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/expirationDateTV");
    public static final By billingAddressTextView = AppiumBy.id("com.saucelabs.mydemoapp.android:id/billingAddressTV");
    public static final By placeOrderButton = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
    public static final By checkoutCompletTitle = AppiumBy.id("com.saucelabs.mydemoapp.android:id/completeTV");
    public static final By continueShoppingButton = AppiumBy.id("com.saucelabs.mydemoapp.android:id/shoopingBt");

    public ReviewCheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public void verifyCheckoutPage(CheckoutDTO checkoutDTO){
        Assertions.assertEquals("Review your order",wait.until(ExpectedConditions.visibilityOfElementLocated(paymentTitle)).getText());
        Assertions.assertEquals(checkoutDTO.totalItems().toString() + " Items",wait.until(ExpectedConditions.visibilityOfElementLocated(totalItems)).getText());
        scrollByText("Deliver Address",0);
        Assertions.assertEquals("Deliver Address",wait.until(ExpectedConditions.visibilityOfElementLocated(deliverAddressTitle)).getText());
        Assertions.assertEquals(checkoutDTO.fullName(),wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameTextView)).getText());
        Assertions.assertEquals(checkoutDTO.address(),wait.until(ExpectedConditions.visibilityOfElementLocated(addressNameTextView)).getText());
        Assertions.assertEquals(checkoutDTO.city()+",",wait.until(ExpectedConditions.visibilityOfElementLocated(cityNameTextView)).getText().trim());
        Assertions.assertEquals(checkoutDTO.country()+", "+checkoutDTO.zipCode(),wait.until(ExpectedConditions.visibilityOfElementLocated(countryNameTextView)).getText().trim());
        Assertions.assertEquals("Payment Method",wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodTitle)).getText());
        Assertions.assertEquals(checkoutDTO.cardHolder(),wait.until(ExpectedConditions.visibilityOfElementLocated(cardHolderTextView)).getText());
        Assertions.assertEquals(checkoutDTO.cardNumber(),wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberTextView)).getText().replace(" ",""));
        Assertions.assertEquals("Exp: "+expirationDateConverter(checkoutDTO.expirationDate()),wait.until(ExpectedConditions.visibilityOfElementLocated(expirationDateTextView)).getText());
        scrollByText("Billing address is the same as shipping address",0);
        Assertions.assertEquals("Billing address is the same as shipping address",wait.until(ExpectedConditions.visibilityOfElementLocated(billingAddressTextView)).getText());
    }

    public void placeOrder(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).click();
        Assertions.assertEquals("Checkout Complete",wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutCompletTitle)).getText());
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton)).click();
    }

}
