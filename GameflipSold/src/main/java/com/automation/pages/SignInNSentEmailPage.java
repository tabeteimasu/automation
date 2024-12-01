package com.automation.pages;


import java.io.IOException;
import java.util.Map;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import commonLibs.utilities.ExcelUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInNSentEmailPage extends BasePage{

	
	@CacheLookup
	@FindBy(id="i0116")
	private WebElement email;
	
	@CacheLookup
	@FindBy(id="idSIButton9")
	private WebElement next;
	
	
	
	@CacheLookup
	@FindBy(xpath="//input[@name='passwd']")
	private WebElement password;
	
	
	@CacheLookup
	@FindBy(id="acceptButton")
	private WebElement accept;
	
	
	@CacheLookup
	@FindBy(xpath="(//button[@data-automation-type=\"RibbonSplitButton\"])[1]")
	private WebElement newEmail;
	
	@CacheLookup
	@FindBy(xpath="//*[@role and @dir]")
	private WebElement emailBody;
	
	
	@CacheLookup
	@FindBy(xpath="(//input[@type='text'])[2]")
	private WebElement subject;
	
	
	@CacheLookup
	@FindBy(xpath="//*[@aria-label='收件人' and @role='textbox']")
	private WebElement receiver;
	
	@CacheLookup
	@FindBy(xpath="//button[starts-with(@id, 'splitButton')]")
	private WebElement send;

	
	public SignInNSentEmailPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public void signInNSent(String myemail) {

		elementControl.setText(email, myemail);
		elementControl.clickElement(next);
        elementControl.setText(password, "zz455098730");
		//((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", elementPasswd,"zz455098730");
		
		WebElement elementSignIn = driver.findElement(By.id("idSIButton9"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",elementSignIn);
		
		elementControl.clickElement(accept);
		
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
		WebDriverWait waitNewButton = new WebDriverWait(driver, 60);
		waitNewButton.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//button[@data-automation-type='RibbonSplitButton'])[1]")));
        elementControl.clickElement(newEmail);
        
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String wholeName = driver.getTitle().split("-")[1];
        //System.out.println(wholeName);
        String firstName = wholeName.split(" ")[1];
        //System.out.println(firstName);
        String lastName = wholeName.split(" ")[2];
        //System.out.println(lastName);
        String email = myemail;
        
		String[] data = ExcelUtilities.countyNphone();
        String phone = data[1];
        Random random = new Random();
        int random1 =  random.nextInt(9);
        int random2 =  random.nextInt(9);
        phone = phone.substring(0, phone.length()-2)+random1+random2;
        String county = data[0];
        String content = firstName+"\n"+
        				lastName+"\n"+
        				email+"\n"+
        				phone+"\n"+
        				county;

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@role='textbox' and @dir]")));
		
       
		try {
			 elementControl.setText(subject, "Coursera");
	    } catch (Exception e) {
	    	subject = driver.findElement(By.xpath("//input[starts-with(@id, 'Text')]"));
	    	elementControl.setText(subject, "Coursera");
	    }
		
		
       
        
        String destination  = "labor.sm.coursera.register@labor.ny.gov";
        while(!receiver.isDisplayed())
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        receiver.sendKeys(destination);
        
        WebElement contentEditableDiv = driver.findElement(By.xpath("//div[@role='textbox' and @dir]"));
        contentEditableDiv.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contentEditableDiv.sendKeys(content);
        
        
        try {
        	elementControl.clickElement(send);
        }catch(Exception e) {
        	send = driver.findElement(By.xpath("//button[starts-with(@aria-describedby, 'send')]"));
        	elementControl.clickElement(send);
        }
        
        
	}
}
