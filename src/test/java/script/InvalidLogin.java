package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.FileUtil;
import page.HomePage;
import page.LoginPage;

public class InvalidLogin extends BaseTest
{

		@Test(groups= {"regression"})
		public void testInvalidLogin()
		{
			test.info("Read Test Data from excel file");
			String un = FileUtil.getXlData(xlPath, "InValidLogin", 1, 0);
			String pw = FileUtil.getXlData(xlPath, "InValidLogin", 1, 1);
			
			test.info("1. enter invalid user name:");
			LoginPage loginPage=new LoginPage(page);
			loginPage.setUserName(un);
			
			test.info("2. enter invalid password:");
			loginPage.setPassword(pw);
			
			test.info("3. click go button");
			loginPage.clickGoButton();
			//added
			test.info("4. verify that err msg is displayed");
			boolean result = loginPage.verifyErrMsgIsDisplayed();
			Assert.assertTrue(result);
			test.pass("Err Msg Is displayed for invalid credentials");
			
		}
}
