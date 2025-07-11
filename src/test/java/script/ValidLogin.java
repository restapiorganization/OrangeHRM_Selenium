package script;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.FileUtil;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import page.HomePage;
import page.LoginPage;

public class ValidLogin extends BaseTest{

	@Test(groups= {"regression"})
	  @Description("Verify login with valid credentials")
	  @Severity(SeverityLevel.CRITICAL)
	  @Epic("Authentication")
	  @Feature("Login")
	  @Story("Valid Login")

	public void testValidLogin()
	{
		test.info("Read Test Data from excel file");
		String un = FileUtil.getXlData(xlPath, "ValidLogin", 1, 0);
		String pw = FileUtil.getXlData(xlPath, "ValidLogin", 1, 1);
		
		Allure.step("Navigate to login page");
		test.info("1. enter valid user name:"+un);
		LoginPage loginPage=new LoginPage(page);
		loginPage.setUserName(un);
		
		test.info("2. enter valid password:"+pw);
		loginPage.setPassword(pw);
		
		test.info("3. click go button");
		loginPage.clickGoButton();
		
		test.info("4. verify that home page displayed");
		HomePage homePage=new HomePage(page);
		boolean result = homePage.verifyHomePageIsDisplayed();
		Assert.assertTrue(result);
		test.pass("home is page displayed");
		test.skip("Skipped for refe");
		test.fail("SRR");
		test.addScreenCaptureFromPath(xlPath);
	}
}
