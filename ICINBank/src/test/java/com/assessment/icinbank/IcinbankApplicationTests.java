package com.assessment.icinbank;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


@SpringBootTest
class IcinbankApplicationTests {

	@Test
	public void adminLoginTest(){
		System.out.println("Login test started");

		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		String url = "http://localhost:4200/login";
		driver.get(url);
		System.out.println("Page is opened");
		sleep(2000);


		WebElement username = driver.findElement(By.xpath("//app-root/app-login//form//input[@type='text']"));
		username.sendKeys("piyushpranjal27@gmail.com");
		sleep(2000);

		WebElement password = driver.findElement(By.xpath("//app-root/app-login//form//input[@type='password']"));
		password.sendKeys("admin");
		sleep(2000);

		WebElement loginButton = driver.findElement(By.xpath("//app-root/app-login//form//button[@class='btn btn-primary']"));
		loginButton.click();
		sleep(2000);
		System.out.println("Login successfull");

		sleep(3000);
		WebElement logoutButton = driver.findElement(By.xpath("//div[@id='navbarSupportedContent']/button[@type='submit']"));
		logoutButton.click();
		sleep(2000);
		System.out.println("Logout successfull");

		driver.quit();
	}

	@Test
	public void negativeLoginTest(){
		System.out.println("Negative login test started");
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		String url = "http://localhost:4200/login";
		driver.get(url);
		System.out.println("Page opened");

		sleep(2000);

		WebElement username = driver.findElement(By.xpath("//app-root/app-login//form//input[@type='text']"));
		username.sendKeys("Incorrect username");
		sleep(2000);


		WebElement password = driver.findElement(By.xpath("//app-root/app-login//form//input[@type='password']"));
		password.sendKeys("admin");
		sleep(2000);

		WebElement loginButton = driver.findElement(By.xpath("//app-root/app-login//form//button[@class='btn btn-primary']"));
		loginButton.click();
		sleep(2000);

		String expectedUrl = "http://localhost:4200/login";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl,expectedUrl,"Actual page url is not the same as expected url");
		sleep(2000);

		driver.quit();
		System.out.println("Page closed");
		sleep(3000);
	}


	@Test
	public void userLoginTest(){

		System.out.println("User login test started");
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		String url = "http://localhost:4200/login";
		driver.get(url);


		WebElement username = driver.findElement(By.xpath("//app-root/app-login//form//input[@type='text']"));
		username.sendKeys("amitkr123@gmail.com");
		sleep(2000);


		WebElement password = driver.findElement(By.xpath("//app-root/app-login//form//input[@type='password']"));
		password.sendKeys("amitkr");
		sleep(2000);

		WebElement loginButton = driver.findElement(By.xpath("//app-root/app-login//form//button[@class='btn btn-primary']"));
		loginButton.click();
		sleep(2000);


		String expectedUrl = "http://localhost:4200/user/home";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl,expectedUrl,"Actual url differs from expected url");

		WebElement logoutButton = driver.findElement(By.xpath("//div[@id='navbarSupportedContent']/button[@type='submit']"));
		logoutButton.click();
		sleep(2000);
		System.out.println("Logout successfull");


		driver.quit();


	}












	public void sleep(long m) {

		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
