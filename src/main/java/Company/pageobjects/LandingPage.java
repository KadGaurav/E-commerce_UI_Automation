package Company.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Company.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement emailElement;
	
	@FindBy(id = "userPassword")
	WebElement passwordElement;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]")
	WebElement errorMsg;
	

	public ProductCataloguePage loginApplication(String email,String password) {
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);  
		submit.click();
		ProductCataloguePage productCatalogue= new ProductCataloguePage (driver);
		return productCatalogue;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
}
