package scripts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MeriPustak {
	WebDriver driver;
	String str = "";
	String offPercent = "";

	@BeforeTest
	public void setUp() {
		File pathToBinary = new File(
				"C:\\Users\\AM101_PC14\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);

		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@DataProvider(name = "DP2")
	public Object[][] createData2() {
		Object[][] retObjArr = getTableArray(
				"test\\resources\\data\\Book1.xls", "Sheet1", "delimiter");
		System.out.println("*****************  2 *************************");
		return (retObjArr);
	}

	@Test(dataProvider = "DP2")
	public void checkPrice(String search) {

		System.out
				.println("***************Test Case Started**********************");
		driver.get("http://www.meripustak.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@id='txtsearch']")).sendKeys(
				search + Keys.ENTER);
		for (int i = 1; i <= 10; i++) {

			try {
				offPercent = driver.findElement(
						By.xpath("//*[@id='book_list']/ul/li[" + i
								+ "]/div[2]/span[1]")).getText();
			} catch (Exception e) {
				offPercent = driver.findElement(
						By.xpath("//*[@id='book_list']/ul/li[" + i
								+ "]/div[1]/span[1]")).getText();
			}

			if (offPercent.equals("0%")) {
				System.out.println("No Discount on  Product No. : " + i);
				continue;
			}

			try {
				str = driver.findElement(
						By.xpath("//*[@id='book_list']/ul/li[" + i
								+ "]/div[6]/span[2]")).getText();
			} catch (Exception e) {
				str = driver.findElement(
						By.xpath("//*[@id='book_list']/ul/li[" + i
								+ "]/div[5]/span[2]")).getText();
			}

			System.out.println("******Book No. " + i + "*******");
			System.out.println(str);

		}
		System.out
				.println("***************Test Case Finished**********************\n\n");
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);
			int startRow, startCol, endRow, endCol, ci, cj;
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();
			Cell tableEnd = sheet.findCell(tableName, startCol + 1,
					startRow + 1, 100, 64000, false);
			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();
			System.out.println("startRow=" + startRow + ", endRow=" + endRow
					+ ", " + "startCol=" + startCol + ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;
			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in getTableArray()");
		}
		return (tabArray);
	}

}
