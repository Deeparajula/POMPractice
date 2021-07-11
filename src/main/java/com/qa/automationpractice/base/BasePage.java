package com.qa.automationpractice.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	//public WebDriver driver;
	public Properties prop; //Properties is a class of Java 
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get(); // whenever I use getDriver method it will give a webdriver local coppy 
	}
	
	
	public WebDriver init_driver(String browserName) {
		highlightElement = prop.getProperty("highlight").equals("yes") ? true : false;
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			System.out.println("browser name is chrome");
		}
		
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			System.out.println("browser name is firefox");
		} 
		
		else if(browserName.equals("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			System.out.println("browser name is safari");
		} 
		
		else {
			System.out.println(browserName + " is not found");
		}
		
		getDriver().manage().deleteAllCookies();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().manage().window().fullscreen();
		
		//driver.get(url);
		
		return getDriver();	
		
	}
	
	public Properties init_properties() {
		
		//Connection with Prop config file
		
		prop = new Properties(); //Properties is a class of Java
		String path = null;
		String env = null;
		
		try {
			env = System.getProperty("env");
			
			if(env.equals("qa")) {
				path = ".\\src\\main\\java\\com\\qa\\automationpractice\\config\\qa.config.properties";
			} else if (env.equals("stg")) {
				path = ".\\src\\main\\java\\com\\qa\\automationpractice\\config\\stg.config.properties";
			}
		} catch(Exception e) {
			path = ".\\src\\main\\java\\com\\qa\\automationpractice\\config\\config.properties";
		}
		
		//String path = ".\\src\\main\\java\\com\\qa\\automationpractice\\config\\config.properties";
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip); // loading the properties of config file to one object
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Issue with the config file");
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Screenshot captured failed");
		}
		
		return path;
		
	}
	
}
