package vaxinetest;


import java.io.FileInputStream;

import java.io.IOException;
import java.util.List;
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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class VaccineClass 
{
	WebDriver driver ;
	
	@Test
	public void cowidTest() throws InterruptedException, LineUnavailableException, IOException
	{
		
		String actualpath = System.getProperty("user.dir");
        
        /*String probfilepath = actualpath+"/"+"src/drivers/prob.properties";
        FileInputStream fileInput = new FileInputStream(probfilepath);
    	 Properties prop = new Properties();
    	 prop.load(fileInput);*/
    	
    	//String districtname = prop.getProperty("district");
		System.setProperty("webdriver.chrome.driver", actualpath+"/"+"src/drivers/chromedriver.exe" );
		driver = new ChromeDriver();
		
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
		NgWebDriver ngDriver = new NgWebDriver(jsDriver);
	
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://selfregistration.cowin.gov.in/");
		
		ngDriver.waitForAngularRequestsToFinish();
	
// enter mobile number and click on get otp		
		enterMobileNumber();
		
		Thread.sleep(5000);	
		
// enter opt and verify and continue		
		
		verifyOPTAndContinue();
		
		Thread.sleep(2000);
			
// select person to book appointment 
			
			//selectPersonForBook("nitin");
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		
		WebElement schedulebutton = driver.findElement(By.xpath("//li[@class='bordernone ng-star-inserted']//a[@aria-label='Schedule']//span[contains(text(),'Schedule')]"));
		js2.executeScript("arguments[0].click();", schedulebutton);
				
		Thread.sleep(1000);
// click on search by district
		
		
		Actions action = new Actions(driver);
		WebElement pseudoEle = driver.findElement(By.xpath("//label[@for='status']//button[@data-checked='Search By District']"));
		action.moveToElement(pseudoEle).click().perform();
		
		/*WebElement pseudoEle = driver.findElement(By.xpath("//label[@for='status']//button[@data-checked='Search By District']"));
		((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], '::after').click();",pseudoEle);
	
		WebElement webelementswe = driver.findElement(By.xpath("//li[@class='bordernone ng-star-inserted']//a[@aria-label='Schedule']//span[contains(text(),'Schedule')]"));
		String script = "return window.getComputedStyle(arguments[0], '::after').click();";
		JavascriptExecutor js22 = (JavascriptExecutor) driver;
		js22.executeScript(script, webelementswe);*/
		
		
		/*String script = "return window.getComputedStyle(document.querySelector('.okButton'),':after').getPropertyValue('content')";
		IJavaScriptExecutor js = (IJavaScriptExecutor)_driver;
		String content = (String) js.ExecuteScript(script);*/
		
/*// enter pin code 			
			driver.findElement(By.xpath("//input[@id='mat-input-2']")).sendKeys("422605");
			
// search button
			driver.findElement(By.xpath("//ion-col[@class='col-padding ion-text-start ng-star-inserted md hydrated']//ion-button[contains(text(),'Search')]")).click();
		*/
		
		
	int count = 1;		
	while (count >=1) 
    {
		Thread.sleep(1000);	
// click on state 	
	driver.findElement(By.xpath("//mat-select[@formcontrolname='state_id']")).click();

// click on maharashtra
	Thread.sleep(500);
	driver.findElement(By.xpath("//mat-option//span[contains(text(),'Maharashtra')]")).click();
	Thread.sleep(500);

// click on district
	driver.findElement(By.xpath("//mat-select[@formcontrolname='district_id']")).click();
	Thread.sleep(500);
	driver.findElement(By.xpath("//mat-option//span[contains(text(),'Solapur')]")).click();
	Thread.sleep(500);
	
// click on search button	
	driver.findElement(By.xpath("//ion-button[contains(text(),'Search')]")).click();

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
	
	public void enterMobileNumber() throws InterruptedException 
	{
	//	driver.findElement(By.xpath("//input[@id='mat-input-0']")).click();
		
		int count2 =0;
		
		for (int i = 1; i <= 35; i++) 
		{
			Thread.sleep(2000);
		
			String getentermobilenumber = driver.findElement(By.xpath("//div[@class='mat-form-field-infix ng-tns-c85-6']//input[@type='number']")).getAttribute("value");
			
			System.out.println("get mobile number from input field " + getentermobilenumber);
			
			int lengthofmobilenumber = getentermobilenumber.length();
			
			System.out.println("legth of acual enter : " + lengthofmobilenumber);
				
			if (lengthofmobilenumber==10) 
				{
					driver.findElement(By.xpath("//ion-button[contains(text(),'Get OTP')]")).click();
					Thread.sleep(1000);
					List<WebElement> otpvericationpage = driver.findElements(By.xpath("//ion-button[contains(text(),'Verify & Proceed')]"));
					int getcountofelements = otpvericationpage.size();
					if (getcountofelements>=1) 
					{
						count2=1;
						break;
					} else 
					{
						count2=0;
						continue;
					}				
					
				} else 
				{
					if (count2==1) 
					{
						break;
					}
						count2=0;
						continue;
					
				}
				
			
		}
	}

	
	public void selectPersonForBook(String nameofperson) throws InterruptedException 
	{
		int checkoutgrid = driver.findElements(By.xpath("//body[1]/app-root[1]/ion-app[1]/ion-router-outlet[1]/app-beneficiary-dashboard[1]/ion-content[1]/div[1]/div[1]/ion-grid[1]/ion-row[1]/ion-col[1]/ion-grid[1]/ion-row[@class='sepreetor ng-star-inserted md hydrated']")).size();
		
		for (int i = 1; i <= checkoutgrid; i++) 
		{
			Thread.sleep(500);
			
			List<WebElement> checkingnameofperson = driver.findElements(By.xpath("(//body[1]/app-root[1]/ion-app[1]/ion-router-outlet[1]/app-beneficiary-dashboard[1]/ion-content[1]/div[1]/div[1]/ion-grid[1]/ion-row[1]/ion-col[1]/ion-grid[1]/ion-row[@class='sepreetor ng-star-inserted md hydrated'])["+i+"]//ion-col//ion-grid//ion-row[1]/ion-col[@class='ion-text-start md hydrated']//div[@class='name-box']//span[1]//h3[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'"+nameofperson+"')]"));
			
			int namecount = checkingnameofperson.size();
			
			if (namecount==1) 
			{			
				WebElement element1 = driver.findElement(By.xpath("(//body[1]/app-root[1]/ion-app[1]/ion-router-outlet[1]/app-beneficiary-dashboard[1]/ion-content[1]/div[1]/div[1]/ion-grid[1]/ion-row[1]/ion-col[1]/ion-grid[1]/ion-row[@class='sepreetor ng-star-inserted md hydrated'])["+i+"]//ion-col//ion-grid//ion-row[4]//ion-col[2]//ul//li//a//span"));				
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", element1);
				Thread.sleep(1000);
				element1.click();
				System.out.println("selected user for click : " + nameofperson);
				break;
			} else 
			{
				continue;

			}
		}
	}


	public void verifyOPTAndContinue() throws InterruptedException 
	{
		WebElement checkementpresent = driver.findElement(By.xpath("//input[@id='mat-input-1']"));
		
		checkementpresent.click();
			
			int count = 0;
			
			for (int i = 1; i <= 25; i++) 
			{
				Thread.sleep(5000);
			
				String getvaluefrominput = driver.findElement(By.xpath("//input[@id='mat-input-1']")).getAttribute("value");
				
				System.out.println("get text from input field " + getvaluefrominput);
				
				int lengthofstring = getvaluefrominput.length();
				
				System.out.println("legth of acual enter : " + lengthofstring);
					
				if (lengthofstring==6) 
					{
						driver.findElement(By.xpath("//ion-button[contains(text(),'Verify & Proceed')]")).click();
						count = 1;
						break;
						
					} else 
					{
						count=0;
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

	  
	public static void tone(int hz, int msecs, double vol)throws LineUnavailableException 
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
