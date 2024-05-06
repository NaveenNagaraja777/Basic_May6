package testingacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import testingacademy.resusable.ReusableMethods;

public class MyCart extends ReusableMethods{
WebDriver driver;
public MyCart(WebDriver driver) {
	super(driver);
	this.driver = driver;
	//If page factory is not defined inside construvtor then we will get null pointer exception
	//for ex : Exception in thread "main" java.lang.NullPointerException: Cannot invoke "org.openqa.selenium.WebElement.click()" because "this.cartButton" is null
	//at testingacademy.pageObjects.MyCart.checkOutFromMyCart(MyCart.java:33)
	//at testingacademy.PageObjectTest.main(PageObjectTest.java:28)
	PageFactory.initElements(driver, this);
	
}
//driver.findElement(By.xpath("//ul/li[4]/button"))
@FindBy(xpath="//ul/li[4]/button")
WebElement cartButton;

//driver.findElements(By.cssSelector(".infoWrap"));
@FindBy(css=".infoWrap")
List<WebElement> listCart;

//driver.findElement(By.cssSelector(".subtotal button")).click();
@FindBy(css=".subtotal button")
WebElement checkOutButton;

By checkOutDisplay = By.cssSelector(".subtotal button");

public boolean checkOutFromMyCart(String productName) {
	cartButton.click();	
	boolean name = true;
for(int i=0; i<listCart.size(); i++) {
	if(listCart.get(i).findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName)) {
		name= true;
	}
}
return name;

}

public void checkOut() {
waitForElementsToLoad(checkOutDisplay);
checkOutButton.click();
}
}