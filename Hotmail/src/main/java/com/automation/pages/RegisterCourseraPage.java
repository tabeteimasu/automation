package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterCourseraPage extends BasePage{
	
	@CacheLookup
	@FindBy(id="name")
	private WebElement name;
	
	@CacheLookup
	@FindBy(id="email")
	private WebElement email;
	
	@CacheLookup
	@FindBy(name="password")
	private WebElement password;
	
	@CacheLookup
	@FindBy(id="cds-react-aria-64-base")
	private WebElement agreement;
	
	@CacheLookup
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submit;
	
	public RegisterCourseraPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public void signUp(String myemail) {
		elementControl.setText(name, "placeholder");
		elementControl.setText(email, myemail);
		elementControl.setText(password, "455098730");
		
		elementControl.clickElement(agreement);
		elementControl.clickElement(submit);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		WebDriverWait wait = new WebDriverWait(driver, 10);//pass captcha manually
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//a[@data-track-component='page_nav_link_my_learning'])")));
	}
}
