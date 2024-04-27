package deekshaRaiMaven.AbstractComponentReusable;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import deekshaRaiMaven.pageObjects.cartPage;

public class AbstractComponentReusable {
	 
	/* Enabling Driver 
	 * This class is parent class of all child pageObject Classes
	 * From child to parent we can send variables by super()
	 * Constructor in this class to catch driver 
	 * bcz only constructor can catch variables
	 */
    WebDriver driver;
	public AbstractComponentReusable(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this); //this refers to current class driver
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;  //public goToCartPage()
	
	public void waitForElementToAppear(By findBy)    //By Locator,return type is by, //findBy is variable here
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  //Explicit wait
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));  //By Locator(findBy)


	// we replaced By.cssSelector(".mb-3")))by variable findBy bc we cannot hardcode it,should come as argument, 
	//Here By.cssSelector(".mb-3") is not a web Element bcz WebElement starts with driver.findElement.....
	}
	
	public void waitForElementToDisAppear(WebElement ele)    //By Locator,return type is by, //findBy is variable here
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  //Explicit wait
		 wait.until(ExpectedConditions.invisibilityOf(ele)); 
	}
	
	public void waitForWebElementToAppear(WebElement findBy)    //for ErrorValidations class
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));  //Explicit wait
	wait.until(ExpectedConditions.visibilityOf(findBy));
    }

	
	public cartPage goToCartPage()
	{
		cartHeader.click();
		cartPage cartpage = new cartPage(driver); //making object of class here (instead of writing in SubmitOrderTest bcz we know after goToCartPage we will be redirected to cartPage page
		return cartpage;
	}

}
	
	
	
	
	
	
