package pageClasses;
import baseClasses.BaseClass;
import util.ExcelData;
import util.Report;
import util.Screenshot;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SelectCar extends BaseClass{
	
	//Here we have used Page Object Model (POM). 
	//It is a design pattern in Selenium that creates an object repository for storing all web elements. 
	//It helps reduce code duplication and improves test case maintenance.
	
	By suvPath = By.xpath("//label[text()='SUV']"); 
    By lowestPricePath = By.xpath("//p[@class='font28 latoBlack blackText ']");
    By carNamePath = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div[3]/div/div/div[1]/div/div[2]/div[1]/div[1]/span[1]");
    By homePath = By.xpath("//a[@data-cy='Logo_38']");
    
    
	@Test
	public void selectSuvCar() throws Exception {
		// maximum 10 seconds waiting time to load all the webElements.
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// clicking on the SUV checkBox.
		driver.findElement(suvPath).click();
		Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_5.png");
		Thread.sleep(1000);
		try {
			
			// selecting the lowest price of the cab.
			String lowestPrice = driver.findElement(lowestPricePath).getText();
			
			System.out.println("=====================================");
			System.out.println(" -- Booking Informations -- ");
			System.out.println("=====================================\n");
			System.out.println("=====================================");
			System.out.println("The lowest price = "+ lowestPrice.substring(1));
			
			// selecting the lowest price car name.
			String carName = driver.findElement(carNamePath).getText();
			System.out.println("=====================================");
			System.out.println("Your Booked Car Name = "+ carName);
			System.out.println("=====================================\n");
			Thread.sleep(2000);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_6.png");
			
			// clicking the home button to return the home page.
			WebElement home = driver.findElement(homePath);
			home.click();
			Thread.sleep(2000);
			Report.reportPass("SUV car Booked Successfully !!!");
		}
		catch(Exception e) {
			Report.reportFail("Exception in SUV car Booking");
			System.out.println("No Cab Available, Try Another Time");
		}
	}
}





