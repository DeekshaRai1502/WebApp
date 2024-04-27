package deekshaRaiMaven.ExtentReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportDemo {
	ExtentReports extent; //declaring variable at global level
	
	@BeforeTest
	public void config()
	{
	// ExtentReports, ExtentSparkReporter
		
		//this class expects a class where its report should be created
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		//ExtentSparkReporter is responsible to create a html file and to do some configuration
		//it's a helper class for ExtentReports main class 
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Results");
		
		//ExtentReports is the main class,it is responsible to drive all our reporting execution
		//it is responsible to create and consolidate all our test execution
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Deeksha Rai");
	   
   
		
	}
	
	@Test
	public void initialDemo()
	
	{
		//ExtentTest test = extent.createTest("Initial Demo");  // create one new test in the reporting file
		ExtentTest test = extent.createTest("Initial Demo");  //test is the object which comes from ExtentTest class
		//whenever we create test the extent report will create one object which is unique to test method 
		//"test" object will be responsible to listen and report all the happenings back to the extent report
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();
		//if we want to tweak reporting in this test case we can do it by using "test"
		test.fail("Result do not match"); //it will help to fail the test
		//when we run now test will fail because we are failing our ExtentReports Object
		
		extent.flush();
	}
	
	

}
