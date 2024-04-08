package Company.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Company.TestComponents.BaseTest;
import Company.pageobjects.CheckOutPage;
import Company.pageobjects.ConfirmationPage;
import Company.pageobjects.MyCartPage;
import Company.pageobjects.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"errorValidation"})
	public void loginErrorValidation() throws IOException{
		
	
	landingPage.loginApplication("123456gk@gmail.com", "1234@Abcde");
	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}


	@Test(enabled = false)
	public void ProductErrorValidation() throws IOException{
		String productName = "ZARA COAT 3";
		
		ProductCataloguePage productCatalogue = landingPage.loginApplication("1234567gk@gmail.com", "1234@Abcd");
		productCatalogue.addProductToCart(productName);
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click() ;
		
		MyCartPage myCartPage = new MyCartPage(driver) ;
		Boolean match = myCartPage.verifySelectedProduct("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
}
