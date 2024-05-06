package testingacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingacademy.resusable.ReusableMethods;

public class LandingPage extends ReusableMethods{
	
	WebDriver driver;
	//Constructor method is used to initialize the webdriver required for the page objects
	//Page Factory is a class provided by Selenium WebDriver to support Page Object Design patterns. 
	//In Page Factory, testers use @FindBy annotation. The initElements method is used to initialize web elements. 
	//@FindBy: An annotation used in Page Factory to locate and declare web elements using different locators. 
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	//driver.findElement is replaced by @FindBy in page factory. driver is intialized inside the constructor using initElements method 
	//driver.findElement(By.id("userEmail"))
	@FindBy(id="userEmail")
	WebElement userName;
	
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement loginError;
	
	@FindBy(xpath="//input[@id='userEmail']/following-sibling::div/div")
	WebElement emptyUserName;
	
	@FindBy(xpath="//input[@id='userPassword']/following-sibling::div/div")
	WebElement emptyPassword;
	
	public void loginToApplication(String emailaddress, String emailPassword) {
		userName.sendKeys(emailaddress);
		userPassword.sendKeys(emailPassword);
		loginButton.click();
	}
	
	public void launchUrl(String url) {
		driver.manage().window().maximize();
		driver.get(url);
		
	}
	
	public String errorInvalidUsernamePassword() {
		waitForWebElementToAppear(loginError);
		return loginError.getText();
	}
	
	public String errorEmptyUserName() {
		waitForWebElementToAppear(emptyUserName);
		return emptyUserName.getText();
	}
	
	public String errorEmptyPassword() {
		waitForWebElementToAppear(emptyPassword);
		return emptyPassword.getText();
	}
}
