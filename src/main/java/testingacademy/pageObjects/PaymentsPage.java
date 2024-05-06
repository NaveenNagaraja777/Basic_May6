package testingacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testingacademy.resusable.ReusableMethods;

public class PaymentsPage extends ReusableMethods{
	WebDriver driver;
	public PaymentsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item"))));
	By countriesListDisplay= By.cssSelector(".ta-item");
//List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-item")); 
	@FindBy(css=".ta-item")
	List<WebElement> displayOfCountriesInDropDOwn;
//driver.findElement(By.cssSelector(".field .text-validated"))
	@FindBy(css=".field .text-validated")
	WebElement creditCardNumber;
//driver.findElement(By.xpath("//select[@class='input ddl'][1]"));
	@FindBy(xpath="//select[@class='input ddl'][1]")
	WebElement creditCardExpiryMonth;
//driver.findElement(By.xpath("//select[@class='input ddl'][2]"));
	@FindBy(xpath="//select[@class='input ddl'][2]")
	WebElement creditCardExpiryDate;
// driver.findElement(By.cssSelector(".field.small .input.txt"))
	@FindBy(css=".field.small .input.txt")
	WebElement cvvCode;
//driver.findElement(By.xpath("//div[@class='row'][3]/div/input"))
	@FindBy(xpath="//div[@class='row'][3]/div/input")
	WebElement nameOnCard;
//driver.findElement(By.name("coupon")).sendKeys("rs123");
	@FindBy(name="coupon")
	WebElement couponField;
//driver.findElement(By.cssSelector(".btn-primary")).click();
	@FindBy(css=".btn-primary")
	WebElement applyCouponButton;
//driver.findElement(By.cssSelector(".action__submit"))
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	
	By disappearSpinningWheel = By.cssSelector(".ngx-spinner-overlay");
	
By mess = By.cssSelector(".hero-primary");
	
	public void countrySelection(String countryName) {
		country.sendKeys(countryName);
		waitForElementsToLoad(countriesListDisplay);
		  
				  for(int i =0; i<displayOfCountriesInDropDOwn.size();i++) {
				  if(displayOfCountriesInDropDOwn.get(i).getText().
				  equalsIgnoreCase(countryName)) {
					  displayOfCountriesInDropDOwn.get(i).click(); } }
	}
	
	public void creditCardNumberToEnter(String number) {
		creditCardNumber.clear();
		creditCardNumber.sendKeys(number);
	}
	
	public void creditCardExpiryToSelect(String month, String date, String cvv, String nameOnCreditCard) {
		selectByVisibleText(creditCardExpiryMonth, month);
		selectByVisibleText(creditCardExpiryDate, date);
		cvvCode.sendKeys(cvv);
		nameOnCard.sendKeys(nameOnCreditCard);	
	}
	
	public void applyCoupon(String couponCode) {
		couponField.sendKeys(couponCode);
		applyCouponButton.click();
		waitForElementsToDisappear(disappearSpinningWheel);
	}
	
	public String placeOrder() {
		//waitForElementsToDisappear(disappearSpinningWheel);
		placeOrderButton.click();
		waitForElementsToLoad(mess);
		ConfirmationPage cf = new ConfirmationPage(driver);
		return cf.message();
	}
}
