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
