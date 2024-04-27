package deekshaRaiMaven.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




import deekshaRaiMaven.AbstractComponentReusable.AbstractComponentReusable;

public class ConfirmationPage  extends AbstractComponentReusable {

    WebDriver driver; 
    
    public ConfirmationPage(WebDriver driver)    
    {
    	super(driver);
    	this.driver = driver; 
    	PageFactory.initElements(driver, this); 
    }   
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;

	public String getConfirmMessage()
	{
	  return confirmMessage.getText();
	}
	
	
}


//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));