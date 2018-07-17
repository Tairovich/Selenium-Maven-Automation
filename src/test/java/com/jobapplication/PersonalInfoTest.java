package com.jobapplication;

import static org.testng.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
		technologies.add("Selenium WebDriver-" + data.number().numberBetween(1, 4));
		technologies.add("Maven-" + data.number().numberBetween(1, 4));
		technologies.add("Git-" + data.number().numberBetween(1, 4));
		technologies.add("TestNG-" + data.number().numberBetween(1, 4));
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
		certificates.add("Scrum Master");
		
		additionalSkills = data.job().keySkills();
				
	}
	
	@Test
	public void submitFullApplication() {
		driver.findElement(By.xpath("//input[@name='Name_First']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='Name_Last']")).sendKeys(lastName);
		setGender(gender);
		setDateOfBirth(dateOfBirth);
		driver.findElement(By.xpath("//input[@name='Email']")).clear();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='countrycode']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@name='Address_City']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='Address_Region']")).sendKeys(state);
		Select countryElement = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));
		countryElement.selectByIndex(data.number().numberBetween(1, countryElement.getOptions().size()+1));
		driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(String.valueOf(annualSalary) + Keys.TAB);
		verifySalaryCal(annualSalary);
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
		//Evaluating skill set
		setSkillSet(technologies);
		yearsOfExperience(yearsOfExperience);
		
		Select education = new Select(driver.findElement(By.xpath("//select[@name='Dropdown']")));
		education.selectByIndex(data.number().numberBetween(1, education.getOptions().size()));
		
		driver.findElement(By.xpath("//input[@name='Website']")).sendKeys(github);
		certificates(data.number().numberBetween(1, 4));
		
		driver.findElement(By.xpath("//textarea[@name='MultiLine']")).clear();
		driver.findElement(By.xpath("//textarea[@name='MultiLine']")).sendKeys(additionalSkills);
		driver.findElement(By.xpath("//em[.='Apply']")).click();
		
		
	}
	
	

	
	public void certificates(int cert) {
	
			String OCA = "//input[@value='Java OCA']";
			String AWS = "//input[@value='AWS']";
			String Scrum = "//input[@value='Scrum Master']";
			String[] certArray = {OCA, AWS, Scrum};
			
			switch (cert) {
			case 1:
				driver.findElement(By.xpath(OCA)).click();
				break;
			case 2:
				driver.findElement(By.xpath(OCA)).click();
				driver.findElement(By.xpath(AWS)).click();
				break;
			case 3:
				driver.findElement(By.xpath(OCA)).click();
				driver.findElement(By.xpath(AWS)).click();
				driver.findElement(By.xpath(Scrum)).click();
				break;
			default:
				break;
			}
		
		}
	
	
	
	public void yearsOfExperience(int years) {
		if(years>0) {
			driver.findElement(By.xpath("//a[@rating_value='" + yearsOfExperience + "']")).click();
		}
		
	}
	
	
	public void setSkillSet(List<String> tech) {
		for (int i = 0; i < tech.size(); i++) {
			String technology = tech.get(i).substring(0, tech.get(i).length()-2);
			int rate = Integer.parseInt(  tech.get(i).substring(tech.get(i).length()-1)     );
			String level = "";
			switch (rate) {
			case 1:
				level = "Expert";
				break;
			case 2:
				level = "Proficient";
				break;
			case 3:
				level = "Beginner";
				break;
			default:
				break;
			}
			
			driver.findElement(By.xpath("//input[@rowvalue='" + technology + "' and @columnvalue='"+level + "']")).click();
		}
	}
	

//	public void setSkillSet(List<String> tech) {	//Java-2
//	
//		for (String skill : tech) {
//			
//			String techField = skill.substring(0, skill.length()-2);	//Java
//			int rate = Integer.parseInt(skill.substring(skill.length()-1)); //random number between 1 and 3
//			String skillLevel ="";
//			
//			switch (rate) {
//			case 1:
//				skillLevel = "Expert";
//				break;
//			case 2:
//				skillLevel = "Proficient";
//				break;
//			case 3:
//				skillLevel = "Beginner";
//			default:
//				break;
//			}
//			
//			driver.findElement(By.xpath("//input[@rowvalue ='"+techField+"'and @columnvalue='" + skillLevel+"']")).click();
//		}					
//	}

	
	public void verifySalaryCal(int annual) {
		String monthly = driver.findElement(By.xpath("//input[@name='Formula']")).getAttribute("value");
		String weekly = driver.findElement(By.xpath("//input[@name='Formula1']")).getAttribute("value");
		String hourly =driver.findElement(By.xpath("//input[@name='Formula2']")).getAttribute("value");
		DecimalFormat formatter = new DecimalFormat("#.##");
		
		assertEquals(Double.parseDouble(monthly),	Double.parseDouble( formatter.format((double)annual/12.0))		);
		assertEquals(Double.parseDouble(weekly),	Double.parseDouble( formatter.format((double)annual/52.0))		);
		assertEquals(Double.parseDouble(hourly), 	Double.parseDouble(formatter.format((double)annual/52.0/40.0))	);
	}
	
	public void setGender(int n) {
		if (n == 1) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		}else {
			driver.findElement(By.xpath("//input[@value='Female']")).click();
		}
	}
	
	// Sun Jan 17 00:41:54 CST 1960
	public void setDateOfBirth(String birthday) {
		String[] pieces = birthday.split(" ");
		String bir = pieces[2] +"-"+ pieces[1] + "-" + pieces[5];
		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(bir);
		
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
//		driver.findElement(By.xpath("//em[.=' Next ']")).click();
										//. means search by text when you dont have any attributes inside a tag
//		String nameError = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
//		assertEquals(nameError, "Enter a value for this field.");
		
		
	}
	
}
