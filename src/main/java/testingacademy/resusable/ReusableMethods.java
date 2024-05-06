package testingacademy.resusable;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {
	
	WebDriver driver;
	

	
	
	public ReusableMethods(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/']")
	WebElement home;
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/myorders']")
	WebElement orders;
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/cart']")
	WebElement cart;
	@FindBy(css=".fa.fa-sign-out']")
	WebElement signOut;

	
	public void waitForElementsToLoad(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementsToDisappear(By locator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitForWebElementToAppear(WebElement webElement) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public void selectByVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	
	public void navigation(String navigationTab) {
		switch(navigationTab) {
		  case "Home":
			  waitForWebElementToAppear(home);
			  home.click();
		    break;
		  case "Orders":
			  waitForWebElementToAppear(orders);
		    orders.click();
		    break;
		  case "Cart":
			  waitForWebElementToAppear(cart);
			  cart.click();
			  break;
		  case "Logout":
			  waitForWebElementToAppear(signOut);
			  signOut.click();
			  break;
		  default:
		    // code block
		}
		
	}

}
