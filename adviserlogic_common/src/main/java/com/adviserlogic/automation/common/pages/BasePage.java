package com.adviserlogic.automation.common.pages;

import com.adviserlogic.automation.common.config.BaseRunner;
import org.openqa.selenium.WebDriver;

/**
 * Created by kumar.nipun on 5/17/2018
 */
public abstract class BasePage implements IPage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

  //  @Override
    public void refreshPage() {
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
    }

    //@Override
    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }
}
