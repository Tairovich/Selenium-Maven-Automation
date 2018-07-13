package com.jobapplication;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PersonalInfoTest {

	WebDriver driver;
	String firstName;
	String lastName;
	int gender;
	String dateOfBirth;
	String email;
	String phoneNumber;
	String city;
	String state;
	String country;
	int annualSalary;
	List<String> technologies;
	int yearsOfExperience;
    String education;
	String github;
	List<String> certificates;
	String additionalSkills;
	Faker data = new Faker();
	Random random = new Random();
	
	
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
		firstName = data.name().firstName();
		lastName = data.name().lastName();
		gender = random.nextInt(2)+1;
		dateOfBirth = data.date().birthday().toString();
		email = "omonjon.yokubov@yahoo.com";
		phoneNumber = data.phoneNumber().cellPhone();
		city = data.address().cityName();
		state = data.address().stateAbbr();
		country = data.address().country();
		annualSalary = data.number().numberBetween(60000, 150000);
		
		technologies = new ArrayList<>();
		technologies.add("Java-" + data.number().numberBetween(1, 4));
		technologies.add("HTML-" + data.number().numberBetween(1, 4));
		technologies.add("Selenium-WebDriver-" + data.number().numberBetween(1, 4));
		technologies.add("TestNG-" + data.number().numberBetween(1, 4));
		technologies.add("Maven-" + data.number().numberBetween(1, 4));
		technologies.add("Git-" + data.number().numberBetween(1, 4));
		technologies.add("JUnit-" + data.number().numberBetween(1, 4));
		technologies.add("Cucumber-" + data.number().numberBetween(1, 4));
		technologies.add("API Automation-" + data.number().numberBetween(1, 4));
		technologies.add("JDBC-" + data.number().numberBetween(1, 4));
		technologies.add("SQL-" + data.number().numberBetween(1, 4));
		yearsOfExperience = data.number().numberBetween(0, 11);
	    education = data.number().numberBetween(0, 4) + "";
		github = "https://github.com/Tairovich/Selenium-Maven-Automation.git" ;
		certificates = new ArrayList<>();
		certificates.add("Java OCA");
		certificates.add("AWS");
		additionalSkills = data.job().keySkills();
				
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
//		String nameError = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
//		assertEquals(nameError, "Enter a value for this field.");
		
		
	}
	
}
