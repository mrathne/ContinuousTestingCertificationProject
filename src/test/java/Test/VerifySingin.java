package Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifySingin {
  ChromeDriver driver;
  
  @BeforeMethod
  public void launch(){
	  	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
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
  public void testSingin() {
		driver.manage().timeouts().implicitlyWait(180,TimeUnit.SECONDS);
		driver.findElement(By.linkText("SIGN-ON")).click();
		
		driver.findElement(By.name("userName")).sendKeys("mahinda");
		driver.findElement(By.name("password")).sendKeys("mahinda");
		driver.findElement(By.name("login")).click();
				
	
		String RegisterURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterURL, "http://newtours.demoaut.com/mercuryreservation.php");
  }
  
  @AfterMethod
  public void close(){
	  driver.close();
  }
}


