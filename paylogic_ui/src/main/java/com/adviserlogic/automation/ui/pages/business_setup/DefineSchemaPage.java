package com.adviserlogic.automation.ui.pages.business_setup;

import com.adviserlogic.automation.common.config.Properties;
import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;

public class DefineSchemaPage extends BasePage {

    @FindBy(css = ".form-group>#ddlSchemaName")
    private WebElement schemaNameDropdown;

    @FindBy(css = ".form-group>#ddlSchemaName>option")
    private List<WebElement> schemaNameDropdownOptionsList;

    @FindBy(css = "#divFeeTypes>div.no_wrap")
    private WebElement feeTypeTab;

    @FindBy(css = "#lstFeeTypes>tr>td:nth-of-type(1)")
    private List<WebElement> feeTypeNames;

    @FindBy(css = "#DeleteFeeTypeBtn>#btnDeleteFeeType")
    private WebElement feeTypeDeleteButton;

    @FindBy(css = ".btn_confirm_first>#btnconfirm")
    private WebElement confirmDeletebutton;

    /**
     * Creates an instance of Define Schema page
     * @param driver
     */
    public DefineSchemaPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selects the schema name from dropdown
     * @param productProviderName
     * @throws Exception
     */
    public void selectSchemaFromDropdown(String productProviderName) throws Exception {
        schemaNameDropdown.click();
        Thread.sleep(3000);
        for(int i = 0; i <= schemaNameDropdownOptionsList.size()-1; i++){
            if(schemaNameDropdownOptionsList.get(i).getText().equals(Properties.getSchemaName(productProviderName))){
                Actions act = new Actions(driver);
                act.moveToElement(schemaNameDropdownOptionsList.get(i)).perform();
                schemaNameDropdownOptionsList.get(i).click();
                break;
            }
            else if(i == schemaNameDropdownOptionsList.size()-1 && !schemaNameDropdownOptionsList.get(i).getText().equals(
                    Properties.getSchemaName(productProviderName))){
                throw new Exception("Schema Name not found in dropdown");
            }
            else{
                continue;
            }
        }
    }

    /**
     * Clicks on Fee Type tab
     */
    public void selectFeeTypeTab(){
        feeTypeTab.click();
    }

    /**
     * Deletes all fee mappings for bounced records
     * @param feeType
     * @param productProvider
     * @throws Exception
     */
    public void deleteFeeMapping(List<String> feeType, String productProvider) throws Exception {
        for(int i = 0; i <= feeType.size()-1; i++){
            for(int j = 0; j <= feeTypeNames.size()-1; j++){
                if(feeTypeNames.get(j).getText().equalsIgnoreCase(feeType.get(i))){
                    feeTypeNames.get(j).click();
                    Thread.sleep(2000);
                    feeTypeDeleteButton.click();
                    Thread.sleep(3000);
                    confirmDeletebutton.click();
                    Thread.sleep(2000);
                }
            }
        }
    }

}
