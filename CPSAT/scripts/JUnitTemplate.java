package scripts;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class JUnitTemplate {

	private static WebDriver driver;
	private static String baseURL;

	@Before
	public void setUp() {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC15\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		baseURL = "https://www.google.co.in/";
		driver.get(baseURL);
	}

	@Test
	public void Test() {
		driver.findElement(By.id("lst-ib")).sendKeys("Selenium WebDriver");

		driver.findElement(By.name("btnK")).click();
		
		
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils
					.copyFile(
							scrFile,
							new File(
									"D:\\workspace-selenium_maven_jenkins\\TSL-719\\test\\resources\\screenshots\\Day3Exercise1\\screenshot.png"));//give full path
		} catch (IOException e) {
		
			e.printStackTrace();
		}

	}

	@After
	public void turnDown() {
		driver.close();
		}

}
