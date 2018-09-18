package scripts;

import java.io.File;
import java.sql.Timestamp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thoughtworks.selenium.webdriven.commands.GetText;

public class CrossWordTestNG {
	WebDriver driver;
	String search="Fiction";
	JavascriptExecutor jse ;
	@BeforeTest
	public void setUp() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver",
				"D:\\CP-SAT_Name\\CP-SAT_Exam\\test\\resources\\driver\\chromedriver(1).exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));
		
		jse=(JavascriptExecutor)driver;
		driver.get("https://www.crossword.in/");

	}


	@Test  
	public void CrossWord() throws Exception {
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='search-input']")).sendKeys("Fiction"+Keys.ENTER);
		
		List<WebElement> fiction=new ArrayList<WebElement>();
		
		//
		
		String NoOfResults=driver.findElement(By.xpath("//*[@id='search-results']/div[2]/div[1]/span[1]")).getText();
		
		System.out.println(NoOfResults);
		
		NoOfResults=NoOfResults.replaceAll("Showing ", "");
		NoOfResults=NoOfResults.replaceAll(" Results for "+search, "");
		System.out.println(NoOfResults);
		
		int count=Integer.parseInt(NoOfResults);
		
		for(int i=1;i<=count;i++)
		{
			try
			{
				fiction.add(driver.findElement(By.xpath("//*[@id='search-result-items']/li["+i+"]/div/div[2]/span[1]/a")));
				System.out.println(i+"."+driver.findElement(By.xpath("//*[@id='search-result-items']/li["+i+"]/div/div[2]/span[1]/a")).getText());
			}
			catch(Exception e)
			{
				jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			}
		}
		/*for(WebElement i:fiction)
		{
			System.out.println(i.getText());
		}*/
		
		

	}


	@AfterClass
	public void close() throws InterruptedException{
		Thread.sleep(1000);
		System.out.println("\n");
		//driver.quit();
	}

}