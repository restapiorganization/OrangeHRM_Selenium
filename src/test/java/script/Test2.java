package script;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic.BaseTest;


public class Test2 extends BaseTest{

	@Test
	public void testB()
	{
		String title = page.title();
		
		test.log(Status.INFO,"testB:"+title);
		test.fail("Failed intensionally");
		
		//Assert.fail();
	}
}
