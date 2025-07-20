package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AdminPage {
    WebDriver driver;
    Properties props;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/objectrepo.properties");
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load object repository file", e);
        }
    }

    public void clickAdminMenu() {
        By adminMenu = By.xpath(props.getProperty("admin.menu"));
        WaitUtils.waitForClickable(driver, adminMenu).click();
    }

    public boolean isSystemUsersPageVisible() {
        By header = By.xpath(props.getProperty("admin.systemUsersHeader"));
        return WaitUtils.waitForVisibility(driver, header).isDisplayed();
    }

    public void searchForUser(String username) {
        By searchField = By.xpath(props.getProperty("admin.searchUsernameField"));
        By searchButton = By.xpath(props.getProperty("admin.searchButton"));
        WaitUtils.waitForVisibility(driver, searchField).sendKeys(username);
        WaitUtils.waitForClickable(driver, searchButton).click();
    }

    public boolean isUserPresentInTable(String username) {
        String dynamicXPath = props.getProperty("admin.usernameInTable.prefix") + username + props.getProperty("admin.usernameInTable.suffix");
        By userCell = By.xpath(dynamicXPath);
        return WaitUtils.waitForVisibility(driver, userCell).isDisplayed();
    }
}
