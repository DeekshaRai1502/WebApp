package deekshaRaiMaven.TestComponents;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import deekshaRaiMaven.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;//creating object for LandingPage
	
	public WebDriver initializeDriver() throws IOException
	{
		//properties class  -  it can read Global properties
		//We are doing this to fetch GlobalData.properties (to know which browser to invoke during runtime)
		//using .properties extension in file name, so that by using properties class in java we can parse the file and extract all global parameter values
		Properties prop = new Properties();
		//Converting File into input stream object
		FileInputStream fis = new FileInputStream (System.getProperty("user.dir")+"//src//main//java//deekshaRaiMaven//resources//GlobalData.properties");
		prop.load(fis);
	////String browserName = prop.getProperty("browser");
    //if we need to send browser details from maven(CMD)//below is JAVA Ternary Operator
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser"); //sending browser property from maven(CMD)
		
		
		if(browserName.contains("chrome"))
		{
			//to run test in headless mode
			ChromeOptions options = new ChromeOptions();
			// Webdriver Manager to invoke chrome browser without chrome driver
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless"))
		{
			options.addArguments("headless");
		}
		
	    driver = new ChromeDriver(options);//removed WebDriver bcz we have made it globally
		driver.manage().window().setSize(new Dimension(1440,900)); // if needed to run in full screen
	}
		else if (browserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C://Users//Anuj//Desktop//gecko//geckodriver.exe");
			driver= new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("Edge"))
		{
			//Edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod
	public  LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		//LandingPage landingpage = new LandingPage(driver); //creating object for LandingPage
		landingpage = new LandingPage(driver); //already have public LandingPage landingpage at top
		landingpage.goTo();
		return landingpage;   
	}

	@AfterMethod
	public void closebrowser()
	{
		driver.close();
	}
	
	//Extent Reports - HTML Reports
	 /////public String getScreenshot(String testCaseName) throws IOException {
		//we need to give life to driver, sending as argument WebDriver driver first,then will call it from Listeners class
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver; //casting driver to take screenshots
		File source = ts.getScreenshotAs(OutputType.FILE); //photo clicked by driver
		File output = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"); //File that will be saved on the system
		FileUtils.copyFile(source, output); //Utility copying file from driver to system location/output
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"; //returning the location of the saved image
	}

	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8); // Reading Json Data to String and storing it in variable jsonContent.
		
		//String to HashMap - Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper(); // created new mapper provided by jackson databind to covert string data to hashmap
		
		// Converted string jsonContent to a list of hashmap using jackson databind readValue method/utility and storing it in variable data
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){}); 
		
		return data; //returning the list of hashmap
		
		
	}
	
	
	
	

	}
	
	
	
