package com.pershyn.service;

import com.pershyn.entity.Item;
import com.pershyn.util.Timer;
import com.pershyn.util.WebManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class Registrator {

    public static Logger logger = Logger.getLogger(Registrator.class.getName());

	private static WebDriver driver;

	public static WebDriver registerAccount(String email, String password, String name) {
		driver = WebManager.getChromeWebDriver();

		driver.get("https://www.amazon.com");

		Timer.pauseSeconds(3);

		String regElement = driver.findElement(By.id("nav-flyout-ya-newCust")).findElement(By.tagName("a")).getAttribute("href");

		driver.get(regElement);

		Timer.pauseSeconds(3);

		WebElement nameInput = driver.findElement(By.id("ap_customer_name"));
		WebElement emailInput = driver.findElement(By.id("ap_email"));
		WebElement passwordInput = driver.findElement(By.id("ap_password"));
		WebElement password_check_Input = driver.findElement(By.id("ap_password_check"));
		WebElement createAccButton = driver.findElement(By.id("continue"));
		Timer.pauseSeconds(3);
		nameInput.sendKeys(name);
		Timer.pauseSeconds(3);
		emailInput.sendKeys(email);
		Timer.pauseSeconds(3);
		passwordInput.sendKeys(password);
		Timer.pauseSeconds(3);
		password_check_Input.sendKeys(password);
		Timer.pauseSeconds(3);
		createAccButton.submit();
		Timer.pauseSeconds(5);

		String curPage = driver.getCurrentUrl();
		driver.get(curPage);
		Timer.pauseSeconds(5);

		return driver;

	}

	public static void signIn(String email, String password) {

		if(driver==null){
			driver = WebManager.getChromeWebDriver();

			driver.get("https://www.amazon.com");

			Timer.pauseSeconds(3);

		}

//		driver = WebManager.getChromeWebDriver();

		driver.get("https://www.amazon.com");

		Timer.pauseSeconds(3);

		String regElement = driver.findElement(By.id("nav-flyout-ya-signin")).findElement(By.tagName("a")).getAttribute("href");

		driver.get(regElement);

		Timer.pauseSeconds(3);

		WebElement emailInput = driver.findElement(By.id("ap_email"));
		emailInput.sendKeys(email);
		Timer.pauseSeconds(3);
		WebElement passwordInput = driver.findElement(By.id("ap_password"));
		passwordInput.sendKeys(password);

		Timer.pauseSeconds(3);
		WebElement signInButton = driver.findElement(By.id("signInSubmit"));
		signInButton.submit();
		Timer.pauseSeconds(3);
		String curPage = driver.getCurrentUrl();




	}

	public static Item addToCart(String itemId) {
		if(driver==null){
			driver = WebManager.getChromeWebDriver();

			driver.get("https://www.amazon.com");

			Timer.pauseSeconds(3);

		}

		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-text"));

		searchBox.sendKeys(itemId);
		Timer.pauseSeconds(3);

		searchButton.submit();
		Timer.pauseSeconds(3);

		String regElement = driver.findElement(By.id("result_0")).findElement(By.tagName("a")).getAttribute("href");

		driver.get(regElement);
		Timer.pauseSeconds(3);

		String productTitle = driver.findElement(By.id("productTitle")).getText();
		logger.info(productTitle);

		String price = driver.findElement(By.id("priceblock_ourprice")).getText().replace("$","");
		price = price.replace(",","");
		price = price.replace(".","");
		logger.info(price);

		String url = driver.getCurrentUrl();
		System.out.println(url);

        Timer.pauseSeconds(3);

		WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
		addToCartButton.submit();

		Timer.pauseSeconds(5);
		Boolean addedToCart =false;
		addedToCart = driver.findElement(By.id("huc-v2-order-row-confirm-text"))/*.findElement(By.className("a-size-medium a-text-bold"))*/.getText().equals("Added to Cart");
		logger.info(addedToCart.toString());


		if(addedToCart) {
		Item item = new Item();
		item.setItemId(itemId);
		item.setItemName(productTitle);
		item.setPrice(price);
		item.setUrl(url);
		return item;

		}


		 return null;
	}

}