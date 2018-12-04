package com.pershyn.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebManager {
	
	private static final String PATH_TO_DRIVER = System.getProperty("user.dir") + "/chromedriver";
	private static final String PATH_TO_DRIVER2 = System.getProperty("user.dir") + "\\chromedriver.exe";
	
	public static WebDriver getChromeWebDriver(){
		
		String os = System.getProperty("os.name").toLowerCase();
		
		if(os.contains("mac"))		
		System.setProperty("webdriver.chrome.driver",PATH_TO_DRIVER);
	else
		System.setProperty("webdriver.chrome.driver",PATH_TO_DRIVER2);	
	
		
	WebDriver driver = new ChromeDriver();
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	
	return driver;
	}

}

