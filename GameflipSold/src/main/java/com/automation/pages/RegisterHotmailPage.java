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

public class RegisterHotmailPage extends BasePage{
	
	
	@CacheLookup
	@FindBy(xpath="//*[@id='iSignupAction' or @id='nextButton']")
	private WebElement next;
	
	@CacheLookup
	@FindBy(id="liveSwitch")
	private WebElement link;
	
	@CacheLookup
	@FindBy(xpath="//*[@id='usernameInput' or @id='MemberName']")
	private WebElement prefix;
	
	@CacheLookup
	@FindBy(xpath="//*[@id='PasswordInput' or @id='Password']")
	private WebElement password;
	
	
	
	@CacheLookup
	@FindBy(id="lastNameInput")
	private WebElement last;
	
	
	@CacheLookup
	@FindBy(id="firstNameInput")
	private WebElement first;
	
	
	@CacheLookup
	@FindBy(id="BirthYear")
	private WebElement year;
	
	@CacheLookup
	@FindBy(id="BirthMonth")
	private WebElement month;
	
	
	@CacheLookup
	@FindBy(id="BirthDay")
	private WebElement day;
	
	
	public RegisterHotmailPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}
	
	public void signUp(String myemail) {
		elementControl.clickElement(next);

		WebElement element = driver.findElement(By.id("liveSwitch"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		elementControl.setText(prefix, myemail);
		
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		//elementControl.clickElement(next);
        element = driver.findElement(By.xpath("//*[@id='iSignupAction' or @id='nextButton']"));
        element.click();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(password));
		elementControl.setText(password, "zz455098730");
		
		//elementControl.clickElement(next);
        element = driver.findElement(By.xpath("//*[@id='iSignupAction' or @id='nextButton']"));
        element.click();
        
        
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
		elementControl.setText(last, lastName);
		
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		elementControl.setText(first, firstName);
		
		//elementControl.clickElement(next);
        element = driver.findElement(By.xpath("//*[@id='iSignupAction' or @id='nextButton']"));
        element.click();
        
		elementControl.setText(year, "2000");
		elementControl.selectViaIndex(month, 1);
		elementControl.selectViaIndex(day, 1);
		
		//elementControl.clickElement(next);
        element = driver.findElement(By.xpath("//*[@id='iSignupAction' or @id='nextButton']"));
        element.click();
        
		
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
