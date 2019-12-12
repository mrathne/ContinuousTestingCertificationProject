package Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyCruises {
  ChromeDriver driver;
  
  @BeforeMethod
  public void launch(){
	  	System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox"); //Bypass OS security model   
		options.addArguments("--start-maximized");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.get("http://www.newtours.demoaut.com/");
		driver.manage().window().maximize();
  }
  
  @Test
  public void TestCruises() {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.linkText("Cruises")).click();
				
	
		String RegisterURL = driver.getCurrentUrl();
		String correctURL = "";
		System.out.println(RegisterURL);
		if(RegisterURL.startsWith("http://www.newtours.demoaut.com/mercurycruise.php")) {
			correctURL = "URL is correct";
		}
		System.out.println(correctURL);
		Assert.assertEquals(correctURL, "URL is correct");
  }
  
  @AfterMethod
  public void close(){
	  driver.close();
  }
}

