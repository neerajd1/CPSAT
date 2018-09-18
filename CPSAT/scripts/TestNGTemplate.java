package scripts;

import static org.testng.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestNGTemplate {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.flipkart.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void Test() {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//div[2]/div/div/button")).click();
		driver.findElement(By.xpath("//input[@name='q']")).clear();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("samsung");

		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
		driver.findElement(
				By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div[3]"))
				.click();
		System.out
				.println(driver
						.findElement(
								By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div[3]"))
						.getText());

		String price1;
		String FinalPrice1;
		int FinalPr1;
		String price2;
		String FinalPrice2;
		int FinalPr2;

		for (int i = 2; i <= 4; i++) {
			int j = i + 1;

			if (isElementPresent(By
					.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["
							+ i
							+ "]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]"))) {
				price1 = driver
						.findElement(
								By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["
										+ i
										+ "]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]"))
						.getText();
				FinalPrice1 = price1.substring(1);
				System.out.println(FinalPrice1);
				FinalPr1 = Integer.parseInt(FinalPrice1);
				System.out.println("First Price in Int" + FinalPr1);
			} else {
				price1 = driver
						.findElement(
								By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+i+"]/div/div/div/a/div[2]/div[2]/div/div/div[1]"))
						.getText();
				FinalPrice1 = price1.substring(1);
				System.out.println(FinalPrice1);
				FinalPr1 = Integer.parseInt(FinalPrice1);
				System.out.println("First Price in Int" + FinalPr1);
			}

			if (isElementPresent(By
					.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["
							+ j
							+ "]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]"))) {
				price2 = driver
						.findElement(
								By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["
										+ j
										+ "]/div/div/div/a/div[3]/div[2]/div[1]/div/div[1]"))
						.getText();
				FinalPrice2 = price2.substring(1);
				System.out.println(FinalPrice2);
				FinalPr2 = Integer.parseInt(FinalPrice2);
				System.out.println("Second Price in Int" + FinalPr2);
			} else {
				price2 = driver
						.findElement(
								By.xpath("//*[@id='container']/div/div[1]/div[2]/div/div[1]/div[2]/div["+j+"]/div/div/div/a/div[2]/div[2]/div/div/div[1]"))
						.getText();
				FinalPrice2 = price2.substring(1);
				System.out.println(FinalPrice2);
				FinalPr2 = Integer.parseInt(FinalPrice2);
				System.out.println("Second Price in Int" + FinalPr2);
			}
			if (FinalPr2 >= FinalPr1) {
				System.out.println("Price sorting is fine");
			} else {
				System.out.println("Price sorting not working fine");
			}

		}

	}

	@AfterTest
	public void turnDown() {
		// driver.quit();
				String verificationErrorString = verificationErrors.toString();
				if (!"".equals(verificationErrorString)) {
					fail(verificationErrorString);
				}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	
}
