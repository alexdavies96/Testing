package AutomatedTesting.AutomatedTesting;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import junit.framework.Assert;

public class ShoppingSite {

	private WebDriver webdriver;
	private static ExtentReports report;
	private static String fileName = "ShoppingReport" + ".html";

	@BeforeClass
	public static void init() {
		report = new ExtentReports();
		String filePath = System.getProperty("user.dir") + File.separatorChar + fileName;
		report.attachReporter(new ExtentHtmlReporter(filePath));
	}
	
	@Before
	public void setUp() {
		System.getProperty("webdriver.chrome.driver","/Users/Alex-Rowan/Documents/GitHub/Testing/AutomatedTesting");
		webdriver = new ChromeDriver();
		webdriver.manage().window().maximize();
	}
	
	public static String takeScreenShot(WebDriver webdriver, String fileName) throws IOException {
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		String pathname = System.getProperty("user.dir") + File.separatorChar + fileName + ".jpg";
		FileUtils.copyFile(scrFile,  new File(pathname));
		System.out.println("File Saved at: " + pathname);
		return pathname;
	}
	
	@Test
	public void TestShoppingSite() throws IOException {
		try {
			
			webdriver.navigate().to("http://automationpractice.com/index.php");
			
		//Test 1: Search Test
			WebElement search = webdriver.findElement(By.cssSelector("#search_query_top"));
			search.click();
			String SearchValue = "Dress";
			search.sendKeys(SearchValue);
			WebElement Go = webdriver.findElement(By.cssSelector("#searchbox > button"));
			Go.click();
			WebElement Result = webdriver.findElement(By.cssSelector("#center_column > ul > li:nth-child(1) > div > div.right-block > h5 > a"));
			String ResultString = Result.getText();
			if (ResultString.contains(SearchValue))
			{
				assertTrue("pass", true);
			}
			else 
			{
				fail();
			}
			
		//Test 2: Add to Basket
			/*Actions action = new Actions(webdriver);
			WebElement TShirts = webdriver.findElement(By.cssSelector("#block_top_menu > ul > li:nth-child(3) > a"));
			TShirts.click();
			WebElement hover = webdriver.findElement(By.cssSelector("#center_column > ul > li > div > div.left-block > div > a.product_img_link > img"));
	        action.moveToElement(hover).build().perform();
			WebElement Add = webdriver.findElement(By.cssSelector("#center_column > ul > li > div > div.right-block > div.button-container > a.button.ajax_add_to_cart_button.btn.btn-default > span"));
			Add.click();
			WebElement CheckOut = webdriver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a"));
			CheckOut.click();
			WebElement Total = webdriver.findElement(By.cssSelector("#total_price_container"));
			String TotalValue = Total.getText();
			int TotalValueInt = Integer.parseInt(TotalValue);
			if (TotalValueInt > 0)
			{
				assertTrue("pass", true);
			}
			else 
			{
				fail();
			}*/
			//check if its in the basket
			
		
		//Test 3: Create Account
			WebElement SignIn = webdriver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a"));
			SignIn.click();
			WebElement Email = webdriver.findElement(By.cssSelector("#email_create"));
			String EmailAdd = "Alex.davies96@gmx.com";
		
			//check it wont accept anything other than an email 
			
		//Test4: Sign in with the account
			
		//Test5: Contact. Testing that it wont let me make the contact if one of the fields
		//is not filled in correctly.
			WebElement Contact = webdriver.findElement(By.cssSelector("#contact-link"));
			Contact.click();
			
			WebElement Heading = webdriver.findElement(By.cssSelector("#id_contact"));
			Heading.click();
			WebElement EmailContact = webdriver.findElement(By.cssSelector("#email"));
			EmailContact.sendKeys(EmailAdd);
			WebElement OrderRef = webdriver.findElement(By.cssSelector("#id_order"));
			OrderRef.sendKeys("123124523");
			WebElement Message = webdriver.findElement(By.cssSelector("#message"));
			Message.sendKeys("This is a message in the message box");
			WebElement Send = webdriver.findElement(By.cssSelector("#submitMessage"));
			Send.click();
			String errorColour = webdriver.findElement(By.cssSelector("#center_column > div")).getCssValue("background-color").toString();
			System.out.println(errorColour);
			if (errorColour.equals("rgba(243, 81, 92, 1)"))
			{
				assertTrue("pass", true);
			}
			else 
			{
				fail();
			}
		
		} catch(Exception e) {
			System.out.println("Element Not Found");
			ShoppingSite.takeScreenShot(webdriver, "ScreenShot");
			Assert.fail();
		}
	}
	
	public void dragAndDrop(WebElement dragable, WebElement dropable,int dropableOffsetX, int dropableOffsetY) {
	    Actions builder = new Actions(webdriver);
	    int offsetX = dropable.getLocation().x + dropableOffsetX - dragable.getLocation().x;
	    int offsetY = dropable.getLocation().y + dropableOffsetY - dragable.getLocation().y;
	    builder.clickAndHold(dragable).moveByOffset(offsetX, offsetY).release().perform();
	}
	
	@After
	public void tearDown() {
		webdriver.quit();
	}
	
	@AfterClass
	public static void cleanUp() {
		report.flush();
	}
}
