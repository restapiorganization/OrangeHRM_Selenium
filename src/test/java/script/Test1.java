package script;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic.BaseTest;
import generic.FileUtil;
import page.LoginPage;

public class Test1 extends BaseTest{

	@Test
	public void testA()
	{
		String v=FileUtil.getXlData("./data/input.xlsx", "sheet1", 0, 0);
		test.log(Status.INFO,"xl data:"+v);
		String title = page.title();
		test.log(Status.INFO,"testA:"+title);
		LoginPage loginPage=new LoginPage(page);
		loginPage.setUserName(v);
		page.waitForTimeout(1000);
	}
}
