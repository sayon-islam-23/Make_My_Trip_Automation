package pageClasses;

import baseClasses.BaseClass;
import util.Highlight;
import util.Report;
import util.Screenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookCab extends BaseClass{
	
	//Here we have used Page Object Model (POM). 
	//It is a design pattern in Selenium that creates an object repository for storing all web elements. 
	//It helps reduce code duplication and improves test case maintenance.
	
	By cabsPath = By.xpath("//li[@data-cy='menu_Cabs']");
    By fromPath = By.xpath("//label[@for='fromCity']");
    By fromInputPath = By.xpath("//input[@placeholder='From']");
    By pickUpPath = By.xpath("//span[contains(text(),'Delhi airport, ')]");
    By toInputPath = By.xpath("//input[@placeholder='To']");
    By destinationPath= By.xpath("//span[contains(text(),'Manali, ')]");
    By departureDatepath = By.xpath("//div[@aria-label='Sun Sep 17 2023']");
    By returnPath = By.xpath("//span[text()='Return']");
    By returnDatePath = By.xpath("//div[@aria-label='Thu Sep 21 2023']");
    By hourPath = By.xpath("//span[text()='06  Hr']");
    By minutePath = By.xpath("//span[text()='30  min']");
    By amPath = By.xpath("//span[@class='meridianSlotItemChild meridianSlotItemChild_sel']");
    By applyPath = By.xpath("//span[text()='APPLY']");
    By searchPath = By.xpath("//a[text()='Search']");
    
	@Parameters({"Location", "Destination"})
	@Test
	public void setInput(String Location, String Destination) throws Exception {
		try {
			
			// clicking on the cab section.
			WebElement cabs = driver.findElement(cabsPath);
			cabs.click();
			Thread.sleep(1000);
			WebElement from = driver.findElement(fromPath);
			from.click();
			Thread.sleep(1000);
			
			// entering pick up location.
			WebElement fromInput = driver.findElement(fromInputPath);
			Highlight.highlight(driver,fromInput);
			fromInput.sendKeys(Location);
			Thread.sleep(3000);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_2.png");
			WebElement pickup = driver.findElement(pickUpPath);
			pickup.click();
			Thread.sleep(1000);
			WebElement toInput = driver.findElement(toInputPath);
			Highlight.highlight(driver,toInput);
			toInput.sendKeys(Destination);
			Thread.sleep(3000);
			
			// entering the destination.
			WebElement destination = driver.findElement(destinationPath);
			Highlight.highlight(driver,destination);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_3.png");
			destination.click();
			
			// entering the departure date.
			WebElement departureDate = driver.findElement(departureDatepath);
			Thread.sleep(1000);
			Highlight.highlight(driver,departureDate);
			departureDate.click();
			
			// entering the return date.
			driver.findElement(returnPath).click();
			WebElement returnDate = driver.findElement(returnDatePath);
			Highlight.highlight(driver,returnDate);
			Thread.sleep(1000);
			returnDate.click();
			Thread.sleep(2000);
			
			// entering the time.
			driver.findElement(hourPath).click();
			Thread.sleep(1000);
			driver.findElement(minutePath).click();
			Thread.sleep(1000);
			driver.findElement(amPath).click();
			Thread.sleep(1000);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_4.png");
			driver.findElement(applyPath).click();
			Thread.sleep(1000);
			
			// searching available cars.
			driver.findElement(searchPath).click();
			Thread.sleep(3000);
			
			// cab booking report pass
			Report.reportPass("Cab Booked Successfully !!!");
		}
		catch(Exception e) {
			// cab booking report fail.
			Report.reportFail("Cab Booking Did not Passed");
		}
		
	}
}
