package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		//Set up chrome driver path automatically
		WebDriverManager.chromedriver().setup();
		
		//With the above line of code, we dont have to use the below line to set the path
//		System.setProperty("webdriver.chrome.driver", "/Users/Admin/Documents/SeleniumDependencies/drivers/chromedriver.exe");
		
		//invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		
		//to make it full screen
		driver.manage().window().maximize(); 
		
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/* 
		Step 1. Launch Browser and navigate to https://dice.com
		Expected: DIce home page should be displayed
		 */
		
		String url = "https://dice.com";
		driver.get(url);
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com"; 
		if(actualTitle.contains(expectedTitle)) {
			System.out.println("Dice homepage successfully loaded");
		}else {
			System.out.println("step fails. did not load successfully");
			throw new RuntimeException("did not load successfully");
		}
		
		/* 
		Step 2. 
		Step 2. Insert search keyword and location and then click on find tech jobs
		Expected: 
		-Search result page should be loaded.
		-Page title should contain count of results along with search keyword
		-COunt of result should be displaed on the page
		 */
		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than 0
		int countRead = Integer.parseInt(count.replaceAll(",",""));
		
		if(countRead > 0) {
			System.out.println("Keyword: " + keyword + " search returned " + countRead+
					" in " + location);
		}else {
			System.out.println("Step failed: Keyword: " + keyword + " search returned " + countRead+
					" in " + location);
		}
		
		driver.close();
	}

}
