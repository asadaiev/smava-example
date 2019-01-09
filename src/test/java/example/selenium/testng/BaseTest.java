package example.selenium.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import example.common.Configuration;

public class BaseTest {

    WebDriver driver;

    @BeforeMethod
    void setUp() {
        Configuration config = Configuration
                .getInstance(System.getProperty("testTragetEnv", "config"));
        System.setProperty("webdriver.chrome.driver", config.getProperty("web.driver.location"));
        driver = new ChromeDriver();

        //Maximize Window
        driver.manage().window().maximize();
    }

    @AfterMethod
    void tearDown() {
        driver.close();
    }
}
