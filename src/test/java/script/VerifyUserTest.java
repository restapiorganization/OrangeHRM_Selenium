package script;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AdminPage;
import page.LoginPage;

public class VerifyUserTest extends BaseTest {

    @Test(description = "Verify if 'Admin' user is present in Admin > User Management")
    @Description("Logs in and checks for a user named 'Admin' in the System Users list")
    public void verifyAdminUserIsPresent() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        AdminPage adminPage = new AdminPage(driver);
        adminPage.clickAdminMenu();
        Assert.assertTrue(adminPage.isSystemUsersPageVisible(), "System Users page not visible!");

        adminPage.searchForUser("Admin");
        Assert.assertTrue(adminPage.isUserPresentInTable("Admin"), "User 'Admin' not found in the table!");
    }
}
