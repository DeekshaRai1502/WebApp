
//REPLICA OF StandAloneTest.JAVA

package deekshaRaiMaven.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import deekshaRaiMaven.TestComponents.BaseTest;
import deekshaRaiMaven.pageObjects.CheckoutPage;
import deekshaRaiMaven.pageObjects.ConfirmationPage;
import deekshaRaiMaven.pageObjects.ProductCatalogue;
import deekshaRaiMaven.pageObjects.cartPage;


public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	//public void SubmitOrder(String email, String password, String requiredProduct) throws IOException
	public void SubmitOrder(HashMap<String,String> input) throws IOException
	{
		String requiredProduct = input.get("requiredProduct");
		//LandingPage landingpage = launchApplication();  //creating object for LandingPage in BaseTest.java class
		//// modified it by creating @BeforeMethod in BaseTest class - launchApplication() method
		
		//creating object for LandingPage.java //used above way to optimise the code
		//LandingPage landingpage = new LandingPage(driver);
		//landingpage.goTo();
		
		//landingpage.loginApplication("admin@gmail.com", "Password5"); //bcz we have created object for ProductCatalogue page in LandingPage page so next line will work now
		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		
		//loginApplication
		//ProductCatalogue productCatalogue = new ProductCatalogue(driver);//now we don't need this line bcz we now we have created object in LandingPage class itself
		List<WebElement> products = productCatalogue.getProductList();
		
		//Finding product by Product Name
		//String requiredProduct = "ZARA JACKET";//captured it in data provider
		productCatalogue.addProductToCart(requiredProduct);
		
		//apper of toast message and disappear of spinner and then click to add cart
		cartPage cartpage = productCatalogue.goToCartPage(); //Code is in Abstract...but we can fetch from productCatalogue bcz of inheritence property//also created object for cartPage
		
		//just to check requiredProduct = "ZARA JACKET" is added in cart or not, and .anyMatch will give true or false so it will return boolean value
		//cartPage cartpage = new cartPage(driver); //now we don't need this line bcz we now we have created object in AbstractComponentReusage - class goToCartPage() method itself
		Boolean match = cartpage.verifyProductDisplay(requiredProduct);
		Assert.assertTrue(match);  //if Assert.assertTrue(True),, test will paass
		CheckoutPage checkoutPage = cartpage.goToCheckout(); 
		//CheckoutPage checkoutPage = new CheckoutPage(driver);//now we don't need this line bcz we now we have created object in goToCheckout() method itself
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder(); //We already made Object in checkoutPage, this is how we conclude Object creation here
		//Verify Confirm Message
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
		
		
		
		//if we need to check zara coat 3 is there in orders section or not
		
//		@Test(dependsOnMethods= {"SubmitOrder"})
//		
//		public void OrderHistoryTest()
//		{
//			//zaracoat3
//		}
	
		@DataProvider
		public Object[][] getData() throws IOException
		{
			//if we want to run test with two diff data sets
			//creating two dimentional array which accepts multiple sets
			//new Object [][] is the syntax for creating two dimentional array
			//{}-- defines multiple data sets
			
			
			
			//Object is parent data type or generic data type
			///return new Object[][] {{"admin@gmail.com","Password5","ZARA JACKET"},{"anshika@gmail.com","Iamking@000","ADIDAS"} }; //it will run 2 times with 2 diff data sets
			
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("email", "admin@gmail.com");
//			map.put("password", "Password5");
//			map.put( "requiredProduct", "ZARA JACKET");
//			
//			map1 = new HashMap<String,String>();
//			map1.put("email", "anshika@gmail.com");
//			map1.put("password", "Iamking@000");
//			map1.put("requiredProduct", "ADIDAS ORIGINAL");
			//return new Object[][] {{map},{map1} };
//				
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//deekshaRaiMaven//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)} }; //it will run 2 times with 2 diff data sets
		}
		
		
		
		


}
