package vaxinetest;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class TestforSlot 
{
	  
	static WebDriver driver;
	
	@Test
	public void withOutLogintest() throws IOException, InterruptedException, LineUnavailableException
	{
		// TODO Auto-generated method stub

         String actualpath = System.getProperty("user.dir");
         
         String probfilepath = actualpath+"/"+"src/drivers/prob.properties";
         FileInputStream fileInput = new FileInputStream(probfilepath);
     	 Properties prop = new Properties();
     	 prop.load(fileInput);
     	
     	String districtname = prop.getProperty("district");
		System.setProperty("webdriver.chrome.driver", actualpath+"/"+"src/drivers/chromedriver.exe" );
		driver = new ChromeDriver();
		
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
	
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://www.cowin.gov.in/");
		
		ngDriver.waitForAngularRequestsToFinish();
		
			
		
		Thread.sleep(1500);
		
	    WebElement webElement = driver.findElement(By.xpath("//div[@id='mat-tab-label-0-1']//div[contains(text(),'Search by District')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
		//webElement.click();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='mat-tab-label-0-1']//div[contains(text(),'Search by District')]")));
		 WebElement webElement22 = driver.findElement(By.xpath("//div[@id='mat-tab-label-0-1']//div[contains(text(),'Search by District')]"));
		  webElement22.click();
		
	int count = 1;		
	while (count >=1) 
	{
		// click on state 	
		driver.findElement(By.xpath("//mat-select[@formcontrolname='state_id']")).click();

	// click on maharashtra
		Thread.sleep(500);
		driver.findElement(By.xpath("//mat-option//span[contains(text(),'Maharashtra')]")).click();
		Thread.sleep(500);

	// click on district
		driver.findElement(By.xpath("//mat-select[@formcontrolname='district_id']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//mat-option//span[contains(text(),'"+districtname+"')]")).click();
		Thread.sleep(500);
        
			
	// click on search button	
		driver.findElement(By.xpath("//button[contains(text(),'Search')]")).click();
		
		Thread.sleep(800);

	// click on 18 above
		driver.findElement(By.xpath("//label[contains(text(),'18 & Above')]")).click();
		
	// list of available rows in available slot
		  //List<WebElement> elements = driver.findElements(By.xpath("//div[@class='mat-main-field center-main-field']//div[@class='row ng-star-inserted']"));

		
	// slot not available xpath
		//li[@class='ng-star-inserted']//div[@class='slots-box no-seat ng-star-inserted']
		  
	// check if dose 1 is available
		//li[@class='ng-star-inserted']//div[@class='dosetotal']//span[@title='Dose 1']//p
		  // need to do get text 
		
	// this find only green color xpath
		//li[@class='ng-star-inserted']//div[@class='slots-box ng-star-inserted']

	    
		/*WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='ng-star-inserted']//div[@class='dosetotal']")));
*/
		Thread.sleep(1500);
		
			if (driver.findElements(By.xpath("//li[@class='ng-star-inserted']//div[@class='dosetotal']")).size()!=0) 
			{
				System.out.println("dose is available");
				count=0;
				tone(1000,1000);
				break;
				
			} else 
			{
				System.out.println("dose not available");
				continue;
			}
		}
		
	}
	
	public static float SAMPLE_RATE = 8000f;

	  public static void tone(int hz, int msecs) 
	     throws LineUnavailableException 
	  {
	     tone(hz, msecs, 1.0);
	  }

	  public static void tone(int hz, int msecs, double vol)
	      throws LineUnavailableException 
	  {
	    byte[] buf = new byte[1];
	    AudioFormat af = 
	        new AudioFormat(
	            SAMPLE_RATE, // sampleRate
	            8,           // sampleSizeInBits
	            1,           // channels
	            true,        // signed
	            false);      // bigEndian
	    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
	    sdl.open(af);
	    sdl.start();
	    for (int i=0; i < msecs*8; i++) {
	      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
	      buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
	      sdl.write(buf,0,1);
	    }
	    sdl.drain();
	    sdl.stop();
	    sdl.close();
	  }

}
