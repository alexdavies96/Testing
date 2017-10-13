package AutomatedTesting.AutomatedTesting;

import static org.junit.Assert.*;

import java.awt.MouseInfo;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import junit.framework.Assert;

public class MouseActions {
	
private static WebDriver webdriver;
private static ExtentReports report;
private static String fileName = "MyReport" + ".html";


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
	
	//screen shot
	public static String take(WebDriver webdriver, String fileName) throws IOException{
		File scrFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		String pathname = System.getProperty("user.dir") + File.separatorChar + fileName + ".jpg";
		FileUtils.copyFile(scrFile,  new File(pathname));
		System.out.println("File Saved at: " + pathname);
		return pathname;
	}
	
	@Test
	public void TestMethod() throws IOException {
		try {
		webdriver.navigate().to("http://demoqa.com/");
		WebElement draggable = webdriver.findElement(By.cssSelector("#menu-item-140"));
		draggable.click();
		
		//Default Functionality
		WebElement drag = webdriver.findElement(By.cssSelector("#draggable"));
		WebElement dragRaius = webdriver.findElement(By.cssSelector("#tabs-1"));
		dragAndDrop(drag, dragRaius, 150, 200);
		
		//Constrain Movement (tested)
		WebElement constrainMovement = webdriver.findElement(By.cssSelector("#ui-id-2"));
		constrainMovement.click();
		WebElement drag2 = webdriver.findElement(By.cssSelector("#draggabl"));
		WebElement dragRadius1 = webdriver.findElement(By.cssSelector("#containment-wrapper"));
		int firLoc = drag2.getLocation().x;
		dragAndDrop(drag2, dragRadius1, 10, 40);
		int secLoc = drag2.getLocation().x;
		if (secLoc == firLoc)
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		WebElement drag3 = webdriver.findElement(By.cssSelector("#draggabl2"));
		int firLoc1 = drag3.getLocation().y;
		dragAndDrop(drag3, drag3, 300, 100);
		int secLoc1 = drag3.getLocation().y;
		if (secLoc1 == firLoc1)
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		WebElement drag4 = webdriver.findElement(By.cssSelector("#draggabl3"));
		WebElement container = webdriver.findElement(By.cssSelector("#containment-wrapper"));
		int containerLocX = container.getLocation().x;
		int containerLocY= container.getLocation().y;
		dragAndDrop(drag4, container, 300, 100);
		int secLoc2x = drag4.getLocation().y;
		int secLoc2y = drag4.getLocation().x;
		if (secLoc2y > containerLocY || secLoc2x > containerLocX )
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		WebElement drag5 = webdriver.findElement(By.cssSelector("#draggabl5"));
		WebElement container2 = webdriver.findElement(By.cssSelector("#containment-wrapper > div:nth-child(2)"));
		int parentLocX = container2.getLocation().x;
		int parentLocY= container2.getLocation().y;
		dragAndDrop(drag5, container2, 0, 100);
		int secLoc3x = drag5.getLocation().y;
		int secLoc3y = drag5.getLocation().x;
		if (secLoc3y > parentLocY || secLoc3x > parentLocX )
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		
		//Events (Tested)
		WebElement Event = webdriver.findElement(By.cssSelector("#ui-id-4"));
		Event.click();
		WebElement drag6 = webdriver.findElement(By.cssSelector("#drag"));
		WebElement Constraint = webdriver.findElement(By.cssSelector("#tabs"));
		dragAndDrop(drag6, Constraint, 300, 100);
		WebElement drag7 = webdriver.findElement(By.cssSelector("#drag3"));
		dragAndDrop(drag7, drag7, 300, 100);
		WebElement drag8 = webdriver.findElement(By.cssSelector("#drag2"));
		dragAndDrop(drag8, drag8, 300, 100);
		WebElement events = webdriver.findElement(By.cssSelector("#ui-id-4"));
		events.click(); 
		WebElement drag9 = webdriver.findElement(By.cssSelector("#dragevent"));
		dragAndDrop(drag9, drag9, 100, 100);
		String value = webdriver.findElement(By.cssSelector("#event-drag")).getCssValue("count");
		if (value != "0")
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
	/*	
		//Draggable + Sortable  --ToDoTest--
		WebElement draggableSortable = webdriver.findElement(By.cssSelector("#ui-id-5"));
		draggableSortable.click(); 
		WebElement one = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(1)"));
		WebElement three = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(3)"));	
		
		String OneString = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(1)")).getText();
		String TwoString = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(2)")).getText();
		String ThreeString = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(3)")).getText();
		String FourString = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(4)")).getText();
		String FiveString = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(5)")).getText();
		String[] listArr = {OneString, TwoString, ThreeString, FourString, FiveString};
		dragAndDrop(one, three, 0, 30);
		String OneString2 = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(1)")).getText();
		String TwoString2 = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(2)")).getText();
		String ThreeString2= webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(3)")).getText();
		String FourString2 = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(4)")).getText();
		String FiveString2 = webdriver.findElement(By.cssSelector("#sortablebox > li:nth-child(5)")).getText();
		String[] listArr2 = {OneString2, TwoString2, ThreeString2, FourString2, FiveString2};
		Boolean passed = false;
		System.out.println(listArr[0]);
		System.out.println(listArr2[0]);
		for (int i = 0; i < listArr.length; i++)
		{
		if (listArr[i] == listArr2[i])
		{
			passed = true;
			System.out.println(listArr[i]);
			System.out.println(listArr2[i]);
		}
		}
		
		if (passed != true)
		{
			//assertTrue("passed", true);
		}
		else 
		{
			//fail();
		}
		*/
		
		
		//Default Functionality (Tested)
		WebElement Droppable = webdriver.findElement(By.cssSelector("#menu-item-141"));
		Droppable.click(); 
		WebElement drag14 = webdriver.findElement(By.cssSelector("#draggableview"));
		WebElement Target = webdriver.findElement(By.cssSelector("#droppableview"));
		dragAndDrop(drag14, Target, 50, 30);
		String STarget = webdriver.findElement(By.cssSelector("#droppableview")).getText();
		if (STarget.equals("Dropped!"))
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		
		//Accept (Tested)
		WebElement Accept = webdriver.findElement(By.cssSelector("#ui-id-2"));
		Accept.click();
		WebElement drag15 = webdriver.findElement(By.cssSelector("#draggableaccept"));
		WebElement Target1 = webdriver.findElement(By.cssSelector("#droppableaccept"));
		dragAndDrop(drag15, Target1, 45, 30);
		String STarget1 = webdriver.findElement(By.cssSelector("#droppableaccept")).getText();
		if (STarget1.equals("Dropped!"))
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		
		//Prevent Propagation  (Tested)
		WebElement Prevent = webdriver.findElement(By.cssSelector("#ui-id-3"));
		Prevent.click();
		WebElement drag16 = webdriver.findElement(By.cssSelector("#draggableprop"));
		WebElement Target2 = webdriver.findElement(By.cssSelector("#droppableprop"));
		dragAndDrop(drag16, Target2, 50, 0);
		dragAndDrop(drag16, Target2, 350, 0);
		dragAndDrop(drag16, Target2, 350, 50);
		dragAndDrop(drag16, Target2, 0, 50);
		String TargetSmall1 = webdriver.findElement(By.cssSelector("#droppable-inner")).getText();
		String TargetSmall2 = webdriver.findElement(By.cssSelector("#droppable2-inner")).getText();
		if (TargetSmall1.equals("Dropped!") && TargetSmall2.equals("Dropped!"))
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		//Revert Draggable Position  (Tested)
		WebElement Revert = webdriver.findElement(By.cssSelector("#ui-id-4"));
		Revert.click();
		WebElement drag17 = webdriver.findElement(By.cssSelector("#draggablerevert"));
		WebElement drag18 = webdriver.findElement(By.cssSelector("#draggablerevert2"));
		WebElement Target3 = webdriver.findElement(By.cssSelector("#droppablerevert"));
		int OriginX = drag17.getLocation().x;
		int OriginY = drag17.getLocation().y;
		dragAndDrop(drag18, Target3, 25, 20);
		dragAndDrop(drag17, Target3, 50, 20);
		dragAndDrop(drag18, Target3, 100, 20);
		int Origin2X = drag17.getLocation().x;
		int Origin2Y = drag17.getLocation().y;
		String DropableRevert = webdriver.findElement(By.cssSelector("#droppablerevert")).getText();
		if (DropableRevert.equals("Dropped!"))
		{
			if (OriginX == Origin2X && OriginY == Origin2Y) assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
	
		//Shopping Cart Demo  (Does not need Test)
		WebElement Shopping = webdriver.findElement(By.cssSelector("#ui-id-5"));
		Shopping.click();
		WebElement gadgets = webdriver.findElement(By.cssSelector("#ui-id-10"));
		gadgets.click();
		WebElement drag19 = webdriver.findElement(By.cssSelector("#ui-id-11 > ul > li:nth-child(3)"));
		WebElement Target4 = webdriver.findElement(By.cssSelector("#cart"));
		dragAndDrop(drag19, Target4, 0, 100);
		
		//Default Functionality		(Tested)
		WebElement Selectable = webdriver.findElement(By.cssSelector("#menu-item-142"));
		Selectable.click();
		WebElement drag20 = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(1)"));
		WebElement target = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(5)"));
		String colourBefore = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(1)")).getCssValue("background-color").toString();
		dragAndDrop(drag20, target, 0, 50);
		String colour = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(1)")).getCssValue("background-color").toString();
		String colourEnd = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(5)")).getCssValue("background-color").toString();
		if (colour != colourBefore) 
		{	
			if (colour == colourEnd) assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		//Display as Grid	(Tested)
		WebElement Grid = webdriver.findElement(By.cssSelector("#ui-id-2"));
		Grid.click();
		WebElement drag21 = webdriver.findElement(By.cssSelector("#selectable_grid > li:nth-child(1)"));
		WebElement target1 = webdriver.findElement(By.cssSelector("#selectable_grid > li:nth-child(8)"));
		String colourBefore1 = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(1)")).getCssValue("background-color").toString();
		dragAndDrop(drag21, target1, 100, 40);
		String colour1 = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(1)")).getCssValue("background-color").toString();
		String colourEnd1 = webdriver.findElement(By.cssSelector("#selectable > li:nth-child(1)")).getCssValue("#selectable_grid > li:nth-child(8)").toString();
		if (colour1 != colourBefore1) 
		{	
			if (colour1 == colourEnd1) assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		//Serialize		(Tested)
		WebElement Serialize = webdriver.findElement(By.cssSelector("#ui-id-3"));
		Serialize.click();
		WebElement drag22 = webdriver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(2)"));
		WebElement target2 = webdriver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(4)"));
		String colourBefore2 = webdriver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(2)")).getCssValue("background-color").toString();
		dragAndDrop(drag22, target2, 0, 50);
		String colour2 = webdriver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(2)")).getCssValue("background-color").toString();
		String colourEnd2 = webdriver.findElement(By.cssSelector("#selectable-serialize > li:nth-child(4)")).getCssValue("#selectable-serialize > li:nth-child(4)").toString();
		if (colour2 != colourBefore2) 
		{	
			if (colour2 == colourEnd2) assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		//Default Functionality
		WebElement Sortable = webdriver.findElement(By.cssSelector("#menu-item-151 > a"));
		Sortable.click();
		WebElement select = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(1)"));
		WebElement swap = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(5)"));
		WebElement select1 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(7)"));
		WebElement swap1 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(2)"));
		WebElement select2 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(4)"));
		WebElement swap2 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(3)"));
		WebElement select3 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(5)"));
		WebElement swap3 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(3)"));
		String item1 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(1)")).toString();
		String item2 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(2)")).toString();
		String item3 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(3)")).toString();
		String item4 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(4)")).toString();
		String item5 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(5)")).toString();
		String item6 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(6)")).toString();
		String item7 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(7)")).toString();
		dragAndDrop(select,   swap, 0, 10);
		dragAndDrop(select1, swap1, 0, 5);
		dragAndDrop(select2, swap2, 0, 2);
		dragAndDrop(select3, swap3, 0, 6);
		String Aitem1 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(1)")).toString();
		String Aitem2 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(2)")).toString();
		String Aitem3 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(3)")).toString();
		String Aitem4 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(4)")).toString();
		String Aitem5 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(5)")).toString();
		String Aitem6 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(6)")).toString();
		String Aitem7 = webdriver.findElement(By.cssSelector("#sortable > li:nth-child(7)")).toString();
		if (item1 != Aitem1 || item2 != Aitem2 || item3 != Aitem3 || item4 != Aitem4 || item5 != Aitem5
			|| item6 != Aitem6 || item7 != Aitem7) {  	
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		//Connect Lists		(Tested)
		WebElement Connect = webdriver.findElement(By.cssSelector("#ui-id-2"));
		Connect.click();
		WebElement Left1 = webdriver.findElement(By.cssSelector("#sortable1 > li:nth-child(1)"));
		WebElement Right1 = webdriver.findElement(By.cssSelector("#sortable2 > li:nth-child(1)"));
		WebElement Right2 = webdriver.findElement(By.cssSelector("#sortable2 > li:nth-child(2)"));
		String Left1B = webdriver.findElement(By.cssSelector("#sortable1 > li:nth-child(1)")).getText();
		dragAndDrop(Left1,   Right1, 10, 10);
		dragAndDrop(Right2, Left1, 10, 10);
		String Left1A = webdriver.findElement(By.cssSelector("#sortable1 > li:nth-child(1)")).getText();
		if (Left1B != Left1A) 
		{
			assertTrue("object is within the constraints", true);
		}
		else 
		{
			fail();
		}
		
		//Display As Grid
		WebElement Grid1 = webdriver.findElement(By.cssSelector("#ui-id-3"));
		Grid1.click();
		WebElement One = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(1)"));
		WebElement Two = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(2)"));
		WebElement Three = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(3)"));
		WebElement Six = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(6)"));
		String One1 = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(1)")).getText();
		String Two1 = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(2)")).getText();
		String[] Before = {One1, Two1}; 
		dragAndDrop(One, Three, 150, 100);
		dragAndDrop(Six, Two, 20,-30);
		String One2 = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(1)")).getText();
		String Two2 = webdriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(2)")).getText();
		String[] After = {One2, Two2}; 
		for (int i = 0; i < Before.length; i++)
		{
			if (Before[i] == (After[i]))
			{
				fail();
			}
			else
			{
				assertTrue("passed", true);
			}
		}
		
		//Portlets
		WebElement Portlets = webdriver.findElement(By.cssSelector("#ui-id-4"));
		Portlets.click();
		WebElement Shopping1 = webdriver.findElement(By.cssSelector("#tabs-4 > div > div:nth-child(2) > div > div.portlet-header.ui-sortable-handle.ui-widget-header.ui-corner-all"));
		WebElement Feeds = webdriver.findElement(By.cssSelector("#tabs-4 > div > div:nth-child(1) > div:nth-child(1) > div.portlet-header.ui-sortable-handle.ui-widget-header.ui-corner-all"));
		String ShoppingB = Shopping1.getText();
		String FeedsB = Feeds.getText();
		dragAndDrop(Feeds, Shopping1, 30, 0);
		dragAndDrop(Shopping1, Shopping1, 100, 0);
		WebElement Shopping2 = webdriver.findElement(By.cssSelector("#tabs-4 > div > div:nth-child(2) > div > div.portlet-header.ui-sortable-handle.ui-widget-header.ui-corner-all"));
		WebElement Feeds2 = webdriver.findElement(By.cssSelector("#tabs-4 > div > div:nth-child(1) > div:nth-child(1) > div.portlet-header.ui-sortable-handle.ui-widget-header.ui-corner-all"));
		String ShoppingA = Shopping2.getText();
		String FeedsA = Feeds2.getText();
		if (FeedsB == FeedsA || ShoppingB == ShoppingA)
		{
			fail();
		}
		else
		{
			assertTrue("passed", true);
		}
		
		//RangeSlider
		/*WebElement Slider2 = webdriver.findElement(By.cssSelector("#menu-item-97"));
		Slider2.click();
		WebElement RangeSlider = webdriver.findElement(By.cssSelector("#slider-range-max"));
		WebElement bed = webdriver.findElement(By.cssSelector("#amount1"));
		String NoBeds = null;
		bed.getAttribute(NoBeds);
		System.out.println("hi" + NoBeds);
		dragAndDrop(RangeSlider, RangeSlider, 150, 0);
		String Beds = bed.getText();
		System.out.println("hi" + Beds);
		if (Beds.equals(NoBeds))
		{
			fail();
		}
		else 
		{
			assertTrue("passed", true);
		}*/
		
		//Tabs
		
		//Default Functionality
		
		//Custom Animation Demo
		
		
		} catch(Exception e) {
			System.out.println("Element Not Found");
			Task3.take(webdriver, "ScreenShot");
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
