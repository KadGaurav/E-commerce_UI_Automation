package Company.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Company.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		LandingPage landingPage= new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("123456gk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("1234@Abcd");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		 
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click() ;		
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	    Assert.assertTrue(match);
	    driver.findElement(By.cssSelector(".totalRow button")).click();
	    
	    Actions a  = new Actions(driver);
	    a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
	    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	    driver.findElement(By.cssSelector(".action__submit")).click();
	    
	    String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
	    Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
	    driver.close();
	}
}