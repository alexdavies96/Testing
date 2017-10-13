package AutomatedTesting.AutomatedTesting;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Task1 {

	@BeforeClass
	public static void beforeClass()
	{
		System.out.println("Before Class");
	}
	@Before
	public void beforeMeth()
	{
		System.out.println("Before Method");
	}
	
	@Test
	public void Test1()
	{
		System.out.println("Test");
	}
	
	@After
	public void AfterMeth()
	{
		System.out.println("After method");
	}
	
	@AfterClass
	public static void afterClass()
	{
		System.out.println("After class");
	}
}
