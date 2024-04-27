package deekshaRaiMaven.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import deekshaRaiMaven.AbstractComponentReusable.AbstractComponentReusable;

public class ProductCatalogue extends AbstractComponentReusable {

		// main driver is in StandAloneTest.java,so we need to get that driver here
	    //Constructor-takes the same name as class name, it will be the first method to get execute in class
	
        WebDriver driver; //local variable called driver
        
        public ProductCatalogue(WebDriver driver)    //object created on StandAloneTest.java
        {	//initialization
        	super(driver);
        	this.driver = driver; 
        	PageFactory.initElements(driver, this); //this refers to current class driver
        }   
		
		//PageFactory(Design pattern), reduce the syntax of creating WebElement 
        //List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		@FindBy(css=".mb-3")
		List<WebElement> products; //variable  //List<WebElement> instead of WebElement for findElementSSSSS
		
		@FindBy(css=".ng-animating") ////***ng-animating is the class for loading page appearing during add to cart(we need to ask developer if we don't get html code)
		WebElement spinner; //for waitForElementToDisAppear
		
		//pageFactory is only for driver.find element constructor
		//for By element 
		By productsBy = By.cssSelector(".mb-3");  //waitForElementToAppear
		By addToCart = By.cssSelector(".card-body button:last-of-type"); //addToCart is variable
		By toastMessage = By.cssSelector("#toast-container");
		
		//Action Method to get the product list
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
		
	  //Action Method to get WebElement of the product
	public WebElement getProductByName(String requiredProduct)
	{
		WebElement prod = getProductList().stream().filter(product->     //getProductByName(requiredProduct)
		product.findElement(By.cssSelector("b")).getText().equals(requiredProduct)).findFirst().orElse(null);
		return prod;
	}	
		
	//Action Method to add product to cart
		public void addProductToCart(String requiredProduct)
		{
			WebElement productName =  getProductByName(requiredProduct);  //Above method name - getProductByName(requiredProduct)
					productName.findElement(addToCart).click(); 
			//We cannot apply pagefactory to WebElement.findElement(only to driver.findElement) that is why we did By addToCart
					waitForElementToAppear(toastMessage);
					waitForElementToDisAppear(spinner);
					
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
		
		
}
