package Test;

import org.testng.annotations.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class VerifyBrockenLinks {
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
  public void TestBrockenLinks() {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

       java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
       Iterator<WebElement> it = links.iterator();
       while(it.hasNext()){
    	   WebElement currentElement = it.next();
    	   String elementName = currentElement.getText();
            String url = currentElement.getAttribute("href");
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            if(!url.startsWith("http://www.newtours.demoaut.com")){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }
            
            try {
                HttpURLConnection huc = (HttpURLConnection)(new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                int respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println(elementName + ": " + url+" is a broken link");
                }
                else{
                    System.out.println(elementName + ": " + url+" is a valid link");
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
  }
  
  @AfterMethod
  public void close(){
	  driver.close();
  }
}