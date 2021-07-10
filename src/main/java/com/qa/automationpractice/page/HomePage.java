package com.qa.automationpractice.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.automationpractice.base.BasePage;
import com.qa.automationpractice.util.AppConstants;
import com.qa.automationpractice.util.ElementUtil;

public class HomePage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	
	
    By pageHeader = By.xpath(".//h1[contains(text(),\"My account\")]");
	By siteLogo = By.xpath(".//img[@class='logo img-responsive']");
	By loggedInUserName = By.xpath(".//a[@title='View my customer account']");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getHomePageTitle() {
		//driver.findElement(siteLogo).click();
		elementUtil.doClick(siteLogo);
		elementUtil.waitForTitlePresent(AppConstants.PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	
	public String getAccountPageHeader() { 
		//return driver.findElement(pageHeader).getText(); 
		return elementUtil.doGetText(pageHeader);
	}
	 
	
	public String getLoggedInUserAccountName() {
		return elementUtil.doGetText(loggedInUserName);
	}
	
	
	
}
