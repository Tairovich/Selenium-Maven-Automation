package webElements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlowerPresentation {

	WebDriver driver;
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void presentation() throws InterruptedException {
		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
		
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> sources = new ArrayList<>();
		
		for (WebElement img : images) {
			sources.add(img.getAttribute("src"));
			
		}
		for (String src : sources) {
			
			driver.get(src);
			Thread.sleep(1000);
		}
		
	}
	
}
