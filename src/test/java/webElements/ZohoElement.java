package webElements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ZohoElement {

	WebDriver driver;
	Faker data = new Faker();

	@BeforeClass // runs once for all tests
	public void setUp() {
		System.out.println("setting up webdriver in BeforeClass");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void seleniumWebElementsForm() throws InterruptedException {
		driver.get("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		List<WebElement> inputBoxes = driver.findElements(By.xpath("//input[@type='text']"));
		for (WebElement inputs : inputBoxes) {
			inputs.sendKeys(data.name().fullName());
		}
		List<WebElement> dropdownBox = driver.findElements(By.tagName("select"));
//	Select select = new Select(driver.findElement(By.tagName("select")));
				
		for (WebElement drops : dropdownBox) {
			Select s = new Select(drops);
			s.selectByIndex(data.number().numberBetween(1, 4));
		
		}
		Thread.sleep(4000);
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checks : checkBoxes) {
			
			checks.click();
			Thread.sleep(1000);
		}
		List<WebElement> radioBoxes = driver.findElements(By.xpath("//input[@type='radio']"));
		List<String> q = new ArrayList<>();
		q.add("First Question" + data.number().numberBetween(1, 4));
		q.add("Second Question" + data.number().numberBetween(1, 4));
		q.add("Third Question" + data.number().numberBetween(1, 4));
	
		
		questions(q);
		
		
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
		
		
		
		
		for (WebElement buts : buttons) {
			buts.click();
		}
		
		assertEquals(inputBoxes.size(), 2);
		assertEquals(dropdownBox.size(), 3);
		assertEquals(checkBoxes.size(), 9);
		assertEquals(radioBoxes.size(), 9);
		assertEquals(buttons.size(), 1);
	}

	
	public void questions(List<String> els) throws InterruptedException {
		
		
		for (String radios : els) { //First Question1
			String questions = radios.substring(0, radios.length()-1);
			String answers ="";
			int val =  Integer.parseInt(radios.substring(radios.length()-1));
			
			switch (val) {
			case 1:
				answers = "Answer A";
				break;
			case 2:
				answers = "Answer B";
				break;
			case 3:
				answers = "Answers C";
				break;
			}


			driver.findElement(By.xpath("//input[@type='radio' and @columnvalue = '"+answers+"' and @rowvalue='"+questions+"']")).click();
			
			
			Thread.sleep(1000);
		}
		
		
	}
}
