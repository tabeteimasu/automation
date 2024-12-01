package com.automation.tests;

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

public class SignInAndGrantNTITests extends BaseTests{
	
	@Parameters({"email","password"})

	@Test
	
	public void signInAndGrant(String email,String password) {
		reportUtilities.createTestCase("Verify SignIn Function");
		reportUtilities.addTestLog(Status.INFO, "Performing log");
		
		url = configProperty.getProperty("signInNTIUrl");
		email = configProperty.getProperty("email");
		cmnDriver.navigateToUrl(url);

		try {
		    System.out.println("Starting to sleep...");
		    Thread.sleep(6000); // Sleep for 6 seconds
		    System.out.println("Finished sleeping.");
		} catch (InterruptedException e) {
		    System.out.println("Sleep was interrupted.");
		    e.printStackTrace();
		}
		
		registerNTI.signInMainPage(email, password);
		try {
		    System.out.println("Starting to sleep...");
		    Thread.sleep(3000); // Sleep for 6 seconds
		    System.out.println("Finished sleeping.");
		} catch (InterruptedException e) {
		    System.out.println("Sleep was interrupted.");
		    e.printStackTrace();
		}
		
		//verification for individual sign in page
//		String expectedTitle = "My Dashboard";
//		String actualTitle = cmnDriver.getDriver().findElement(
//				By.xpath("(//span[@class='elementor-button-text'])[3]")).getText();
//		reportUtilities.addTestLog(Status.INFO, "Comparing expected and actual title");
//		AssertJUnit.assertEquals(expectedTitle,actualTitle);
		
		//verification for signing in on main page
		String expectedTitle = "Welcome,";
		String actualTitle = cmnDriver.getDriver().findElement(
				By.xpath("//span[@class='bb-dash__intro']")).getText();
		reportUtilities.addTestLog(Status.INFO, "Comparing expected and actual title");
		AssertJUnit.assertEquals(expectedTitle,actualTitle);
		
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
