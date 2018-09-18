package scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFactoryTemplate {

	// By field1 = By.id("ID_nameField1");

	@FindBy(id = "ID_nameField1")
	WebElement field1;

	// By field2 = By.id("ID_nameField2");
	@FindBy(id = "ID_nameField2")
	WebElement field2;

	// By mulOption = By.id("gwt-uid-2");
	@FindBy(id = "gwt-uid-2")
	WebElement mulOption;

	// By calButton = By.id("ID_calculator");
	@FindBy(id = "ID_calculator")
	WebElement calButton;

	// By result = By.id("ID_nameField3");
	@FindBy(id = "ID_nameField3")
	WebElement result;

	WebDriver driver;

	PageFactoryTemplate(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public String multiply(String val1, String val2) {
		field1.clear();
		field1.sendKeys(val1);
		field2.clear();
		field2.sendKeys(val2);
		mulOption.click();
		calButton.click();
		WebElement element = result;
		String strvalue = element.getAttribute("value");
		System.out.println("Multiplied value :" + strvalue);
		return strvalue;
	}

}