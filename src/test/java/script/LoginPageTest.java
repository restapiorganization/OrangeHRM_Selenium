package script;


import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import page.LoginPage;
import utils.ConfigUtil;
import utils.ExcelUtil;

@Epic("OrangeHRM Login Page")
@Feature("Title Verification")
public class LoginPageTest extends BaseTest 
{

    @Test(description = "Verify OrangeHRM Login Page Title from Excel Data")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid Login Page Title")
    @Description("Test Description: Validate the page title of OrangeHRM login page using expected title from Excel sheet")
    public void verifyLoginPageTitle() 
    {
        // Page Object
        LoginPage loginPage = new LoginPage(driver);

        // Excel data
        String expectedTitle = ExcelUtil.getCellValue(ConfigUtil.getProperty("xlPath"), "Sheet1", 0, 0);

        // Logging step in Allure
        Allure.step("Expected Title from Excel: " + expectedTitle);
        Allure.step("Actual Title from Page: " + loginPage.getTitle());

        // Assertion
        Assert.assertEquals(loginPage.getTitle(), expectedTitle, "Page title mismatch!");
        loginPage.enterUsername(ConfigUtil.getProperty("username"));
        loginPage.enterUsername(ConfigUtil.getProperty("password"));
        loginPage.clickLogin();
    }
}
