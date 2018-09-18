# CPSAT


File pathToBinary = new File("C:\\Users\\AM101_PC15\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.manage().window().maximize();

System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();

    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='js-link-box-en']/strong")).click();
		
		
		WebDriverWait wait=new WebDriverWait(driver,5);
		WebElement serachbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//*[@id='searchInput']")));
		
    
    @Test(enabled = false)
	public void Second() {
		System.out.println("This is the Test Case number Two");
	}
  
  
  
  		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[1]/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		System.out.println(driver.findElement(By.xpath("//*[@id='result']")).getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[2]/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']")).getText());	
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[2]/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']")).getText());
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[3]/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().sendKeys("selenium");
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']")).getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[3]/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']")).getText());
		Thread.sleep(1000);



	private static WebDriver driver;
	private static String baseURL;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() {

		selectChrome();
		selectFirefox();	
		baseURL = "https://www.flipkart.com/";

	}

	@Test
	public void testLowToHigh() {

	}

	@Test
	public void testHighToLow() {

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		String verificationErrorString = verificationErrors.toString();
package scripts;

import java.io.File;
import java.sql.Timestamp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
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

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
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

public class DragAndDrop {
	WebDriver driver;
	String search = "Fiction";
	Actions action;
	JavascriptExecutor jse;

	@BeforeTest
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"D:\\CP-SAT_Name\\CP-SAT_Exam\\test\\resources\\driver\\chromedriver(1).exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

		jse = (JavascriptExecutor) driver;
		driver.get("https://code.makery.ch/library/dart-drag-and-drop/");

	}

	@Test
	public void CrossWord() throws Exception {

		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.switchTo().frame(
				driver.findElement(By
						.xpath("/html/body/div/article/div/div/iframe[1]")));
		Thread.sleep(1000);

		
		driver.findElement(By.xpath("/html/body/div/img[4]")).click();
		
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\before.png"));
		

		action.dragAndDrop(
				driver.findElement(By.xpath("/html/body/div/img[4]")),
				driver.findElement(By.xpath("/html/body/div/div"))).perform();

		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\after.png"));
	}

	@AfterClass
	public void close() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("\n");
		// driver.quit();
	}

}		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {

		return false;
	}

	private void selectFirefox() {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC15\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
	}

	private void selectChrome() {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

public boolean verifyTextPresent(String value) {
		boolean x = driver.getPageSource().contains(value);
		return x;
	}
}


Actions action = new Actions(driver);
		WebElement we =driver.findElement(By.xpath("//*[@id='stUI192_txt']"));
		action.moveToElement(we).build().perform();
		Thread.sleep(2000);
		we = driver.findElement(By.id("stUI196_cnt"));
		action.moveToElement(we).build().perform();
		we.click();
    
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\workspace-selenium_maven_jenkins\\TSL-719\\test\\resources\\screenshots\\Day3Exercise1\\screenshot.png"));
