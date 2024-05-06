package testingacademy.testComponesnts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import testingacademy.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	public WebDriver initalizeDriver() throws IOException {
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\testingacademy\\resources\\GlobalData.properties");
		//FileInputStream fis = new FileInputStream("C:\\Users\\mural\\eclipse-workspace\\SeleniumFWDesign\\src\\main\\java\\testingacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		//Java Ternary Opertaor for If else logic
		String browser =  System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser"); 
		//String browser = prop.getProperty("browser"); 	 		
		if(browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browser.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
	;
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		return driver;
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initalizeDriver();
		lp = new LandingPage(driver);
		lp.launchUrl("https://rahulshettyacademy.com/client");
		return lp;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBroser() {
		driver.close();
	}
	// Traditional method to pass the data
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"naveen@123.com" , "Brave@2024" , "ZARA COAT 3"},{"naveen@123.com" ,
	 * "Brave@2024" , "ADIDAS ORIGINAL"}}; }
	 */
	
	//Hashmap methods to pass the data
	
	/*
	 * @DataProvider public Object[][] getData() { HashMap<String, String> hm = new
	 * HashMap<String, String>(); hm.put("email", "naveen@123.com");
	 * hm.put("password", "Brave@2024"); hm.put("productName", "ZARA COAT 3");
	 * HashMap<String, String> hm1 = new HashMap<String, String>(); hm1.put("email",
	 * "naveen@123.com"); hm1.put("password", "Brave@2024"); hm1.put("productName",
	 * "ADIDAS ORIGINAL"); return new Object[][] {{hm},{hm1}}; }
	 */
	
	//Passing data through JSON file

	public List<HashMap<String, String>> getData(String filePath) throws IOException {
		// Read Json file
		String jsoncontent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//Convert String to Hashmap using Jackson Databind
		
		ObjectMapper om = new ObjectMapper();
		List<HashMap<String, String>> data1 = om.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data1;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		File destinationfile = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(sourceFile, destinationfile);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
}

