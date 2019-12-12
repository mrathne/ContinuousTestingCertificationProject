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

public class VerifyFlightBooking {
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
  public void TestFlightBooking() {
		driver.manage().timeouts().implicitlyWait(180,TimeUnit.SECONDS);
		driver.findElement(By.linkText("SIGN-ON")).click();
		
		driver.findElement(By.name("userName")).sendKeys("mahinda");
		driver.findElement(By.name("password")).sendKeys("mahinda");
		driver.findElement(By.name("login")).click();
				
	
		String RegisterURL = driver.getCurrentUrl();
		Assert.assertEquals(RegisterURL, "http://newtours.demoaut.com/mercuryreservation.php");
		
		driver.findElement(By.linkText("Flights")).click();
				

		Select drpCountry = new Select(driver.findElement(By.name("toPort")));
		drpCountry.selectByVisibleText("London");
		
		Select returnDate = new Select(driver.findElement(By.name("toDay")));
		returnDate.selectByVisibleText("12");
		
		Select airline = new Select(driver.findElement(By.name("airline")));
		airline.selectByVisibleText("Unified Airlines");
			
		driver.findElement(By.name("findFlights")).click();
		
		String flightreserveURL = driver.getCurrentUrl();
		Assert.assertEquals(flightreserveURL, "http://newtours.demoaut.com/mercuryreservation2.php");
		                                       
		
		driver.findElement(By.name("reserveFlights")).click();
		String rescuePurchase = driver.getCurrentUrl();
		Assert.assertEquals(rescuePurchase, "http://newtours.demoaut.com/mercurypurchase.php");
  }
  
  @AfterMethod
  public void close(){
	  driver.close();
  }
}
