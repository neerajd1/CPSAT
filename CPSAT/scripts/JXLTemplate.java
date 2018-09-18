package scripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class JXLTemplate {

	private WebDriver driver;

	SoftAssert s_assert = new SoftAssert();

	@BeforeTest
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - Start Time - "
				+ new Timestamp(date.getTime()));

	}

	@DataProvider(name = "AnyID")
	public Object[][] createData1() {
		Object[][] retObjArr = getTableArray(
				"test\\resources\\data\\book_data1.xls", "DataPool",
				"bookTestData1");// method is defined below
		return (retObjArr);
	}

	@Test(dataProvider = "AnyID")
	public void xmlTest(String booktitle, String authorname) {

		driver.get("http://www.barnesandnoble.com/");
		try {
			driver.findElement(By.className("icon-close-modal")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("popup did not appear");
		}

		driver.findElement(By.id("searchBarBN")).clear();
		driver.findElement(By.id("searchBarBN")).sendKeys(booktitle);
		driver.findElement(By.className("icon-search-2")).click();
		driver.findElement(By.linkText(booktitle)).click();
		s_assert.assertTrue(verifyTextPresent(authorname));

	}

	@AfterTest
	public void turnDown() {
		driver.quit();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));
	}

	public boolean verifyTextPresent(String value) {
		boolean x = driver.getPageSource().contains(value);
		return x;
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
			System.out.println("error in getTableArray()");

		}

		return (tabArray);
	}

	public void writeToExcel(String fileName, String sheetName,
			String searchKeyword, String resultCount) throws BiffException,
			RowsExceededException, WriteException {
		try {
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			WritableWorkbook writeableWB = Workbook.createWorkbook(new File(
					fileName), workbook);
			WritableSheet sheet = writeableWB.getSheet(sheetName);
			Cell keyCell = sheet.findCell(searchKeyword);

			int keyRow = keyCell.getRow();
			int keyCol = keyCell.getColumn();
			Label label = new Label((keyCol + 1), keyRow, resultCount);
			sheet.addCell(label);
			writeableWB.write();
			writeableWB.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
