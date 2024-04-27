package deekshaRaiMaven.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import deekshaRaiMaven.AbstractComponentReusable.AbstractComponentReusable;

public class LandingPage extends AbstractComponentReusable{

		// main driver is in StandAloneTest.java,so we need to get that driver here
	    //Constructor-takes the same name as class name, it will be the first method to get execute in class
	
        WebDriver driver; //local variable called driver
        
        //* From child to parent-AbstractComponentReusable we can send variables by super()*//
   	
        
        public LandingPage(WebDriver driver)    //object created on StandAloneTest.java
        {	super(driver);
        	//initialization, it will run first
        	this.driver = driver; 
        	PageFactory.initElements(driver, this); //this refers to current class driver
        }   

		//WebElement userEmail = driver.findElement(By.id("userEmail"));
		
		//PageFactory(Design pattern), reduce the syntax of creating WebElement 
		
		@FindBy(id="userEmail")
		WebElement userEmail; //variable
		
		@FindBy(id="userPassword")
		WebElement userPassword; //variable
		
		@FindBy(id="login")
		WebElement submit; //variable
		
		@FindBy(css="div[aria-label='Incorrect email or password.']")
		WebElement errorMessage;
				
//div[@aria-label='Incorrect email or password.']
		
		//Action Method
		
		public ProductCatalogue loginApplication(String email,String password)
		{
			//PageObject should not hold any data, it should only focus on elements and objects
			userEmail.sendKeys(email); //don't hardcode it, get it from actual testcases
			userPassword.sendKeys(password);
			submit.click();
			//making object of class here (instead of writing in SubmitOrderTest bcz we know after LandingPage we will be redirected to ProductCatalogue page
			ProductCatalogue productCatalogue = new ProductCatalogue(driver);
			return productCatalogue;
			
		}
		
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
		
		//Action method to get URL , 
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
		
		
}
