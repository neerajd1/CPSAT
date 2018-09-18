package scripts;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstCry {
	WebDriver driver;
	String str = "";
	int[] sorted;
	int[] unsorted;

	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("http://www.firstcry.com/");
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("\tFirstCry.com loaded");

		try {
			driver.findElement(By.className("_pop_close"))
					.sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
		}


		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

	}

		SoftAssert s_assert;
	WebDriver driver;

	@SuppressWarnings("deprecation")
	public static String[][] getExcelData(String fileName, String sheetName)
			throws IOException {
		String[][] arrayExcelData = null;
		Workbook wb = null;
		try {
			File file = new File(fileName);
			FileInputStream fs = new FileInputStream(file);
			// .xls
			if (fileName.substring(fileName.indexOf(".")).equals(".xlsx")) {

				// If it is xlsx file then create object of XSSFWorkbook class

				wb = new XSSFWorkbook(fs);
			} else if (fileName.substring(fileName.indexOf(".")).equals(".xls")) {
				// If it is xls file then create object of HSSFWorkbook class
				wb = new HSSFWorkbook(fs);
			}
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfRows = sh.getPhysicalNumberOfRows();
			int totalNoOfCols = sh.getRow(0).getPhysicalNumberOfCells();

			System.out.println("totalNoOfRows=" + totalNoOfRows + ","
					+ " totalNoOfCols=" + totalNoOfCols);
			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
			for (int i = 1; i <= totalNoOfRows - 1; i++) {
				for (int j = 0; j <= totalNoOfCols - 1; j++) {
					sh.getRow(i).getCell(j).setCellType(1);
					arrayExcelData[i - 1][j] = sh.getRow(i).getCell(j)
							.getStringCellValue().toString();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getExcelData()");
		}
		return arrayExcelData;
	}

	

	@DataProvider(name = "DP1")
	public Object[][] createData1() throws IOException {
		Object[][] retObjArr = getExcelData(
				"test\\resources\\data\\movie_data_POI.xls", "DataPool");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "DP1")
	public void checkPrice(String search) throws InterruptedException {

		System.out
				.println("***************Test Case Started**********************");
		driver.get("http://www.firstcry.com/");
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("\tFirstCry.com loaded");

		try {
			driver.findElement(By.className("_pop_close"))
					.sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
		}

		driver.findElement(By.id("search_box")).clear();
		driver.findElement(By.id("search_box")).sendKeys(search + Keys.ENTER);
		System.out.println("\t" + search + " searched");

		driver.findElement(
				By.xpath("//div[contains(@class,'lft') and contains(@class,'shortby') and contains(@class,'sortpr')]"))
				.click();
		
		Thread.sleep(3000);

		driver.findElement(
				By.xpath("//div[contains(@class,'lft') and contains(@class,'shortby') and contains(@class,'sortpr') and contains(@class,'fact')]"))
				.click();
		
		Thread.sleep(3000);

		System.out.println("\tHigh To Low set");
		
		List<WebElement> alist = driver.findElements(By.className("r1"));

		System.out.println("\tResults :" + alist.size());

		sorted = new Integer[alist.size()];
		unsorted = new Integer[alist.size()];

		int j = 0,temp;

		for (WebElement i : alist) {
			str = i.getText();
			str = str.replaceAll("Rs.","");
			temp = (int)Double.parseDouble(str);
			sorted[j] = new Integer(temp);
			unsorted[j] = new Integer(temp);
			j++;
		}
		
		Arrays.sort(sorted, Collections.reverseOrder()); 

		if (Arrays.equals(sorted, unsorted)) {
			System.out.println("\t" + search + " are Sorted");
		} else
			System.out.println("\t" + search + " are Not sorted");

	}

	@AfterClass
	public void close() {
		driver.close();
	}

	
}
