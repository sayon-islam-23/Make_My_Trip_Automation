package baseClasses;
import util.Report;
import io.github.bonigarcia.wdm.WebDriverManager;
import util.Report;
import util.Screenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseClass {
// initializing the webDriver.
public static WebDriver driver; 

// creating the logger variable for the extent report.
public static ExtentTest logger;

// creating time stamp variable which will fetch the dynamic time which will be use to naming the screenshots.
public static String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

@Test
@BeforeTest
@Parameters("browser_value")
public void setBrowser(@Optional("chrome") String browser_value) {
	if(browser_value.equalsIgnoreCase("chrome")) {
		
		// WebDriverManager is an open-source Java library is an open-source Java library. 
		// which is use to invoke chrome on browser.
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		// maximizing the window size.
		driver.manage().window().maximize();
		System.out.println("===============================================");
		System.out.println("Tests are opening in Google Chrome");
		System.out.println("===============================================\n");
	}
	else if(browser_value.equalsIgnoreCase("edge")) {
		
		// using webDriver manager to setup the Edge driver.
		// which is use to invoke edge on browser.
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		
		// maximizing the window size.
		driver.manage().window().maximize();
		System.out.println("===============================================");
		System.out.println("Tests are opening in Microsoft Edge");
		System.out.println("===============================================\n");
	}
	else {
		System.err.println("===============================================");
		System.err.println("Select Correct Option");
		System.err.println("===============================================\n");
	}
}
@Test
public void openMMT() throws IOException, InterruptedException {
	
	// creating a file reader instance to read data from an external file.
	FileReader fr = new FileReader(System.getProperty("user.dir")+"\\Resources\\config.properties");
	
	// creating Properties instance to read data from the properties file.
	Properties p = new Properties();
	
	// loading data from the properties file.
	p.load(fr);
	
	// in properties file data stored as a key value. so here we are fetching value of the "url" key from the properties file.
	String url = p.getProperty("url");
	
	// opening the website in the browser.
	driver.get(url);
	System.out.println("===============================================");
	System.out.println("Make My Trip Opens");
	System.out.println("===============================================\n");
	
	// creating the extent report.
	Report.setupExtentReports();
    Report.reportPass("URL Is WORKING");
    Report.reportPass("Make My Trip Website Opens Successfully !!!");
}
@Test
public void closePopUp() throws Exception {
	
	// creating the actions instance.
	Actions actions=new Actions(driver);
	
	// Refreshing the website.
	driver.navigate().refresh();
	Thread.sleep(10000);
	
	// closing the pop up.
	actions.moveByOffset(0,0).click().build().perform();
    Screenshot.takeSnapShot(driver, System.getProperty("user.dir")+"\\Screenshots\\"+timestamp+"_01.png");
}
@AfterTest
public void closeBrowser() {
	System.out.println("===============================================");
	System.out.println("Closing the Browser");
	System.out.println("===============================================\n");
	// closing the extent report.
	Report.endReport();
	// closing the browser.
	driver.quit();
}
}
