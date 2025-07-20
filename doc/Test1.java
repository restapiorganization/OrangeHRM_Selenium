package script;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import generic.BaseTest;
import generic.FileUtil;
import page.LoginPage;

public class Test1 extends BaseTest {

    @Test
    public void testA() {
        // 🔹 Read value from Excel
        String value = FileUtil.getXlData("./data/input.xlsx", "sheet1", 0, 0);
        test.log(Status.INFO, "xl data: " + value);

        // 🔹 Capture and log page title
        String title = driver.getTitle();
        test.log(Status.INFO, "testA: " + title);

        // 🔹 Interact with LoginPage
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName(value);

        // 🔹 Optional delay to mimic page.waitForTimeout()
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            test.warning("Sleep interrupted: " + e.getMessage());
        }
    }
}