package testingacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testingacademy.resusable.ReusableMethods;

public class ProductPage extends ReusableMethods{
	WebDriver driver;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//driver.findElements(By.cssSelector(".col-sm-10"));
	// List of products locator
	@FindBy(css=".col-sm-10")
	List<WebElement> productList;
	// List of products to load locator
	By items = By.cssSelector(".col-sm-10");
	By name = By.cssSelector("b");
	By addToCart = By.cssSelector("button:last-of-type");
	By toastMessage = By.id("toast-container");

	public List<WebElement> getProductList() {
		//wait for products to load
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".col-sm-10"))));
		waitForElementsToLoad(items);
		return productList;
		}
	
	public void getProductByName(String productName){
		for(int i=0; i<productList.size(); i++) {
			if(productList.get(i).findElement(name).getText().equalsIgnoreCase(productName)) {
				productList.get(i).findElement(addToCart).click();
			}
		}
		waitForElementsToLoad(toastMessage);
	}
	
}
