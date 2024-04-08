package Company.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Company.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement openList;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement optionList;
	
	@FindBy(css=".action__submit")
	WebElement Submit;
	
	By list =By.cssSelector(".ta-item");
	
	public void selectFromList(String optsToBeSelected) {
		//Actions a = new Actions(driver);
		//a.sendKeys(optsToBeSelected).build().perform();
		openList.sendKeys(optsToBeSelected);
		waitForElementToAppear(list);
		optionList.click();
	}
	public ConfirmationPage clickOnPlaceOrder() {
		Submit.click();		
		return new ConfirmationPage(driver);
	}
	
	
	
	

}
