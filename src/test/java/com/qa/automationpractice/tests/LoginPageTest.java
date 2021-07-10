package com.qa.automationpractice.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.automationpractice.base.BasePage;
import com.qa.automationpractice.page.LoginPage;
import com.qa.automationpractice.util.AppConstants;
import com.qa.automationpractice.util.Credentials;

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	@BeforeTest(alwaysRun=true)
	public void setUp() {
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1, groups="sanity")
	public void verifyPageTitleTest() {
		String pageTitle = loginPage.getPageTitle();
		System.out.println("Page title is :" +pageTitle);
		Assert.assertEquals(pageTitle, AppConstants.PAGE_TITLE);
	}
	
	@Test(priority=3) 
	public void verifySignInLinkTest() {
	 Assert.assertTrue(loginPage.checkSignInLink()); 
	}
	 
	@Test(priority=4)
	public void verifyLoginPageTitleTest() {
		String loginPageTitle = loginPage.getLoginPageTitle();
		System.out.println("Login Page title is :" +loginPageTitle);
		Assert.assertEquals(loginPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority=6, groups="sanity")
	public void LoginTest() {
		loginPage.doLogin(userCred);
		String myAccountPageTitle = loginPage.getMyAccountPageTitle();
		System.out.println("Account Page title is :" +myAccountPageTitle);
		Assert.assertEquals(myAccountPageTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = {{"test1@gmail.com", "test1"}, 
							{" ", "test12"}, 
							{"test3@gmail.com", " "}, 
							{"test1", "test1"},
							{" ", " "}
						};
		return data;
	}
	
	@Test(priority=5, dataProvider = "getLoginInvalidData", enabled=false)
	public void login_InvalidCredentialTest(String username, String pwd) {
		userCred.setAppUsername(username); // encapsulation concept
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		
		Assert.assertTrue(loginPage.checkLoginErrorMsg());
	}
	
	@AfterTest(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}

}
