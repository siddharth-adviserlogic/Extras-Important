package com.adviserlogic.automation.common.config;

import com.adviserlogic.automation.common.config.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;


/**
 * Created by kumar.nipun on 5/17/2018
 */
public abstract class BaseRunner {

    public static WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        String browser = Properties.getBrowserName().toLowerCase();
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", Properties.getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", Properties.getFirefoxDriverPath());
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("No such browser supported: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.switchTo().defaultContent();
            driver.close();
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Problem in getting driver. Found null, see the application logs for more info");
        }
        return driver;
    }
}
