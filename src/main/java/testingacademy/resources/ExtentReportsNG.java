package testingacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

	public static ExtentReports getExtentReportsObject() {
		String pathToCreateOutputReportFile = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter esr = new ExtentSparkReporter(pathToCreateOutputReportFile);
		esr.config().setReportName("Demo Extent Reprorts");
		esr.config().setDocumentTitle("Demo Of ER");
		ExtentReports rep = new ExtentReports();
		rep.attachReporter(esr);
		rep.setSystemInfo("Tester", "Chrome");
		return rep;
	}
}
