package com.filipetavares.tests.pages;
import com.filipetavares.tests.core.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

	public static final By usernameField = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
	public static final By passwordField = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
	public static final By loginButton = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn");
	public static final By loginMessage = AppiumBy.id("com.saucelabs.mydemoapp.android:id/selectTextTV");
	public static final By logoutButton = AppiumBy.id("android:id/button1");
	public static final By logoutMessage = AppiumBy.androidUIAutomator("new UiSelector().text(\"Are you sure you want to logout\")");


	private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private HomePage homePage = new HomePage(driver);

	public LoginPage(AppiumDriver driver) {
		super(driver);
	}

	public void login(String username, String password) {
		// Esperando até que o menuButton esteja clicável e clicando nele
		homePage.goToLogin();
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
		driver.findElement(passwordField).sendKeys(password);
		driver.findElement(loginButton).click();
	}

	public void logout() {
		// Esperando até que o menuButton esteja clicável e clicando nele
		homePage.goToLogout();
		wait.until(ExpectedConditions.textToBe(logoutMessage,"Are you sure you want to logout"));
		driver.findElement(logoutButton).click();
		wait.until(ExpectedConditions.textToBe(loginMessage,"Select a username and password from the list below, " +
				"or click on the username to automatically populate the username and password."));
	}

}
