package com.filipetavares.tests.pages.checkout;

import com.filipetavares.tests.core.BasePage;
import com.filipetavares.tests.dto.CheckoutDTO;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PaymentCheckoutPage extends BasePage {

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public static final By paymentTitle = AppiumBy.id("com.saucelabs.mydemoapp.android:id/enterPaymentTitleTV");
    public static final By fullNameEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    public static final By cardNumberEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cardNumberET");
    public static final By expirationDateEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/expirationDateET");
    public static final By securityCodeEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/securityCodeET");
    public static final By billingAddressCheckBox = AppiumBy.id("com.saucelabs.mydemoapp.android:id/billingAddressCB");
    public static final By reviewOrderButton = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");

    public PaymentCheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillPaymentCheckoutForm(CheckoutDTO checkoutDTO){
        wait.until(ExpectedConditions.visibilityOfElementLocated(paymentTitle));
        driver.findElement(fullNameEditText).sendKeys(checkoutDTO.cardHolder());
        driver.findElement(cardNumberEditText).sendKeys(checkoutDTO.cardNumber());
        scrollDown();
        wait.until(ExpectedConditions.visibilityOfElementLocated(expirationDateEditText)).sendKeys(checkoutDTO.expirationDate());
        driver.findElement(securityCodeEditText).sendKeys(checkoutDTO.securityCode());

        if(driver.findElement(billingAddressCheckBox).isSelected()){
            driver.findElement(billingAddressCheckBox).click();
        }
        driver.findElement(reviewOrderButton).click();
    }

}
