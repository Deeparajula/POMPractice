package com.qa.automationpractice.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.automationpractice.base.BasePage;
import com.qa.automationpractice.page.HomePage;
import com.qa.automationpractice.page.LoginPage;
import com.qa.automationpractice.util.AppConstants;
import com.qa.automationpractice.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeTest(alwaysRun=true)
	@Parameters(value= {"browser"})
	public void setUp(String browser) {
		String browserName = null;
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		// to handle browser from Config file and TestNG xml
				if(browser.equals(null)) {
					browserName = prop.getProperty("browser");
				} else {
					browserName = browser;
				}
		
		//String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
	}
	
	@Test(priority=1)
	public void verifyAccountPageHeaderTest() throws InterruptedException {
		Thread.sleep(5000);
		String pageHeader = homePage.getAccountPageHeader();
		System.out.println("Page header is : " +pageHeader);
		//Thread.sleep(5000);
		Assert.assertEquals(pageHeader, AppConstants.PAGE_HEADER);
	}
	
	@Test(priority=2,groups = "sanity")
	public void verifyHomePageTitleTest() throws InterruptedException {
		Thread.sleep(5000);
		String homePageTitle = homePage.getHomePageTitle();
		System.out.println("Home Page title is :" +homePageTitle);
		Assert.assertEquals(homePageTitle, AppConstants.PAGE_TITLE);
	}
	
	@Test(priority=3, groups = "sanity")
	public void verifyLoggedInUserName() {
		String accountUsername = homePage.getLoggedInUserAccountName();
		System.out.println("Logged in user name is :" +accountUsername);
		Assert.assertEquals(accountUsername, prop.getProperty("accountname"));
	}
	
	@AfterTest(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}

}
