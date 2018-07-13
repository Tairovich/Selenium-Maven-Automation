package com.jobapplication;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PersonalInfoTest {

	WebDriver driver;
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("setting up webdriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeMethod //runs before each @Test
	public void navigateToHomePage() {
		System.out.println("navigating to homepage in @BeforeMethod");
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");
	}
	@Test
	public void fullNameEmptyTest() {
		//Firstly assert that you are on the right page
		assertEquals(driver.getTitle(), "SDET Job Application");
		//clear the first name
	//	<input type="text" maxlength="255" elname="first" name="Name_First" value="" invlovedinsalesiq="false">
		//from all these attributes we chose name attribute for our xpath
		driver.findElement(By.xpath("//input[@name='Name_First']")).clear();
		//clear the last name
		driver.findElement(By.xpath("//input[@name='Name_Last']")).clear();
		
		// <em> Next </em>
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
										//. means search by text when you dont have any attributes inside a tag
		String nameError = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
		assertEquals(nameError, "Enter a value for this field.");
		System.out.println("test failded");
		
	}
	
}
