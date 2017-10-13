package AutomatedTesting.AutomatedTesting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskSelenium {

	private WebDriver webdriver;
	
	@Before
	public void setUp() {
		System.getProperty("webdriver.chrome.driver","/Users/Alex-Rowan/Documents/GitHub/Testing/AutomatedTesting");
		webdriver = new ChromeDriver();
		webdriver.manage().window().maximize();
	}
	
	@After
	public void tearDown() {
		webdriver.quit();
	}
	
	@Test
	public void googleTest(){
		webdriver.navigate().to("http://www.qa.com");
		WebElement contactLink = webdriver.findElement(By.cssSelector("#menu > div.navigation-links-hugescreen.visible-f > ul > li:nth-child(7) > div > a > span"));
		contactLink.click();
	}
}
