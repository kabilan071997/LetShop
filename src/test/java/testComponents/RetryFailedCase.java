package testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedCase implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
