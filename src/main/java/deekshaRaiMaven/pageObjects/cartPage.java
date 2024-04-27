package deekshaRaiMaven.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import deekshaRaiMaven.AbstractComponentReusable.AbstractComponentReusable;

public class cartPage extends AbstractComponentReusable {

	    WebDriver driver; 
        
        public cartPage(WebDriver driver)    
        {	//initialization
        	super(driver);
        	this.driver = driver; 
        	PageFactory.initElements(driver, this); 
        }   
		
		@FindBy(css=".cartSection h3")
		List<WebElement> cartProducts;
		
		@FindBy(css=".totalRow button")
		WebElement checkoutElement;
		
	public Boolean verifyProductDisplay(String requiredProduct)
	{
		Boolean match = cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(requiredProduct));
		return match;
	}
		
	public CheckoutPage goToCheckout()
	{
		checkoutElement.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver); //making object of class here (instead of writing in SubmitOrderTest bcz we know after goToCheckout() we will be redirected to CheckoutPage page
		return checkoutPage;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
		
		
}
