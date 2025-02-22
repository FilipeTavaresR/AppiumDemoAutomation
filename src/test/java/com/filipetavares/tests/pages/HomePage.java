package com.filipetavares.tests.pages;

import com.filipetavares.tests.core.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage extends BasePage {
	private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	public static final By menuButton = AppiumBy.accessibilityId("View menu");
	public static final By menuLoginButton = AppiumBy.accessibilityId("Login Menu Item");
	public static final By menuLogoutButton = AppiumBy.accessibilityId("Logout Menu Item");
	public static final By menuCatalogButton = By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemTV\" and @text=\"Catalog\"]");
	public static final By catalogTitle = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"title\"]");

	public HomePage(AppiumDriver driver) {
		super(driver);
	}

	public void homePageIsVisible(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTitle));
	}
	public void goToCatalog() {
		wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(menuCatalogButton)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTitle));
	}

	public void goToLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(menuCatalogButton));
		scrollDown();
		wait.until(ExpectedConditions.elementToBeClickable(menuLoginButton)).click();
	}

	public void goToLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
		scrollDown();
		wait.until(ExpectedConditions.elementToBeClickable(menuLogoutButton)).click();
	}

	public void clickProductByListNumber(int number){
		wait.until(ExpectedConditions.visibilityOfElementLocated(catalogTitle));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"Product Image\"])["+number+"]"))))
				.click();
	}
}
