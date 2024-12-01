package com.automation.tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class RegisterNTITests extends BaseTests{
	
	@Parameters({"email","password"})

	@Test
	
	public void signUpAndGrant(@Optional String email,@Optional String password) {
		reportUtilities.createTestCase("Verify SignUp Function");
		reportUtilities.addTestLog(Status.INFO, "Performing log");
		
		url = configProperty.getProperty("signUpNTIUrl");
		email = configProperty.getProperty("email");
		cmnDriver.navigateToUrl(url);
		WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("input_3.3")));
		registerNTI.signUp(email, password);
		try {
			Thread.sleep(30000);// the sign up process takes about 10 seconds.
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		String expectedTitle = "Successful Registration";
		String actualTitle = cmnDriver.getTitleOfThePage();
		reportUtilities.addTestLog(Status.INFO, "Comparing expected and actual title");
		AssertJUnit.assertTrue(actualTitle.contains(expectedTitle));
		
		//navigate to the last step directly
		cmnDriver.navigateToUrl("https://training.nti.org.bb/coursera-courses/people-and-soft-skills-assessment/");
	
		WebDriverWait waitC = new WebDriverWait(driver, 10);
        waitC.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='View Course']")));
		registerNTI.grantMembership();
		String expectedTitleCoursera = "Skills for Global Success | Coursera";
		String actualTitleCoursera = cmnDriver.getTitleOfThePage();
		reportUtilities.addTestLog(Status.INFO, "Comparing expected and actual title");
		AssertJUnit.assertEquals(actualTitleCoursera, expectedTitleCoursera);
	}
}
