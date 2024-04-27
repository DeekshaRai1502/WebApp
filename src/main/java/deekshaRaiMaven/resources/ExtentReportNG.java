package deekshaRaiMaven.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject()//by making it static we can directy do classname.method without creating object(ExtentReportNG.getReportObject())
	{// ExtentSparkReporter, ExtentReport
	
			//this class expects a class where its report should be created
			String path = System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			//ExtentSparkReporter is responsible to create a html file and to do some configuration
			//it's a helper class for ExtentReports main class 
			reporter.config().setReportName("Web Automation Result");
			reporter.config().setDocumentTitle("Test Results");
			
			//ExtentReports is the main class,it is responsible to drive all our reporting execution
			//it is responsible to create and consolidate all our test execution
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Deeksha Rai");
			return extent;
			

}
	
}
