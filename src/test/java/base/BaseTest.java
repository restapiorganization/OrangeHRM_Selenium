package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.qameta.allure.Step;
import utils.ConfigUtil;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        ConfigUtil.loadConfig();
    }

    @BeforeMethod
    @Step("Launching browser and navigating to site")
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigUtil.getProperty("url"));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
