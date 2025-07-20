package page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import utils.ExcelUtil;
import utils.WaitUtils;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton   = By.xpath("//button[@type='submit']");

    // Page actions
    public void enterUsername(String username)
    {
        WaitUtils.waitForVisibility(driver, usernameField).sendKeys(username);
    }

    public void enterPassword(String password)
    {
        WaitUtils.waitForVisibility(driver, passwordField).sendKeys(password);
    }

    public void clickLogin() 
    {
        WaitUtils.waitForClickable(driver, loginButton).click();
    }
    public String getTitle() 
    {
        return driver.getTitle();
    }
    
    @Test(description = "Verify Title from Excel")
    @Description("Validates OrangeHRM title matches the expected value from Excel")
    public void verifyTitleFromExcel() {
        LoginPage login = new LoginPage(driver);
        String expectedTitle = ExcelUtil.getCellValue("src/test/resources/testdata.xlsx", "Sheet1", 0, 0);
        Assert.assertEquals(login.getTitle(), expectedTitle, "Title does not match!");
    }

    
}
