package Company.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Company.TestComponents.BaseTest;
import Company.TestComponents.Retry;
import Company.pageobjects.CheckOutPage;
import Company.pageobjects.ConfirmationPage;
import Company.pageobjects.MyCartPage;
import Company.pageobjects.OrdersPage;
import Company.pageobjects.ProductCataloguePage;

public class SubmitOrderTest extends BaseTest{

		@Test(dataProvider = "getData",groups="Purchase", retryAnalyzer=Retry.class)
		public void submitOrder(HashMap<String,String>input) throws IOException{
		
		ProductCataloguePage productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click() ;
		
		MyCartPage myCartPage = new MyCartPage(driver) ;
		Boolean match = myCartPage.verifySelectedProduct(input.get("product"));
		
		Assert.assertTrue(match);
		CheckOutPage checkOut = myCartPage.clickCheckOut();
		
		checkOut.selectFromList("India");
		ConfirmationPage confirmationPage = checkOut.clickOnPlaceOrder();

	    
	    String confirmMsg = confirmationPage.getFinalMessage();
	    System.out.println(confirmMsg);
	    Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
	}
		
		@Test(dependsOnMethods = {"submitOrder"},dataProvider = "getData",retryAnalyzer=Retry.class)
		public void verifyOrderList(String email,String password , String productName) throws InterruptedException {

			ProductCataloguePage productCatalogue = landingPage.loginApplication(email, password);
			OrdersPage ordersPage=productCatalogue.goToOrders();
			Assert.assertTrue(ordersPage.verifyOrdersDisplay(productName));
		}
		
		public String getScreenShot(String testCaseName) throws IOException {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+ "//reports//"+testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+ "//reports//"+testCaseName + ".png";
		}
		
		@DataProvider()
		public Object[][] getData() throws IOException{
			/*
			 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
			 * "123456gk@gmail.com"); map.put("password", "1234@Abcd"); map.put("product",
			 * "ZARA COAT 3");
			 * 
			 * HashMap<String,String> map1 = new HashMap<String,String>();
			 * 
			 * map1.put("email", "1234567gk@gmail.com"); map1.put("password", "1234@Abcd");
			 * map1.put("product", "ADIDAS ORIGINAL");
			 */
			List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\Company\\data\\Purchase.json");
			
			return new Object[] [] {{data.get(0)},{data.get(1)}};
		}
		
}