package testingacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.resusable.ReusableMethods;

public class OrdersPage extends ReusableMethods{
	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productName;

	public void productNameCheck(String productNameInCart) {
		navigation("Orders");
		boolean name = false;
		for(int i=0; i<productName.size();i++) {
			if(productName.get(i).getText().equalsIgnoreCase(productNameInCart)) {
				name=true;
				break;
			}
		}
	}
}
