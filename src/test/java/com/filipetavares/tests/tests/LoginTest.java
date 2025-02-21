package com.filipetavares.tests.tests;


import com.filipetavares.tests.core.BaseTest;
import com.filipetavares.tests.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoginTest extends BaseTest {

	private LoginPage loginPage;

	@BeforeEach
	public void setUpPages() {
		loginPage = new LoginPage(driver);
	}

	@Test
	public void testLoginLogout() {
		loginPage.login("standard_user", "secret_sauce");
		loginPage.logout();
	}

}
