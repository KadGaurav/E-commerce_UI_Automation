package Company.StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Company.TestComponents.BaseTest;
import Company.pageobjects.CheckOutPage;
import Company.pageobjects.ConfirmationPage;
import Company.pageobjects.LandingPage;
import Company.pageobjects.MyCartPage;
import Company.pageobjects.ProductCataloguePage;
import io.cucumber.java.en.*;

public class StepDefinationsImp extends BaseTest {

	LandingPage landingPage;
	ProductCataloguePage productCataloguePage;
	ConfirmationPage confirmationPage;
	MyCartPage myCartPage;
	
	@Given("I landed on Ecommerce Page")
	public void logInEcommercePage() throws IOException {
		landingPage = launchApp();
	}
	
	@Given("^Logged in with username (.+) and password (.+) $")
	public void logInUsingUserNameAndPassword(String username ,String password) {
		productCataloguePage = landingPage.loginApplication(username, password);
	}
	
	@When("^ I add product (.+) to Cart$")
	public void iAddProdToCart(String productName) {
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the Order$")
	public void checkOutSubmitOrder(String productName) throws InterruptedException {
		
		 myCartPage = productCataloguePage.openCart() ;
		
		Boolean match = myCartPage.verifySelectedProduct(productName);
		
		Assert.assertTrue(match);
		CheckOutPage checkOut = myCartPage.clickCheckOut();
		
		checkOut.selectFromList("India");
		confirmationPage = checkOut.clickOnPlaceOrder();
	}
	
	@Then("{String}  message is displayed on ConfirmationPage")
	public void messageDisplayedConfirmationPage(String string) {
		
		String confirmMsg = confirmationPage.getFinalMessage();
	    System.out.println(confirmMsg);
	    Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
	    driver.close();
	}
	
}
