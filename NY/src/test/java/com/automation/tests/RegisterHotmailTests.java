package com.automation.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class RegisterHotmailTests extends BaseTests{
	//pass parameter in the testxml as parameter for the test method
	//if no parameter for the test method, the @parameter will be omitted.
	@Parameters({"email"}) 

	@Test
	
	public void verifySignUp() {

		reportUtilities.createTestCase("Verify SignUp Function");
		reportUtilities.addTestLog(Status.INFO, "Performing log");
		
		url = configProperty.getProperty("signUpHotmail");
		String email = configProperty.getProperty("email").split("@")[0];
		cmnDriver.navigateToUrl(url);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		registerHotmail.signUp(email);

	}
}
