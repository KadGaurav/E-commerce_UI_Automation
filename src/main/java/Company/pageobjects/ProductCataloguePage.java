package Company.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Company.AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {	


	WebDriver driver;
	
	public ProductCataloguePage (WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	//@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	//WebElement cartButton;
	
	//@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	//WebElement ordersBtn;
	
	
	By productsBy = By.cssSelector(".mb-3") ;
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By cartButtonBy = By.xpath("//button[@routerlink='/dashboard/cart']");
	By spinner = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() 
	{
		waitForElementToAppear(productsBy);
		
		List<WebElement> productName = driver.findElements(By.cssSelector(".mb-3"));
		return productName;
	}
	
	public WebElement getProductByName(String productName) 
	{
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	public MyCartPage openCart() throws InterruptedException {
		waitForElementToDisappear(spinner);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		MyCartPage myCartPage = new MyCartPage(driver);
		return myCartPage;
	}
	
	public OrdersPage goToOrders() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

	
}