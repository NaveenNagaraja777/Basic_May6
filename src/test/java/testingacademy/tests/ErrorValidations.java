package testingacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testingacademy.pageObjects.LandingPage;
import testingacademy.pageObjects.MyCart;
import testingacademy.pageObjects.PaymentsPage;
import testingacademy.pageObjects.ProductPage;
import testingacademy.testComponesnts.BaseTest;

public class ErrorValidations extends BaseTest{
	
	  @Test(groups={"smoke"})
	  public void invalidEMailOrPassword() throws InterruptedException,
	  IOException { lp.loginToApplication("naveen32@123.com", "Brave@2024");
	  Assert.assertEquals("Incorrect email or password",
	  lp.errorInvalidUsernamePassword());
	  
	  }
	 
	
	@Test
	public void emptyUserNameAndPassword() {
		lp.loginToApplication("", "");
		Assert.assertEquals("*Email is required", lp.errorEmptyUserName());
		Assert.assertEquals("*Password is required", lp.errorEmptyPassword());
	}
	
	@Test
	public void emptyUserNameAndValidPassword() {
		lp.loginToApplication("", "Brave@2024");
		Assert.assertEquals("*Email is required", lp.errorEmptyUserName());
	}
	
	@Test
	public void validUserNameAndEmptyPassword() {
		lp.loginToApplication("naveen@123.com", "");
		Assert.assertEquals("*Password is required", lp.errorEmptyPassword());
	}
	
	@Test
	public void invalidUserNameAndValidPassword() {
		lp.loginToApplication("sddds", "Brave@2024");
		Assert.assertEquals("*Enter Valid Email", lp.errorEmptyUserName());
	}
	
}
