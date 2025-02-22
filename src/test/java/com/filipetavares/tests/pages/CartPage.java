package com.filipetavares.tests.pages;

import com.filipetavares.tests.core.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class CartPage extends BasePage {

    public static final By cartButton = AppiumBy.accessibilityId("Displays number of items in your cart");
    public static final By addToCart = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt");
    public static final By checkoutButton = AppiumBy.xpath("//android.widget.Button[@content-desc=\"Confirms products for checkout\"]");
    public static final By totalQuantity = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemsTV\"]");
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    private HomePage homePage = new HomePage(driver);

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public void addItemToCart(int item) {
        homePage.clickProductByListNumber(item);
        scrollDown();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(addToCart))).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(cartButton))).click();
    }

    public void addItemsAndCheckout() {
        addItemToCart(1);
        goToCart();
        verifyCheckoutTotalItems(1);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(checkoutButton))).click();
    }

    public void verifyCheckoutTotalItems(Integer expectedTotal){
        Assertions.assertEquals(expectedTotal.toString() + " Items",wait.until(ExpectedConditions.visibilityOfElementLocated(totalQuantity)).getText());
    }


}
