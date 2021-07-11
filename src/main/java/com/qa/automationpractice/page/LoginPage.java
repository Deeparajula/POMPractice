package com.qa.automationpractice.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automationpractice.base.BasePage;
import com.qa.automationpractice.util.AppConstants;
import com.qa.automationpractice.util.Credentials;
import com.qa.automationpractice.util.ElementUtil;
import com.qa.automationpractice.util.JavaScriptUtil;

public class LoginPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	//1. locators - By
	By signInLink = By.xpath(".//a[contains(text(),\"Sign in\")]");
	By emailId = By.id("email");
	By password = By.id("passwd");
	By loginButton = By.id("SubmitLogin");
	By forgotPasswordLink = By.xpath(".//a[contains(text(),\"Forgot your password?\")]");
	By resetPasswordHeader = By.xpath(".//h1[contains(text(),\"Forgot your password?\")]");
	By goBackToLogin = By.xpath(".//span[contains(text(),\"Back to Login\")]");
	By invalidLoginError = By.xpath(".//p[contains(text(),\"There is 1 error\")]");
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	//Page Actions:
	public String getPageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	//When normal methods are not working then only use JavaScript
	public String getPageTitleUsingJS() {
		return jsUtil.getTitleByJS();
	}
	
	public boolean checkSignInLink() { 
		elementUtil.waitForElementPresent(signInLink); //using wait for element when required
		return elementUtil.doIsDisplayed(signInLink); 
	}
	
	public String getLoginPageTitle() {
		elementUtil.doClick(signInLink);
		elementUtil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String getMyAccountPageTitle() {
		elementUtil.waitForTitlePresent(AppConstants.ACCOUNT_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public HomePage doLogin(Credentials userCred) {
		
		elementUtil.doClick(signInLink);
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginButton);
		return new HomePage(driver); //Page chain model 
	}
	
	public boolean checkLoginErrorMsg() {
		return elementUtil.doIsDisplayed(invalidLoginError);
		
	}
	
	/*
	 * public boolean checkShowPasswordLink() { return
	 * driver.findElement(showPasswordLink).isDisplayed();
	 * //driver.findElement(showPasswordLink).click(); }
	 */
	
	/*
	 * public String checkForgotPasswordLink() { //return
	 * driver.findElement(forgotPasswordLink).isDisplayed();
	 * driver.findElement(forgotPasswordLink).click(); String a=
	 * driver.findElement(resetPasswordHeader).getText();
	 * driver.findElement(goBackToLogin).click(); return a;
	 * 
	 * }
	 */
	 
	
	
}
