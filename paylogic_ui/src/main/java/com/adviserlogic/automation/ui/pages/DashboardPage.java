package com.adviserlogic.automation.ui.pages;

import com.adviserlogic.automation.common.config.Properties;
import com.adviserlogic.automation.common.pages.BasePage;
import com.adviserlogic.automation.ui.components.Header;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class DashboardPage extends BasePage {

    public Header header;
    public final String dashboardPageUrl = Properties.getBaseUrl() + "/Apps/DashboardLicensee.aspx";

    @FindBy(partialLinkText = "Upload Data File")
    private WebElement uploadDataFilelink;

    @FindBy(css = "[href *= 'ProductProvider/Index']")
    private WebElement producProviderLinkLeftRail;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Click on upload data file link
     */
    public void goToUploadHistory(){
        uploadDataFilelink.click();
    }

    /**
     * Clicks on product provider link under business setup for navigation to product provider page
     */
    public void goToProductProviderPage(){
        producProviderLinkLeftRail.click();
    }

}
