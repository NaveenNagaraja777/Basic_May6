package testingacademy.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ERDemo {
	ExtentReports rep;

	//Extent Reports and Extent Spark Reporter
	@BeforeTest
	public void confi() {
		String pathToCreateOutputReportFile = System.getProperty("user.dir")+"\\reports\\index.html";
		//String pathToCreateOutputReportFile = "C:/Users/mural/eclipse-workspace/SeleniumFWDesign/reports/index1.html";
		ExtentSparkReporter esr = new ExtentSparkReporter(pathToCreateOutputReportFile);
		esr.config().setReportName("Demo Extent Reprorts");
		esr.config().setDocumentTitle("Demo Of ER");
		rep = new ExtentReports();
		rep.attachReporter(esr);
		rep.setSystemInfo("Tester", "Chrome");
	}
	@Test
	public void erDemo() {
		ExtentTest test = rep.createTest("Its for Demo");
		WebDriver driver;
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		driver.close();
		rep.flush();
		
	}
}
