package testingacademy.testComponesnts;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer{
	
	int count = 0;
	int maxAttemptsToRetry = 1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxAttemptsToRetry) {
			count++;
			return true;
		}
		return false;
	}

}
