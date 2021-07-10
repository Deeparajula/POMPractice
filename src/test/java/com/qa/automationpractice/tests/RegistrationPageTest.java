package com.qa.automationpractice.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.automationpractice.base.BasePage;
import com.qa.automationpractice.page.LoginPage;
import com.qa.automationpractice.page.RegistrationPage;
import com.qa.automationpractice.util.AppConstants;
import com.qa.automationpractice.util.Credentials;
import com.qa.automationpractice.util.ExcelUtil;

public class RegistrationPageTest {

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	RegistrationPage registrationPage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp() {
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		
		registrationPage = new RegistrationPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getRegistrationTestData() {
		Object[][] data = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return data;
	}
	
	@Test(dataProvider = "getRegistrationTestData")
	public void registrationTest(String emailCreate, String firstName, String lastName, String password, String address, String city, String state, String postalCode, String mobile ) throws InterruptedException {
		registrationPage.createNewRegistration(emailCreate, firstName, lastName, password, address, city, state, postalCode, mobile);
	}
	
}
