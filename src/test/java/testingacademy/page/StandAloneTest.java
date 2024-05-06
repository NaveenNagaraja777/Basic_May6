package testingacademy.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("naveen@123.com");
		driver.findElement(By.id("userPassword")).sendKeys("Brave@2024");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".col-sm-10"))));
		List<WebElement> listofItyems = driver.findElements(By.cssSelector(".col-sm-10"));
		
		for(int i=0; i<listofItyems.size(); i++) {
			
			//System.out.println(listofItyems.get(i).findElement(By.cssSelector("b")).getText());
			if(listofItyems.get(i).findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)) {
				listofItyems.get(i).findElement(By.cssSelector("button:last-of-type")).click();
			}
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul/li[4]/button")).click();
		Thread.sleep(3000);
	List<WebElement> listOfCartItems = driver.findElements(By.cssSelector(".infoWrap"));
		
		for(int i=0; i<listOfCartItems.size(); i++) {
			if(listOfCartItems.get(i).findElement(By.cssSelector("h3")).getText().equalsIgnoreCase("ADIDAS ORIGINAL")) {
				Assert.assertTrue(true);
			}
		}
		driver.findElement(By.cssSelector(".subtotal button")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		//driver.findElements(By.cssSelector(".ta-item")).click();
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item"))));
		  List<WebElement> countryList =
		  driver.findElements(By.cssSelector(".ta-item")); 
		  for(int i =0; i<countryList.size();i++) {
		  if(countryList.get(i).getText().
		  equalsIgnoreCase("India")) {
		  countryList.get(i).click(); } }
		 
		
		
		  WebElement cardNo =
		  driver.findElement(By.cssSelector(".field .text-validated")); cardNo.clear();
		  cardNo.sendKeys("1234 5678 9123 4567");
		  WebElement month = driver.findElement(By.xpath("//select[@class='input ddl'][1]"));
		  Select monthSelect = new Select(month);
		  monthSelect.selectByVisibleText("03"); 
		  WebElement date = driver.findElement(By.xpath("//select[@class='input ddl'][2]"));
		  Select dateSelect = new Select(date);
		  dateSelect.selectByVisibleText("12");
		  driver.findElement(By.cssSelector(".field.small .input.txt")).sendKeys("123"); 
		  driver.findElement(By.xpath("//div[@class='row'][3]/div/input")).sendKeys("Ganesh"); 
		  driver.findElement(By.name("coupon")).sendKeys("rs123");
		  driver.findElement(By.cssSelector(".btn-primary")).click();
		  Thread.sleep(5000);
		  driver.findElement(By.cssSelector(".action__submit")).click();
		  Assert.assertTrue(driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order."));
		  driver.close();

	}

}
