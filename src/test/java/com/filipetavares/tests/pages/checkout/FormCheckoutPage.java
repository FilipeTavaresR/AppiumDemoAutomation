package com.filipetavares.tests.pages.checkout;

import com.filipetavares.tests.core.BasePage;
import com.filipetavares.tests.dto.CheckoutDTO;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class FormCheckoutPage extends BasePage {

    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    public static final By checkoutTitle = AppiumBy.id("com.saucelabs.mydemoapp.android:id/checkoutTitleTV");
    public static final By fullNameEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/fullNameET");
    public static final By address1EditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/address1ET");
    public static final By cityEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cityET");
    public static final By zipCodeEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/zipET");
    public static final By countryEditText = AppiumBy.id("com.saucelabs.mydemoapp.android:id/countryET");
    public static final By paymentButton = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");

    public FormCheckoutPage(AppiumDriver driver) {
        super(driver);
    }

    public void fillCheckoutForm(CheckoutDTO checkoutDTO){
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutTitle));
        driver.findElement(fullNameEditText).sendKeys(checkoutDTO.fullName());
        driver.findElement(address1EditText).sendKeys(checkoutDTO.address());
        driver.findElement(cityEditText).sendKeys(checkoutDTO.city());
        scrollDown();
        driver.findElement(zipCodeEditText).sendKeys(checkoutDTO.zipCode());
        driver.findElement(countryEditText).sendKeys(checkoutDTO.country());
        driver.findElement(paymentButton).click();
    }

}
