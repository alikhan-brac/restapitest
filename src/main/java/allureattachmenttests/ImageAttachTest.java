package allureattachmenttests;

import allureattachmenttests.util.FileUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageAttachTest {
    private WebDriver driver;
    private final String screenshotFileName = "src/main/resources/screenshot.jpeg";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://example.com");
    }

    @Attachment(value = "Screenshot", type = "image/jpeg")
    public byte[] attachScreenshot(File screenshot) {
        try {
            return Files.readAllBytes(screenshot.toPath()); // Attach screenshot as byte array
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0]; // Return empty byte array on error
        }
    }

    @Test(description = "checking screenshot attachment with allure")
    public void testScreenshot() {
        File screenshotFile = FileUtil.takeScreenshot(driver, screenshotFileName);
        if (screenshotFile != null) {
            attachScreenshot(screenshotFile); // Attach the screenshot
        }
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
        deleteScreenshot(screenshotFileName);
    }

    private void deleteScreenshot(String fileName) {
        try {
            Files.deleteIfExists(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
