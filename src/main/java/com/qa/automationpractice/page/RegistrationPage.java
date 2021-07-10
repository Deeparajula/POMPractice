package com.qa.automationpractice.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automationpractice.util.ElementUtil;
import com.qa.automationpractice.util.JavaScriptUtil;

public class RegistrationPage {

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsutil;
	
	By signInLink = By.xpath(".//a[contains(text(),\"Sign in\")]");
	By emailCreate = By.id("email_create");
	By firstName = By.id("customer_firstname");
	By lastName = By.id("customer_lastname");
	By password = By.id("passwd");
	By address = By.id("address1");
	By city = By.id("city");
	By state = By.id("id_state");
	By postalCode = By.id("postcode");
	By mobile = By.id("phone_mobile");
	
	By createAccount = By.xpath(".//h3[contains(text(),\"Create an account\")]");
	By createAccountBtn = By.id("SubmitCreate");
	By createAccountPg = By.id("account-creation_form");
	
	By registerBtn = By.id("submitAccount");
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	public String getRegistrationPageTitle() {
		//elementUtil.waitForElementPresent("")
		return elementUtil.doGetPageTitle();
	}
	
	public void createNewRegistration(String email, String FN, String LN, String pswd, String adrs, String City, String State, String Postal, String Mobile) throws InterruptedException {
		
		elementUtil.doClick(signInLink);
		elementUtil.waitForElementPresent(createAccount);
		
		elementUtil.doSendKeys(emailCreate, email);
		elementUtil.doClick(createAccountBtn);
		
		elementUtil.waitForElementPresent(createAccountPg);
		
		elementUtil.doSendKeys(firstName, FN);
		
		elementUtil.doSendKeys(lastName, LN);
		
		elementUtil.doSendKeys(password, pswd);
		
		elementUtil.doSendKeys(address, adrs);
		
		elementUtil.doSendKeys(city, City);
		
		elementUtil.doSendKeys(state, State);
		
		elementUtil.doSendKeys(postalCode, Postal);
		
		elementUtil.doSendKeys(mobile, Mobile);
		
		elementUtil.doClick(registerBtn);
		
		
		
	}
}
