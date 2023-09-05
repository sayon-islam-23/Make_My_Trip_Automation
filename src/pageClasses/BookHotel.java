package pageClasses;
import baseClasses.BaseClass;
import util.ExcelData;
import util.Highlight;
import util.Report;
import util.Screenshot;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class BookHotel extends BaseClass{
	
	//Here we have used Page Object Model (POM). 
	//It is a design pattern in Selenium that creates an object repository for storing all web elements. 
	//It helps reduce code duplication and improves test case maintenance.
	
	By hotelPath = By.xpath("//li[@data-cy='menu_Hotels']");
    By fromPath = By.xpath("//label[@for='city']");
    By locationPath = By.xpath("//input[@placeholder='Where do you want to stay?']");
    By checkInPath = By.xpath("//div[@aria-label='Sun Sep 17 2023']");
    By checkOutPath = By.xpath("//div[@aria-label='Thu Sep 21 2023']");
    By roomPath = By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[2]/div/div[1]/div[4]/label/p/span/span[1]");
    By adultPath = By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div[2]/div/div[1]/div[4]/label/p/span/span[3]");
    By applyPath = By.xpath("//button[@data-cy='RoomsGuestsNew_327']");
    By searchPath = By.xpath("//button[text()='Search']");
    By homePath = By.xpath("//a[@data-cy='Logo_38']");

    By adultNumber = By.xpath("//p[@data-cy='roomGuestCount']");
    By adultList = By.xpath("//span[@data-testid='adult_count']");
    By adultDropDown = By.xpath("//ul[@class='gstSlct__list']/li");
    By adultListApply = By.xpath("//button[text()='Apply']");
    
    
	@Test
	@Parameters("destination_place")
	public void goToHotels(String destination_place) throws Exception {
		try {
			Thread.sleep(2000);
			
			// going to the Hotel Booking section.
			WebElement hotel = driver.findElement(hotelPath);
			Highlight.highlight(driver,hotel);
			hotel.click();
			Thread.sleep(2000);
			WebElement from = driver.findElement(fromPath);
			from.click();
			Thread.sleep(1000);
			
			// clicking on the location.
			WebElement location = driver.findElement(locationPath);
			Highlight.highlight(driver,location);
			location.click();
			Thread.sleep(1000);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_10.png");
			
			// entering the destination.
			location.sendKeys(destination_place);
			Thread.sleep(3000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			String destination = driver.findElement(By.id("city")).getAttribute("value");
			System.out.println("=====================================");
			System.out.println("Hotel Booking Destination = "+destination);
			System.out.println("=====================================\n");
			Thread.sleep(2000);
			
			// entering the check in date
			WebElement checkIn = driver.findElement(checkInPath);
			Thread.sleep(1000);
			Highlight.highlight(driver,checkIn);
			checkIn.click();
			
			// entering the check out date
			WebElement checkOut = driver.findElement(checkOutPath);
			Thread.sleep(1000);
			Highlight.highlight(driver,checkOut);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_11.png");
			checkOut.click();
			
			// getting number of room.
			String room = driver.findElement(roomPath).getText();
			
			// getting number of adults.
			String adults = driver.findElement(adultPath).getText();
			System.out.println("=====================================");
			System.out.println("No of rooms are booked :" + room + " for " + adults + " adult persons");
			System.out.println("=====================================\n");
			Thread.sleep(2000);
			
			// clicking on the apply button.
			WebElement apply = driver.findElement(applyPath);
			Highlight.highlight(driver,apply);
			apply.click();
			Thread.sleep(1000);
			
			// counting the list of adults options available
			listAdult();  
			Thread.sleep(2000);
			
			// clicking on the search button
			WebElement search = driver.findElement(searchPath);
			Highlight.highlight(driver,search);
			search.click();
			System.out.println("=====================================");
			
			// hotel booking successfully on extent report. 
			Report.reportPass("Hotel Booked Successfully !!!");
			System.out.println("=====================================\n");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,200)");
			Thread.sleep(2000);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_12.png");
			WebElement home = driver.findElement(homePath);
			home.click();
			Report.reportPass("Hotel Booked Successfully !!!");
			System.out.println("=====================================");
			System.out.println("Make My Trip Closes");
			System.out.println("=====================================\n");
		}
		catch(Exception e) {
			Report.reportFail("Exception in Hotel Booking");
		}
		
	}
	@Test
	public void listAdult() throws Exception { 
		
			// collecting the number of adult options available.
			driver.findElement(adultNumber).click();
			Thread.sleep(1000);
			driver.findElement(adultList).click();
			Thread.sleep(1000);
			// storing all the options of adult drop down box in a list and then iterating through it
		    List<WebElement> list = driver.findElements(adultDropDown);
		    List<String> number = new ArrayList<String>();

		    for (int i = 0; i < list.size(); i++) {
		        WebElement ele = list.get(i);
		        number.add(ele.getText()); //storing all the all adult number in another list  
		    }
		    driver.findElement(adultListApply).click();
			System.out.println("================================================");
		    System.out.println("Number of Adults available in the list");
		    //printing the list in console
		    System.out.println(number); 
			System.out.println("================================================\n");
		    
		    ExcelData.saveData(number);
		}
}
