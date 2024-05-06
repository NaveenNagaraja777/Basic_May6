package testingacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.resusable.ReusableMethods;

public class ConfirmationPage extends ReusableMethods{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//driver.findElement(By.cssSelector(".hero-primary"))
		@FindBy(css=".hero-primary")
		WebElement successMessage;
		
		public String message() {
			return successMessage.getText();
		}

	
	
}
