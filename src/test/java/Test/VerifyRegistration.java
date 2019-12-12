package Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyRegistration {
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
		driver.get("http://www.newtours.demoaut.com");
		driver.manage().window().maximize();
  }
  
  @Test
  public void TestRegistration() {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.linkText("REGISTER")).click();
				
		driver.findElement(By.name("firstName")).sendKeys("Mahinda");
		driver.findElement(By.name("lastName")).sendKeys("Rathnayaka");
		driver.findElement(By.name("phone")).sendKeys("+941234567899");
		driver.findElement(By.name("userName")).sendKeys("abc@gmail.com");
		driver.findElement(By.name("address1")).sendKeys("address1");
		driver.findElement(By.name("address2")).sendKeys("address2");
		driver.findElement(By.name("city")).sendKeys("Colombo");
		driver.findElement(By.name("state")).sendKeys("Colombo");
		driver.findElement(By.name("postalCode")).sendKeys("PS CL");
		Select drpCountry = new Select(driver.findElement(By.name("country")));
		drpCountry.selectByVisibleText("SRI LANKA");		
		driver.findElement(By.name("email")).sendKeys("mahinda");
		driver.findElement(By.name("password")).sendKeys("mahinda");
		driver.findElement(By.name("confirmPassword")).sendKeys("mahinda");
			
		driver.findElement(By.name("register")).click();
		
		String RegisterURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterURL, "http://newtours.demoaut.com/create_account_success.php");
  }
  
  @AfterMethod
  public void close(){
	  driver.close();
  }
}
