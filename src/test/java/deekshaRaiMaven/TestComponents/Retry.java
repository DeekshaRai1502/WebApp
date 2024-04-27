package deekshaRaiMaven.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		//in case of failure after going to OnTestfailure block, test will come here to check if rerun is required 
		if(count<maxTry)
		{
			count++;
			return true;
		}
		
		return false;
	}

}
