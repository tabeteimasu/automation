package com.automation.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class SignInNSentEmailTests extends BaseTests{
	//pass parameter in the testxml as parameter for the test method
	//if no parameter for the test method, the @parameter will be omitted.
	@Parameters({"email"}) 

	@Test
	
	public void verifySignInNSentEmail() {

		reportUtilities.createTestCase("Verify SignIn Function");
		reportUtilities.addTestLog(Status.INFO, "Performing log");
		
		url = configProperty.getProperty("signInHotmail");
		String email = configProperty.getProperty("email");
		cmnDriver.navigateToUrl(url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		signInNSentEmailPage.signInNSent(email);

	}
}
