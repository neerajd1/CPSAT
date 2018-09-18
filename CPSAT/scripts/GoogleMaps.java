package scripts.cpsatmock;



import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

////////////////////////////JUnit//////////////////////////////

public class GoogleMaps1 {

	private static WebDriver driver;
	private static String baseURL;

	@Before
	public void beforeTest() {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC15\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		driver.manage().window().maximize();
		baseURL = "https://www.google.com/maps/";
		driver.get(baseURL);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);

		System.out
				.println("******************************* 1 **************************");

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));
	}

	@Test
	public void test1() throws InterruptedException, IOException {

		System.out
				.println("******************************* 2 **************************");

		driver.findElement(By.id("searchboxinput")).sendKeys("Mandavgon Pharata");
		driver.findElement(By.id("searchbox-searchbutton")).click();
		
		String str = driver.findElement(By.xpath("//*[@id='pane']/div/div[1]/div/div/div[1]/div[3]")).getText();
		System.out.println(str);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\workspace-selenium_maven_jenkins\\TSL-719\\test\\resources\\screenshots\\maps.png"));
		
		
		driver.findElement(By.xpath("//*[@id='pane']/div/div[1]/div/div/div[1]/button[2]/div/div")).click();
		
		driver.findElement(By.xpath("//*[@id='sb_ifc51']/input")).sendKeys("LTI Mahape");
		
		driver.findElement(By.xpath("//*[@id='directions-searchbox-0']/button[1]")).click();
		
		str = driver.findElement(By.xpath("//*[@id='section-directions-trip-0']/div[2]/div[1]/div[1]/div[2]/div")).getText();
		System.out.println("Distance is: "+str);
		
		str = driver.findElement(By.xpath("//*[@id='section-directions-trip-0']/div[2]/div[1]/div[1]/div[1]/span[1]")).getText();
		System.out.println("Time required is: "+str);
	}

	@After
	public void afterTest() {
		driver.close();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));

	}

}
