package testComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import letShop.utilities.ExtentReport;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReport.getReportObject();
	ExtentTest test;
	ThreadLocal tl = new ThreadLocal();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		tl.set(test);
		test.log(Status.INFO, "Test Started - Inside onTestStart");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed - Inside onTestSuccess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Failed - Inside onTestFailure");
		takeScreenshot();
		test.addScreenCaptureFromPath("D:\\Playground\\Workspace\\LetShop\\screenshots\\LoginTest.png", "Login Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.FAIL, "Test Skipped - Inside onTestFailure");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		test.log(Status.PASS, "Test Passed - Inside onTestFailedButWithinSuccessPercentage");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		test.log(Status.FAIL, "Test Failed - Inside onTestFailedWithTimeout");
	}

	@Override
	public void onStart(ITestContext context) {
		// test.log(Status.INFO, "Test Started - Inside onStart");
	}

	@Override
	public void onFinish(ITestContext context) {
		test.log(Status.INFO, "Test Finished - Inside onFinish");
		extent.flush();
	}

}
