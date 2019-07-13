package com.adviserlogic.automation.common.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by kumar.nipun on 5/21/2018
 */
public class PageComponent {

    protected WebDriver driver;
    protected WebElement parent;

    public PageComponent(WebDriver driver) {
        this(driver, null);
    }

    public PageComponent(WebDriver driver, WebElement parent) {
        this.driver = driver;
        this.parent = parent;
    }
}
