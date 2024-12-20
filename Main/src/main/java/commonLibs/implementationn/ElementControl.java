package commonLibs.implementationn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ElementControl {
	WebDriver driver;
	
	public ElementControl(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void clickElement(WebElement element) {
		element.click();
		
	}
	
	public void clear(WebElement element) {
		element.clear();
	}
	
	public void setText(WebElement element, String text) {
		element.sendKeys(text);
	}
	
	public boolean isEnabled(WebElement element) {
		return element.isEnabled();
	}
	
	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	public String getTextFromAlert() {
		return driver.switchTo().alert().getText();
	}
	
	public void selectViaVisbleText(WebElement element, String text) {
		Select selDropdown = new Select(element);
		selDropdown.selectByVisibleText(text);
	}
	
	public void selectViaIndex(WebElement element, int index) {
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}
}
