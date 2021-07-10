package com.qa.automationpractice.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.automationpractice.util.JavaScriptUtil;

public class JavaScriptTest {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\SaideepaRajula\\Desktop\\Automation\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php");
		
		JavaScriptUtil js = new JavaScriptUtil(driver);
		
		//js.generateAlert("hi test popup");
		//js.refreshBrowserByJS(driver);
		
		//WebElement element = driver.findElement(By.xpath(".//a[contains(text(),\"Sign in\")]"));		
		//js.clickElementByJS(element);
		
		
		//WebElement emailId = driver.findElement(By.id("email"));
		//js.sendKeysUsingJSWithId("email", "dipa@gmail.com");
		
		//System.out.println(js.getBrowserInfo());
		//System.out.println(js.getPageInnerText());
		
		//System.out.println(js.getTitleByJS());
		
		//js.drawBorder(emailId);
		
		//js.flash(element);
		//js.scrollPageDown();
		
		WebElement element1 = driver.findElement(By.xpath(".//h1[contains(text(),\"Automation Practice Website\")]"));
		js.scrollIntoView(element1);
		//driver.quit();

	}
}
