package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber -> TestNG or JUnit runner, here we are using TestNG runner
@CucumberOptions(features="src/test/java/cucumber",glue="deekshaRaiMaven.stepDefinitions"
,monochrome=true, tags = "@ErrorValidation", plugin= {"html:target/cucumber.html"})//feature file path,step definition path for mapping
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	//AbstractTestNGCucumberTests to understand and run cucumber code 
	//information about running TestNG feature file
	
}
