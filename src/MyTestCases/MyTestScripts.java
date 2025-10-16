package MyTestCases;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestScripts {

	WebDriver driver=new EdgeDriver();
	String SwagLabsWebsite="https://www.saucedemo.com/";
	String Email="standard_user";
	String pass="secret_sauce";
	Random rand =new Random();
	
	
	@BeforeTest
	public void setUp() 
	{
		driver.get(SwagLabsWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void LoginTest() throws InterruptedException
	{
		WebElement UserName=driver.findElement(By.id("user-name"));
		UserName.sendKeys(Email);
		WebElement Password=driver.findElement(By.id("password"));
		Password.sendKeys(pass);
		WebElement LoginButton=driver.findElement(By.id("login-button"));
		LoginButton.click();
		Thread.sleep(2000);
	}
	@Test(priority=2)
	public void AddRandomItemsTest()
	{
		List <WebElement> Items=driver.findElements(By.className("inventory_item_name"));
		 List <WebElement> AddToCartButton=driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
		 int RandomItem1=rand.nextInt(Items.size());
		 int RandomItem2 = 0;
		 if(RandomItem1==5)
		 {
			 RandomItem2=RandomItem1-1;
		 }
		 else
		 {
			 RandomItem2=RandomItem1+1;
		 }
        AddToCartButton.get(RandomItem1).click();
        AddToCartButton.get(RandomItem2).click();
	}
	
	@Test(priority=3)
	public void CheckOutTest() throws InterruptedException
	{
		driver.navigate().to("https://www.saucedemo.com/cart.html");
        WebElement CheckOutButton=driver.findElement(By.cssSelector(".btn.btn_action.btn_medium.checkout_button"));
       CheckOutButton.click();
       WebElement FirstName =driver.findElement(By.id("first-name"));
       WebElement LastName =driver.findElement(By.id("last-name"));
       WebElement ZipCode=driver.findElement(By.id("postal-code"));
       WebElement ContinueButton=driver.findElement(By.id("continue"));
       FirstName.sendKeys("Mohammad");
       LastName.sendKeys("Nofal");
       ZipCode.sendKeys("2006538291");
       ContinueButton.click();
       WebElement FinishButton=driver.findElement(By.id("finish"));
       FinishButton.click();
       Thread.sleep(3000);
       boolean actualResult=driver.getPageSource().contains("Thank you for your order!");
       Assert.assertEquals(actualResult, true);

	}
	
}
