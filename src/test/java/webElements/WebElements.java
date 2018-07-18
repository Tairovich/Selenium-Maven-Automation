package webElements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
		WebDriver driver;
		Faker data = new Faker();
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("setting up webdriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void myLinks() {
		driver.get("http://github.com");
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		//how many links are there on github page
		int numberOfGitLinks = links.size();
		System.out.println(numberOfGitLinks);
//		for (int i = 0; i < numberOfGitLinks; i++) {
//			if(!links.get(i).getText().isEmpty()) {
//		//	System.out.println(links.get(i).getText());
//			}
//		}
		
		//Add each link into a list of Strings
		
		List<String> strLinks = new ArrayList<>();
		for (WebElement link : links) {
			if(!link.getText().isEmpty()) {
				strLinks.add(link.getText());
			}
		}
		System.out.println("------------------------------");
		System.out.println(strLinks.toString());
		
		List<WebElement> menuLinks = driver.findElements(By.xpath("//a[@class='js-selected-navigation-item HeaderNavlink px-0 py-3 py-lg-2 m-0']"));
		
		assertEquals(menuLinks.size(), 5);
		
		
		
	}
	
	
	@Test
	public void seleniumWebElementsForm() {
		driver.get("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[@type='text']"));
		for (WebElement inputs : inputBoxes) {
			inputs.sendKeys(data.name().fullName());
		}
		
		
		
		List<WebElement> dropdownBox = driver.findElements(By.tagName("select"));
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		List<WebElement> radioBoxes = driver.findElements(By.xpath("//input[@type='radio']"));
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		
		
		
		
		assertEquals(inputBoxes.size(), 2);
		assertEquals(dropdownBox.size(), 3);
		assertEquals(checkBoxes.size(), 9);
		assertEquals(radioBoxes.size(), 9);
		assertEquals(buttons.size(), 1);
	}
	
	
	
	
	
}
