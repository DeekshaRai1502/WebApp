package deekshaRaiMaven.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import deekshaRaiMaven.AbstractComponentReusable.AbstractComponentReusable;

public class CheckoutPage extends AbstractComponentReusable {

	    WebDriver driver; 
        
        public CheckoutPage(WebDriver driver)    
        {
        	super(driver);
        	this.driver = driver; 
        	PageFactory.initElements(driver, this); 
        }   
		
		
		@FindBy(css="[placeholder='Select Country']")
		WebElement Country;
		
		@FindBy(xpath = "//button[contains(@class,'ta-item')] [2]")
		WebElement SelectCountry;
	
		
		@FindBy(css=".action__submit")
		WebElement Submit;
		
		By result = By.cssSelector(".ta-results");
		
	public void selectCountry(String CountryName)
	{

		Actions a = new Actions(driver);
		a.sendKeys(Country, CountryName).build().perform();
		waitForElementToAppear(result);
         SelectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		Submit.click();
		return new ConfirmationPage(driver);  //not creating Object in Class file, instead we create it here and return type will be next page(ConfirmationPage) instead of void
		//Here instead of making Object in Class file, when we are sure what next page will be(ConfirmationPage), then we create Object in this way
	}

		
		
		
		
		
		
		
			
		
		
}
