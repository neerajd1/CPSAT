package scripts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Basics {

	static WebDriver driver;
	static String baseURL;

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

	public void setUp(String url) {

		baseURL = url;
		// selectChrome();
		selectFirefox();
		driver.get(baseURL);

	}

	public void popupTest() throws InterruptedException {
		setUp("http://the-internet.herokuapp.com/javascript_alerts");
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[1]/button"))
				.click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		System.out.println(driver.findElement(By.xpath("//*[@id='result']"))
				.getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[2]/button"))
				.click();
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']"))
				.getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[2]/button"))
				.click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']"))
				.getText());
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[3]/button"))
				.click();
		Thread.sleep(1000);
		driver.switchTo().alert().sendKeys("selenium");
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']"))
				.getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='content']/div/ul/li[3]/button"))
				.click();
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//*[@id='result']"))
				.getText());
		Thread.sleep(1000);

	}

	public void waitTest() {
		setUp("https://www.wikipedia.org/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='js-link-box-en']/strong"))
				.click();
		driver.findElement(By.xpath("//*[@id='searInput']")).sendKeys(
				"Selenium");
		
		
		WebDriverWait wait=new WebDriverWait(driver,5);
		WebElement serachbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//*[@id='searchInput']")));
		
		
		driver.findElement(By.xpath("//*[@id='searInput']")).sendKeys("Selenium");

	}

	public void WikipediaTest() throws IOException {
		setUp("https://www.wikipedia.org/");
		driver.findElement(By.id("js-link-box-en")).click();
		driver.findElement(By.name("search")).sendKeys("Selenium software");
		driver.findElement(By.id("searchButton")).click();
		driver.findElement(By.linkText("Selenium (software)")).click();
		String title = driver.getTitle();
		System.out.println("Title" + title);
		String heading = driver.findElement(By.id("firstHeading")).getText();
		System.out.println("Heading" + heading);
		String pageSource = driver.getPageSource();
		System.out.println("Page Source :- \n" + pageSource);
		byte[] strToBytes = pageSource.getBytes();
		FileOutputStream outputStream;
		outputStream = new FileOutputStream(
				"C:\\Users\\AM101_PC15\\Desktop\\pagesource.txt");

		outputStream.write(strToBytes);
		outputStream.close();
	}

	public void googleTest() {
		setUp("https://www.google.co.in");
		System.out.println(driver.findElement(By.id("lst-ib")).getAttribute("title"));
		driver.findElement(By.xpath("//div[@id='hplogo']/a/img")).getAttribute(
				"title");
		System.out.println(driver.findElement(
				By.xpath("//div[@id='hplogo']/a/img")).getAttribute("title"));
		driver.findElement(By.id("lst-ib")).sendKeys("Selenium WebDriver");
		driver.findElement(By.name("btnK")).click();
		driver.navigate().back();
		WebElement webelement = driver.findElement(By.id("lst-ib"));
		webelement.sendKeys("Selenium");
		webelement.clear();
		webelement.sendKeys("Neeraj");
		webelement.sendKeys(Keys.BACK_SPACE);
		webelement.sendKeys("j");
		webelement.sendKeys(Keys.ENTER);

	}

	public void AgileTestingAllianceTest() {

		setUp("https://www.agiletestingalliance.org/");
		System.out.println("Image height is : " +driver.findElement(By.xpath("/html/body/header/div/div/h1/a/img")).getSize().getHeight());
		System.out.println("Image width is : " + driver.findElement(By.xpath("/html/body/header/div/div/h1/a/img")).getSize().getWidth());
		List<WebElement> allElements1 = driver.findElement(
				By.xpath("//footer/div[@class='soc-icons']")).findElements(
				By.tagName("a"));

		for (WebElement we : allElements1) {
			System.out.println(we.getAttribute("href"));
		}
	}

	public void windowManageTest() {
		setUp("https://www.google.co.in");
		driver.manage().window().maximize();
		Dimension n = new Dimension(592, 592);
		driver.manage().window().setSize(n);

		driver.navigate().back();
		driver.navigate().refresh();
		driver.navigate().to("https://www.guru99.com/");
		driver.navigate().forward();
		driver.manage().window().maximize();
	}
	
	
	public void annaUniversityTest() throws InterruptedException, IOException{
		setUp("https://www.annauniv.edu/");
		driver.findElement(
				By.xpath("/html/body/table/tbody/tr[1]/td[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td[5]/div/a"))
				.click();

		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.cssSelector("#link3 > strong"));

		action.moveToElement(we).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		we = driver.findElement(By.id("menuItemHilite33"));
		action.moveToElement(we).click().build().perform();
		Thread.sleep(2000);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("D:\\workspace-selenium_maven_jenkins\\TSL-719\\test\\resources\\screenshots\\Day3Exercise1\\screenshot.png"));
		
	}

	public void calculatorTest(){
		setUp("https://ata123456789123456789.appspot.com/");
		driver.findElement(By.id("ID_nameField1")).sendKeys("5");
		driver.findElement(By.id("ID_nameField2")).sendKeys("2");
		driver.findElement(By.id("gwt-uid-2")).click();
		driver.findElement(By.id("ID_calculator")).click();
		System.out.println("result : "+driver.findElement(By.id("ID_nameField3")).getAttribute("value"));
		
		driver.findElement(By.id("ID_nameField1")).sendKeys("5");
		driver.findElement(By.id("ID_nameField2")).sendKeys("2");
		List<WebElement> list = driver.findElement(
				By.xpath("//*[@id='calModeRbContainer1']")).findElements(
				By.tagName("span"));
		for (WebElement l : list) {

			l.findElement(By.tagName("input")).click();
			driver.findElement(By.id("ID_calculator")).click();
			System.out.println("result : "
					+ driver.findElement(By.id("ID_nameField3")).getAttribute(
							"value"));
		}

	}
	
	
	
	public static void main(String[] args) {

		Basics b = new Basics();
		// b.setUp("https://www.google.co.in");

		
		driver.quit();
	}

}

/*
 * Theory  ;-
 * 
 * 
 * */
