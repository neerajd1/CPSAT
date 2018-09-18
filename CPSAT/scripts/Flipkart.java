package scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Flipkart {
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

		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("\tFlipkart.com loaded");

		try {
			driver.findElement(By.className("LM6RPg")).sendKeys(Keys.ESCAPE);
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
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("\tFlipkart.com loaded");

		try {
			driver.findElement(By.className("LM6RPg")).sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
		}
		driver.findElement(By.className("LM6RPg")).clear();
		driver.findElement(By.className("LM6RPg"))
				.sendKeys(search + Keys.ENTER);
		System.out.println("\t" + search + " searched");

		Thread.sleep(2000);

		driver.findElement(
				By.cssSelector("#container > div > div.t-0M7P._2doH3V > div._3e7xtJ > div > div._1HmYoV.hCUpcT > div._1HmYoV._35HD7C.col-10-12 > div:nth-child(1) > div > div._3ywJNQ > div:nth-child(4)"))
				.click();
		
		Thread.sleep(2000);
		System.out.println("\tLow To High set");
		List<WebElement> alist = driver.findElements(By.className("_1vC4OE"));

		System.out.println("\tResults :" + alist.size());

		sorted = new int[alist.size()];
		unsorted = new int[alist.size()];

		int j = 0;

		for (WebElement i : alist) {
			str = i.getText();
			str = str.replaceAll("₹", "");
			str = str.replaceAll(",", "");
			sorted[j] = Integer.parseInt(str);
			unsorted[j] = Integer.parseInt(str);
			j++;
		}
		Arrays.sort(sorted);

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
