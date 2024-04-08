package Company.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartPage {
	WebDriver driver;
	
	public MyCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
    
    public Boolean verifySelectedProduct(String productName) {
    	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));    	
    	return match;
    }
    
    public CheckOutPage clickCheckOut() {
    	checkOutButton.click(); 
    	CheckOutPage checkOutPage = new CheckOutPage(driver);
    	return checkOutPage;
    }
}
