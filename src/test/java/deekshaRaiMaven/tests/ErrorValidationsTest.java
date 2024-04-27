
//REPLICA OF StandAloneTest.JAVA

package deekshaRaiMaven.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import deekshaRaiMaven.TestComponents.BaseTest;
import deekshaRaiMaven.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test(retryAnalyzer= Retry.class)
	public void ErrorTest() throws IOException
	{
		
		//LandingPage landingpage = launchApplication();  //creating object for LandingPage in BaseTest.java class
		//// modified it by creating @BeforeMethod in BaseTest class - launchApplication() method
		
		//creating object for LandingPage.java //used above way to optimise the code
		//LandingPage landingpage = new LandingPage(driver);
		//landingpage.goTo();
		
		//landingpage.loginApplication("admin@gmail.com", "Password5"); //bcz we have created object for ProductCatalogue page in LandingPage page so next line will work now
		landingpage.loginApplication("admiyuunn@gmail.com", "Pafhjassword5");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

		
		
		

}
}
