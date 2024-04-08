package Company.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Company.AbstractComponents.AbstractComponent;
import dev.failsafe.internal.util.Assert;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary")
	WebElement finalMessage;
	
	public String getFinalMessage(){
		String confirmMsg = finalMessage.getText();
		return confirmMsg;
	}
}
