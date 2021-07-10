package com.qa.automationpractice.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automationpractice.base.BasePage;

public class ElementUtil extends BasePage{

	WebDriver driver;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;
	Properties prop;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public boolean waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public String doGetPageTitle() {
		try {
		return driver.getTitle();
		}catch(Exception e) {
			System.out.println("Some execption got occured while getting the page title");
		}
		return null;
	}
	
	//this method is used to create webelement on the basis of By locator
	//param locator
	//return element
	public WebElement getElement(By locator) {
		WebElement element= null;
		try {
			//if(waitForElementPresent(locator));
				element = driver.findElement(locator);
				if(highlightElement)
					{
					jsUtil.flash(element);
					}
		} catch (Exception e) {
			System.out.println("Some execption got occured while creating the web element");
		}
		return element;
	}
	
	public void doClick(By locator) {
		try {
		getElement(locator).click();
		}
		catch(Exception e) {
			System.out.println("Some execption got occured while clicking on the web element");
		}
			
	}
	
	public void doSendKeys(By locator, String value) {
		try {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		}
		catch(Exception e) {
			System.out.println("Some execption got occured while entering value in a field");
		}
	}
	
	/*
	 * public void doSendKeysInt(By locator, CharSequence[] value1) { try {
	 * WebElement ele1 = getElement(locator); ele1.clear(); ele1.sendKeys(value1); }
	 * catch(Exception e) { System.out.
	 * println("Some execption got occured while entering value in a field"); } }
	 */
	
	public boolean doIsDisplayed(By locator) {
		try {
		 return getElement(locator).isDisplayed();
		}
		catch(Exception e) {
			System.out.println("Some execption got occured");
		}
		return false;
	}
	
	public String doGetText(By locator) {
		try {
		return getElement(locator).getText();
		}
		catch(Exception e) {
			System.out.println("Some execption got occured while getting the text");
		}
		return null; //default return
	}
}
