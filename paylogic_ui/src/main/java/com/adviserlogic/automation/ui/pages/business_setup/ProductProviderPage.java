package com.adviserlogic.automation.ui.pages.business_setup;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductProviderPage extends BasePage {


    /**
     * Creates product provider page instance
     *
     * @param driver
     */
    public ProductProviderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".seachWithIcon>input")
    private WebElement searchBox;

    @FindBy(css = "#pgridScrollHeight>div")
    private List<WebElement> productProviderRows;

    @FindBy(css = "[class *= 'fa-ellipsis-v']")
    private WebElement optionsIcon;

    @FindBy(css = "[class *= 'pName']")
    private WebElement productProviderName;


    @FindBy(css = "[class = 'rightNav']>ul>li:nth-of-type(4)>a")
    private WebElement defineSchemaLink;

    /**
     * Searches for the product provider and navigates to the corresponding define schema page
     *
     * @param productProvider
     */
    public void searchProductProviderAndGoToDefineSchemaPage(String productProvider) throws InterruptedException {
        searchBox.sendKeys(productProvider);
        Thread.sleep(3000);
        if (productProviderName.getText().equals(productProvider)) {
            optionsIcon.click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementsByClassName('rightNav')[0].children[0].children[3].children[0].click();");
        Thread.sleep(5000);
        }
    }

}
