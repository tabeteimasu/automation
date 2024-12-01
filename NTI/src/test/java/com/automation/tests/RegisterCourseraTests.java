package com.automation.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class RegisterCourseraTests extends BaseTests{
	//pass parameter in the testxml as parameter for the test method
	//if no parameter for the test method, the @parameter will be omitted.
	@Parameters({"email"}) 

	@Test
	
	public void verifySignUp() {

		reportUtilities.createTestCase("Verify SignUp Function");
		reportUtilities.addTestLog(Status.INFO, "Performing log");
		
		url = configProperty.getProperty("signUpCourseraUrl");
		String email = configProperty.getProperty("email");
		cmnDriver.navigateToUrl(url);
		registerCoursera.signUp(email);
		
		String expectedTitle = "Successful Registration &#x2d; NTI Training";
		String actualTitle = cmnDriver.getTitleOfThePage();
		
		reportUtilities.addTestLog(Status.INFO, "Comparing expected and actual title");
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
	}
}
