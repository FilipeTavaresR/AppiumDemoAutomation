package com.filipetavares.tests.core;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public abstract class BasePage {
    protected AppiumDriver driver; // Agora, o driver é uma variável de instância

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void scrollDown() {
        try {
            String scrollCommand = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollForward(50);";
            driver.findElement(new AppiumBy.ByAndroidUIAutomator(scrollCommand));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollByText(String text, int index) {
        try {
            driver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))." +
                    "scrollIntoView(new UiSelector().text(\"" + text + "\").instance(" + index + "));"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
