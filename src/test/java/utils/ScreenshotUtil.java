package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil 
{

    // 🔹 Save screenshot to file path
    public static String capture(WebDriver driver, String filePath)
    {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(src.toPath(), new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Unable to save screenshot: " + e.getMessage());
        }
        return filePath;
    }

    // 🔹 Return screenshot as byte stream (for Allure)
    public static ByteArrayInputStream captureAsStream(WebDriver driver) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }
}