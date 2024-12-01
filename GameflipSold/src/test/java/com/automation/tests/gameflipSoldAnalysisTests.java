package com.automation.tests;

import commonLibs.utilities.ExcelUtilities;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class gameflipSoldAnalysisTests extends BaseTests{


	@Test
	
	public void analyse() {

		reportUtilities.createTestCase("Analyse sold items");
		reportUtilities.addTestLog(Status.INFO, "Performing log");
		
		url = configProperty.getProperty("gameflipURL");

		cmnDriver.navigateToUrl(url);
		
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
		gameflipSoldItemsPage.printItemsInDescendingOrder();
               
	}
}
