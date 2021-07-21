import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HCL_Return_Request_order 
{
	WebDriver driver ;
	
	@Test
	public void returnProductRequest() throws InterruptedException, IOException
	{
		String systempath = System.getProperty("user.dir");
		
		System.setProperty("webdriver.chrome.driver", systempath+"/"+"src/drivers/chromedriver.exe" );
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get("https://return-order-app.herokuapp.com/");
		
		
		// send order id into text field
		driver.findElement(By.xpath("//input[@id='order-id']")).sendKeys("1257945872");
		
		// click on find order button
		driver.findElement(By.xpath("//button[contains(text(),'Find Order')]")).click();
		
		// verifying the element is present or not if present continue with further test else stop execution 
		List<WebElement> returnbutton = driver.findElements(By.xpath("//button[contains(text(),'Return This Product')]"));
	
		int numberofbutton =  returnbutton.size();
		
		if (numberofbutton == 1) 
		{
			// click on return this product
			 driver.findElement(By.xpath("//button[contains(text(),'Return This Product')]")).click(); 
			 			
			// after click it take more time to load page so require wait helper
			 WebDriverWait wait = new WebDriverWait(driver, 10);

			WebElement element = wait.until(
				     ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/div[@id='root']/div[1]/div[2]//div[@class='result']")));
				
				     // get token id
				    String gettokenidtext = element.getText();
					
					System.out.println("order token id is : " + gettokenidtext);
					
					// sending token id to text file
					File placetoken = new File("result.txt");

					FileWriter writefile = new FileWriter(placetoken);

					writefile.write(gettokenidtext);
					
					writefile.close();
					
					System.out.println("order has return successfully");
								
		
		} else 
		{
			System.out.println("Please enter the valid order id");
		}
	}
}
