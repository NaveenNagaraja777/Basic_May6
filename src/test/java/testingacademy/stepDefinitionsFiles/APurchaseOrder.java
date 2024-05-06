package testingacademy.stepDefinitionsFiles;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testingacademy.pageObjects.LandingPage;
import testingacademy.pageObjects.MyCart;
import testingacademy.pageObjects.PaymentsPage;
import testingacademy.pageObjects.ProductPage;
import testingacademy.testComponesnts.BaseTest;

public class APurchaseOrder extends BaseTest{
	
	public LandingPage landing;
	public PaymentsPage ppage;
	@Given ("I have url to login to ecommerse website")
	public void i_have_url_to_login_to_ecommerse_website() throws IOException {
		landing= launchApplication();
	}
	@Given("^I have logged in with valid (.+) and (.+)$")
	public void i_have_logged_in_with_valid_username_and_password(String username, String password ){
		landing.loginToApplication(username, password);
		
	}
	
	@When("^I add the (.+)$")
	public void i_add_the_product(String product) throws InterruptedException {
		ProductPage pp = new ProductPage(driver);
		List<WebElement> prod = pp.getProductList();
		pp.getProductByName(product);
		Thread.sleep(2000);
	}
	
	 @And ("^Checkout the (.+) to submit the order$")
	 public void  checkout_the_product_to_submit_the_order(String product) {
		 MyCart mc = new MyCart(driver);
		 Assert.assertTrue(mc.checkOutFromMyCart(product));
			mc.checkOut();
			PaymentsPage ppage = new PaymentsPage(driver);
			ppage.countrySelection("India");
			ppage.creditCardNumberToEnter("1224 1234 1234 1223");
			ppage.creditCardExpiryToSelect("03", "03" ,"456", "Naveen K");
			ppage.applyCoupon("RS123");
	 }
	 
	 @Then ("I see {string} success message")
	 public void i_see_success_message(String message) {
		 PaymentsPage ppage = new PaymentsPage(driver);
		 Assert.assertTrue(ppage.placeOrder().equalsIgnoreCase(message));
		 driver.close();
	 }
	 
	 @Given ("I have logged in with {string} and {string}")
		public void i_have_logged_in_with_invalid_credentials(String string1, String string2) {
			lp.loginToApplication(string1, string2);
		}
		
	 @Then ("I should see {string} message")
	    public void i_should_see_validation_message(String message) {
	    	 Assert.assertEquals(message,lp.errorInvalidUsernamePassword());
	    	 driver.close();
	    }

}
