package testingacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testingacademy.pageObjects.LandingPage;
import testingacademy.pageObjects.MyCart;
import testingacademy.pageObjects.OrdersPage;
import testingacademy.pageObjects.PaymentsPage;
import testingacademy.pageObjects.ProductPage;
import testingacademy.testComponesnts.BaseTest;
import testingacademy.testComponesnts.RetryFailedTestCases;

public class PageObjectTest extends BaseTest{
	//String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getData",groups={"smoke","regression"})
	public void orderProduct(HashMap<String, String> inputObject) throws InterruptedException, IOException {
		
		lp.loginToApplication(inputObject.get("email"), inputObject.get("password"));
		ProductPage pp = new ProductPage(driver);
		List<WebElement> prod = pp.getProductList();
		pp.getProductByName(inputObject.get("productName"));
		Thread.sleep(2000);
		MyCart mc = new MyCart(driver);
		//mc.checkOutFromMyCart(productName);
		Assert.assertTrue(mc.checkOutFromMyCart(inputObject.get("productName")));
		mc.checkOut();
		PaymentsPage ppage = new PaymentsPage(driver);
		ppage.countrySelection("India");
		ppage.creditCardNumberToEnter("1224 1234 1234 1223");
		ppage.creditCardExpiryToSelect("03", "03" ,"456", "Naveen K");
		ppage.applyCoupon("RS123");
		Assert.assertTrue(ppage.placeOrder().equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods = {"orderProduct"},retryAnalyzer = RetryFailedTestCases.class)
	public void verifyOrder(HashMap<String, String> inputObject) {
		lp.loginToApplication(inputObject.get("email"), inputObject.get("password"));
		OrdersPage op = new OrdersPage(driver);
		op.productNameCheck(inputObject.get("productName"));
	}
	
	@DataProvider 
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>>data = getData(System.getProperty("user.dir")+"\\src\\test\\java\\testingacademy\\data\\input.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};

	}
	

}
