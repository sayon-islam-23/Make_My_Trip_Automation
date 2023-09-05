package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Highlight {
	@Test
	public static void highlight(WebDriver driver, WebElement element)throws InterruptedException{
		
		// converting the webDriver object into the JavascriptExecutor.
	    JavascriptExecutor js=(JavascriptExecutor)driver;
	    
	    // applying highlight to the target webElement.
	    js.executeScript("arguments[0].setAttribute('style', 'background:lightgreen; border: 1px solid black;')",element);
	    Thread.sleep(2000);
	    js.executeScript("arguments[0].setAttribute('style','background:lightgreen; border: solid 2px white');", element);      
	}
}

