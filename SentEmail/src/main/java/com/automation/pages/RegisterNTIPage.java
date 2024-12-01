package com.automation.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterNTIPage extends BasePage{
	
	@CacheLookup
	@FindBy(name="input_3.3")
	private WebElement firstName;
	
	@CacheLookup
	@FindBy(name="input_3.6")
	private WebElement secondName;
	
	@CacheLookup
	@FindBy(name="input_4")
	private WebElement emailInput;
	
	@CacheLookup
	@FindBy(name="input_21")
	private WebElement passwordInput;
	
	@CacheLookup
	@FindBy(name="input_21_2")
	private WebElement passwordConfirm;
	
	
	@CacheLookup
	@FindBy(id="label_26_30_1")
	private WebElement checkBox;
	
	@CacheLookup
	@FindBy(id="gform_submit_button_26")
	private WebElement submit;
	
	@CacheLookup
	@FindBy(xpath="//span[@class='elementor-button-text' and text()='View All Coursera Courses']")
	private WebElement viewCoursera;
		
	@CacheLookup
	@FindBy(xpath="(//span[@class='elementor-button-text' and text()='View Program'])[1]")
	private WebElement viewProgram;
	
	@CacheLookup
	@FindBy(xpath="//h2[text()='People Skills for Professional and Personal Success']")
	private WebElement enrollNow;;
	
	@CacheLookup
	@FindBy(xpath="//span[text()='View Course']")
	private WebElement openCoursera;
	
	@CacheLookup
	@FindBy(id="cds-react-aria-1")
	private WebElement passwordInputCoursera;
	
	@CacheLookup
	@FindBy(id="cds-react-aria-2")
	private WebElement submitCoursera;
	
	@CacheLookup
	@FindBy(id="user_login")
	private WebElement emailSignIn;
	
	@CacheLookup
	@FindBy(id="user_pass")
	private WebElement passwordSignIn;
	
	@CacheLookup
	@FindBy(id="wp-submit")
	private WebElement LogIn;
	
	@CacheLookup
	@FindBy(id="user")
	private WebElement emailSignInMainPage;
	
	@CacheLookup
	@FindBy(id="password")
	private WebElement passwordSignInMainPage;
	
	@CacheLookup
	@FindBy(xpath="//span[@class='elementor-button-text' and text()='Log In']")
	private WebElement LogInMainPage;
	
	
	public RegisterNTIPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public void signUp(String email,String password) {
		elementControl.setText(firstName, "your");
		elementControl.setText(secondName, "name");
		elementControl.setText(emailInput, email);
		elementControl.setText(passwordInput,password);
		elementControl.setText(passwordConfirm,password);
		elementControl.clickElement(checkBox);
		elementControl.clickElement(submit);
	}
	
	public void signInIndividualPage(String email,String password) {
		elementControl.setText(emailSignIn, email);
		elementControl.setText(passwordSignIn, password);
		elementControl.clickElement(LogIn);
	}
	
	public void signInMainPage(String email,String password) {
		elementControl.setText(emailSignInMainPage, email);
		elementControl.setText(passwordSignInMainPage, password);
		elementControl.clickElement(LogInMainPage);
	}
		
    public void grantMembership() { 
		
    	elementControl.clickElement(openCoursera);//last operation at the NTI site
    	try {
			Thread.sleep(10000);//redirect to coursera
		}catch(InterruptedException e){
			e.printStackTrace();
		}
        // Get all window handles
        Set<String> allWindowHandles = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(allWindowHandles);

        // Switch to the new tab (assuming it is the second tab opened)
        driver.switchTo().window(windowHandlesList.get(1));

		elementControl.setText(passwordInputCoursera, "455098730"); // login account
		elementControl.clickElement(submitCoursera);
	}
}
