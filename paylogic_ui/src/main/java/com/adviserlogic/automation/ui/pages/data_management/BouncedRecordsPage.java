package com.adviserlogic.automation.ui.pages.data_management;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BouncedRecordsPage extends BasePage {

    @FindBy(css = "#searchBounceRecords")
    private WebElement searchTextBox;

    @FindBy(css = "#tblBounceRecords_wrapper [type = 'checkbox']")
    private List<WebElement> feeTypecheckboxes;

    @FindBy(css = "#lstBounceRecords>tr>td:nth-of-type(9)")
    private List<WebElement> bouncedRecordsTableFeeTypeList;

    @FindBy(css = "button[onClick = 'fnsearchBounceRecords()']")
    private WebElement searchButton;

    @FindBy(css = ".box_right>#btnMap")
    private WebElement mapButton;

    @FindBy(css = ".ui-multiselect>span:nth-of-type(2)")
    private WebElement feeTypeNameDropdownMappingDialog;

    @FindBy(css = ".ui-multiselect-checkboxes [type = 'checkbox']")
    private WebElement feeTypeCheckboxMappingDialog;

    @FindBy(css = ".modal-footer>[onclick = 'MapFeeTpeNSubProduct()']")
    private WebElement saveButtonFeeTypeMappingDialog;

    @FindBy(css = "a[href *= '/Apps/UploadHistory.aspx']")
    private WebElement uploadHistoryPageLink;

    @FindBy(css = "#spnUserName")
    private WebElement userProfileLink;

    @FindBy(css = "#btnSignOut")
    private WebElement signOutButton;

    public BouncedRecordsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Logs out from the presently logged in profile
     */
    public void logOut(){
        userProfileLink.click();
        signOutButton.click();
    }

    /**
     * Maps all bounced records fee type taken from upload history page
     * @param feetTypes
     */
    public void mapFeeTypes(Set<String> feetTypes) throws InterruptedException {
        List<String> feeTypeList = new LinkedList<>();
        feeTypeList.addAll(feetTypes);
        for(int i = 0; i <= feeTypeList.size()-1; i++){
            searchTextBox.clear();
            Thread.sleep(3000);
            searchTextBox.sendKeys(feeTypeList.get(i));
            Thread.sleep(1000);
            searchButton.click();
            if(bouncedRecordsTableFeeTypeList.get(0).getText().equals(feeTypeList.get(i))){
                Thread.sleep(3000);
                feeTypecheckboxes.get(0).click();
                Thread.sleep(5000);
                mapButton.click();
                Thread.sleep(3000);
                feeTypeNameDropdownMappingDialog.click();
                Thread.sleep(3000);
                feeTypeCheckboxMappingDialog.click();
                Thread.sleep(3000);
                saveButtonFeeTypeMappingDialog.click();
                Thread.sleep(10000);
            }
        }
    }

    /**
     * Clicks on upload history page link displayed in the left rail
     */
    public void goToUploadHistoryPage(){
        uploadHistoryPageLink.click();
    }
}
