package pageClasses;

import baseClasses.BaseClass;
import util.Highlight;
import util.Report;
import util.Screenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BuyGiftCard extends BaseClass{
	
	//Here we have used Page Object Model (POM). 
	//It is a design pattern in Selenium that creates an object repository for storing all web elements. 
	//It helps reduce code duplication and improves test case maintenance.

   	By giftPath = By.xpath("//span[text()='Giftcards']");
    By birthDayPath = By.xpath("/html/body/div[1]/div/div[2]/div/div[1]/div/div[2]/div[3]/ul/li[2]/div/div/p[1]");
    By senderPath = By.xpath("//input[@name='senderName']");
    By mobilePath = By.xpath("//input[@name='senderMobileNo']");
    By emailPath = By.xpath("//input[@name='senderEmailId']");
    By submitPath = By.xpath("//button[text()='BUY NOW']");
    By errorPath = By.xpath("//p[@class='red-text font11 append-top5']");
    
	public String window ;
	@Test
	public void SelectGift() throws Exception {
		
		// closing the upcoming pop up
		Thread.sleep(1000);
		driver.navigate().refresh();
		Actions actions=new Actions(driver);
		Thread.sleep(2000);
	    actions.moveByOffset(20, 100).click().build().perform();
	    window = driver.getWindowHandle();
	    
	    // going to the gift section.
		WebElement gift = driver.findElement(giftPath);
		gift.click();
		System.out.println();
		System.out.println("=====================================");
		System.out.println("Gift Card Section Opens");
		System.out.println("=====================================\n");
		Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_7.png");
	}
	
	@Test
	public void SelectBirthdayGift() throws Exception{
		try {
			
			// navigating the driver to the next window.
			for(String window: driver.getWindowHandles())
			{
				driver.switchTo().window(window);
			}
			Thread.sleep(2000);
			// scrolling down the webPage.
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,300)");
			Thread.sleep(1000);
			
			// selecting the birthday gift card.
			WebElement birthday = driver.findElement(birthDayPath);
			js.executeScript("arguments[0].click();",birthday);
			System.out.println();
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_8.png");
			System.out.println("=====================================");
			System.out.println("Birthday GiftCard Selected");
			System.out.println("=====================================\n");
			
			// selecting gift card test case pass.
			Report.reportPass("BirthDay GiftCard Booked Successfully !!!");
		}
		catch(Exception e) {
			// selecting gift card test case fail.
			Report.reportFail("Exception in BirthDay GiftCard Booking");
		}
		
	}
	
	@Parameters({"name","mobile_number","email_id"})
	@Test
	public void InputGiftData(String name, String mobile_number, String email_id) throws Exception{
		// providing Sender Name, Phone Number, and a wrong Email address and display the error message.
		try {
			for(String window: driver.getWindowHandles())
			{
				driver.switchTo().window(window);
			}
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
			Thread.sleep(1000);
			WebElement sender = driver.findElement(senderPath);
			Highlight.highlight(driver,sender);
			sender.sendKeys(name);
			Thread.sleep(1000);
			WebElement mobile = driver.findElement(mobilePath);
			Highlight.highlight(driver,mobile);
			mobile.sendKeys(mobile_number);
			Thread.sleep(1000);
			WebElement email = driver.findElement(emailPath);
			Highlight.highlight(driver,email);
			email.sendKeys(email_id);
			Thread.sleep(1000);
			WebElement submit = driver.findElement(submitPath);
			submit.click();
			Thread.sleep(2000);
			Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_9.png");
			WebElement error = driver.findElement(errorPath);
			System.err.println("============================================");
			System.err.println("The ERROR I'm getting => "+error.getText());
			System.err.println("============================================\n");
			Report.reportPass("BirthDay GiftCard Error Caught Successfully !!!");
			Thread.sleep(2000);
			driver.close();
			driver.switchTo().window(window);
		}
		catch(Exception e) {
			Report.reportFail("Exception in BirthDay GiftCard");
		}
		
	}
}
