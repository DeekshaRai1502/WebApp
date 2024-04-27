package deekshaRaiMaven.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import deekshaRaiMaven.TestComponents.BaseTest;
import deekshaRaiMaven.pageObjects.CheckoutPage;
import deekshaRaiMaven.pageObjects.ConfirmationPage;
import deekshaRaiMaven.pageObjects.LandingPage;
import deekshaRaiMaven.pageObjects.ProductCatalogue;
import deekshaRaiMaven.pageObjects.cartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImpl extends BaseTest {
	
	public LandingPage landingPage;           //declaring landingPage object here bcz launchApplication() returns landingPage 
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();   //catching landingPage object
		//code
	}
	
	@Given("^Logged in with username (.+) and password (.+)$") //<name> and <password> are parameterised and value is coming at runtime//(.+) is the regular expression
	public void Logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingpage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		cartPage cartpage = productCatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);  //if Assert.assertTrue(True),, test will paass
		CheckoutPage checkoutPage = cartpage.goToCheckout(); 
		confirmationPage = checkoutPage.submitOrder();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page
	@Then("{string} message is displayed on Confirmation page")
	public void message_is_displayed_on_Confirmation_page(String string)
	{
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertEquals(string, landingpage.getErrorMessage());
	}
	
}
