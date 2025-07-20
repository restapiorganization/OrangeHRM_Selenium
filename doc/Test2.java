package script;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic.BaseTest;
import page.HomePage;


public class Test2 extends BaseTest{

	@Test
	public void testB()
	{
		String title = driver.getTitle();
		
		test.log(Status.INFO,"testB:"+title);
		
		
		//Assert.fail();
	}
	
}
