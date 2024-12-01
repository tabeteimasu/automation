package com.automation.pages;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import commonLibs.utilities.ExcelUtilities;
import commonLibs.implementationn.GameflipItemEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GameflipSoldItemsPage extends BasePage{
	
	
//	@FindBy(xpath = "//*[@class='pr-4']")
//	private List<WebElement> items;
//
//	@FindBy(xpath = "//*[@class='money']")
//	private List<WebElement> prices;
	
	
	@FindBy(xpath = "//a[text()='Next']")
	private WebElement NextButton;
	
	public GameflipSoldItemsPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public void printItemsInDescendingOrder() {
	    List<GameflipItemEntry> itemPriceList = new ArrayList<>();
	    List<WebElement> items;
	    List<WebElement> prices;

	    while (true) {
	        try {
	            // Fetch items and prices
	            items = driver.findElements(By.xpath("//*[@class='pr-4']"));
	            prices = driver.findElements(By.xpath("//*[@class='money']"));

	            // Check if either list is empty
	            if (items.isEmpty() || prices.isEmpty()) {
	                System.out.println("No more items or prices found. Exiting.");
	                break;
	            }

	            // Populate the list with items and their prices
	            for (int i = 0; i < Math.min(items.size(), prices.size()); i++) {
	                String itemText = items.get(i).getText();
	                String priceText = prices.get(i).getText().replace("$", ""); // Remove dollar sign for parsing
	                double priceValue = Double.parseDouble(priceText); // Parse price to double

	                // Check if the item with the same name and price already exists
	                boolean found = false;
	                for (GameflipItemEntry entry : itemPriceList) {
	                    if (entry.getItemName().equals(itemText) && entry.getPrice() == priceValue) {
	                        entry.incrementQuantity(); // Increment quantity if found
	                        found = true;
	                        break;
	                    }
	                }
	                // If not found, add a new item entry
	                if (!found) {
	                    itemPriceList.add(new GameflipItemEntry(itemText, priceValue));
	                }
	            }

	            // Click the Next button and wait for the new page to load
	            elementControl.clickElement(NextButton);
	            WebDriverWait wait = new WebDriverWait(driver, 10);
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Previous']")));
	            Thread.sleep(5000);
	        } catch (StaleElementReferenceException e) {
	            System.out.println("Stale element encountered, re-fetching items and prices: " + e.getMessage());
	            // Re-fetch items and prices after the exception
	            continue; // Go back to the start of the loop to retry fetching
	        } catch (NoSuchElementException e) {
	            System.out.println("Exception while processing: " + e.getMessage());
	            break; // Exit on other exceptions
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    // Sort the list in descending order by total price (price * quantity)
	    itemPriceList.sort((entry1, entry2) -> Double.compare(entry2.getTotalPrice(), entry1.getTotalPrice()));

	    double totalPrice = 0;
	    // Print the sorted items
	    System.out.println("______________________________________");
	    for (GameflipItemEntry entry : itemPriceList) {
	        System.out.printf("%s: $%.2f (Quantity: %d, Total: $%.2f)%n",
	                          entry.getItemName(), entry.getPrice(), entry.getQuantity(), entry.getTotalPrice());
	        totalPrice += entry.getTotalPrice();
	    }
	    // Print the total price
	    System.out.printf("Total Price: $%.2f%n", totalPrice);
	    System.out.println("______________________________________");

	    // Output to Excel
	    try {
	        ExcelUtilities.outputItemPrice(itemPriceList);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	
//    public void printItemsInDescendingOrder() {
//        List<Map.Entry<String, Double>> itemPriceList = new ArrayList<>();
//        List<WebElement> items = null;
//        List<WebElement> prices = null;
//        
//        //int count = 0;
//   
//
//        while(true)
//        {
////        	count++;
////        	System.out.println("-------------");
////        	System.out.println("Page "+count);
//        	
//        	try
//        	{
//        		items = driver.findElements(By.xpath("//*[@class='pr-4']"));
//        	}
//        	catch(StaleElementReferenceException e) 
//        	{
//	        	System.out.println("item names exception: "+e.getMessage());
//	        }
//        	
//           
//        	try
//        	{
//        		prices = driver.findElements(By.xpath("//*[@class='money']"));
//        	}
//        	catch(StaleElementReferenceException e) 
//        	{
//	        	System.out.println("prices exception: "+e.getMessage());
//	        }
//        	
//        	
////            System.out.println("number of elements:");
////            System.out.println(items.size()+":"+prices.size());
////            
////            for (WebElement item : items) {
////                System.out.println("Item visible: " + item.isDisplayed());
////            }
//            
//            // Check if either list is empty before processing
////            if (items.isEmpty() || prices.isEmpty()) {
////                break; // Exit the loop if there are no items or prices
////            }
//	        // Populate the list with items and their prices
//	        for (int i = 0; i < Math.min(items.size(), prices.size()); i++) {
//	            String itemText = items.get(i).getText();
//	            String priceText = prices.get(i).getText().replace("$", ""); // Remove dollar sign for parsing
//	            double priceValue = Double.parseDouble(priceText); // Parse price to double
//	
//	            // Add each item and its price to the list
//	            itemPriceList.add(Map.entry(itemText, priceValue));
//	        }
//	        
//	        //System.out.println("Page "+count+" finished");
//	        
//	        try {
//	        	elementControl.clickElement(NextButton);
//	        	
//		        try {
//		        	WebDriverWait wait = new WebDriverWait(driver, 10);
//		        	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Previous']")));
//		        }catch(StaleElementReferenceException e) {
//		        	System.out.println("PreviousButton exception: "+e.getMessage());
//		        }
//	        	
//	        	Thread.sleep(3000);
//	        }catch (NoSuchElementException | InterruptedException |StaleElementReferenceException e) {
//	        	System.out.println("NextButton exception: "+e.getMessage());
//	        	break;
//	        }	
//	        
//
//	        
//
//        }
//        
//        // Sort the list in descending order by price
//        itemPriceList.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));
//
//        double totalPrice = 0;
//        // Print the sorted items, allowing duplicates
//        for (Map.Entry<String, Double> entry : itemPriceList) {
//            System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
//            totalPrice += entry.getValue();
//        }
//        // Print the total price
//        System.out.printf("Total Price: $%.2f%n", totalPrice);
//        
//        try {
//			ExcelUtilities.outputItemPrice(itemPriceList);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
    
    
}



