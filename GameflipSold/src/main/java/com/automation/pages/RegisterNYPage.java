package com.automation.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterNYPage extends BasePage{
	
	
	@CacheLookup
	@FindBy(id="selfregform:firstname")
	private WebElement first;
	
	@CacheLookup
	@FindBy(id="selfregform:lastname")
	private WebElement last;
	
	
	@CacheLookup
	@FindBy(id="selfregform:email")
	private WebElement email;
	
	
	@CacheLookup
	@FindBy(id="selfregform:confirmEmail")
	private WebElement confirmEmail;
	
	@CacheLookup
	@FindBy(id="selfregform:userId")
	private WebElement username;
	
	
	@CacheLookup
	@FindBy(id="selfregform:signinButton")
	private WebElement signIn;
	
	@CacheLookup
	@FindBy(id="selfregform:city")
	private WebElement city;
	
	
	@CacheLookup
	@FindBy(id="selfregform:dob")
	private WebElement date;
	
	@CacheLookup
	@FindBy(id="selfregform:signinButton")
	private WebElement continueBtn;
	
	
	@CacheLookup
	@FindBy(id="confirmationpage:continueButton")
	private WebElement createAccount;
	
	
	public RegisterNYPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public void signUp(String myemail) {    
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
		elementControl.setText(last, lastName);
		elementControl.setText(first, firstName);
		
		elementControl.setText(confirmEmail, myemail);
		elementControl.setText(email, myemail);
		
		elementControl.setText(username, myemail);
		elementControl.clickElement(signIn);
        
		
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
		elementControl.setText(city, "ny");
		elementControl.setText(date, "01/01/1990");
		elementControl.clickElement(continueBtn);
		
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        elementControl.clickElement(createAccount);
	}
}
