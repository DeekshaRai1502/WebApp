package deekshaRaiMaven.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import deekshaRaiMaven.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{
	
	//////////////we will give details of this class to  testng.XML
	
    ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReportObject(); // return type is ExtentReports is ExtentReportNG class
	//When we are running multiple test parallel in testng.xml file, it can override and we may get distorted data or result description, to overcome this we need below step
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName());//result.getMethod().getMethodName() will get us the name of the test case
		extentTest.set(test);//it will assign unique thread id so that it cannot override
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		////test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed");//extentTest.get() is to retrieve unique thread details
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		//test.log(Status.FAIL, "Test Passed");
		/////test.fail(result.getThrowable()); // it will print error message in report
		extentTest.get().fail(result.getThrowable()); // it will print error message in report//extentTest.get() is to retrieve unique thread details
		//now our main goal is to attach SCREENSHOT to report
		//i)-take screenshot ii)-attach to report
		
		//giving life to driver
		//driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	    /////after try catch
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		//above description, first it will get the class what this test is referring(from testng.xml),then it will go to actual class(from .java class),then get driver from there,we are using class here bcz files are part of class not part of method, 
		} catch (Exception e1) { //generic exception
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		//below will return the path of where exactly ur screenshot is stored in your local system(check in BaseTest)
		/////String filePath = getScreenshot(result.getMethod().getMethodName()); //we are sending test case name in getScreenshot method on which we are sending screenshot(in BaseTest-public String getScreenshot(String testCaseName))
		/////test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());//first argument is where file is stored and second is how to show in report
		
		/////after try catch
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);//giving life to driver in Base Test class method
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //we are sending test case name in getScreenshot method on which we are sending screenshot(in BaseTest-public String getScreenshot(String testCaseName))
		/////test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());//first argument is where file is stored and second is how to show in report
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());//first argument is where file is stored and second is how to show in report//extentTest.get() is to retrieve unique
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush(); //without this report will not be generated to display on screen, it will just make entries
	}

}
