package com.adviserlogic.automation.ui.pages.data_management;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UnMappedAccountsPage extends BasePage {

    public UnMappedAccountsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(partialLinkText = "UnMapped Accounts")
    private WebElement goToUnMappedAccountsPage;

    @FindBy(css=".col-md-4.col-sm-6.col-xs-12.box_right")
    private WebElement unMappedSearchBox;

    @FindBy(css=".col-md-4.col-sm-6.col-xs-12.box_right>div>input")
    private WebElement searchBoxTextInput;

    @FindBy(css=".col-md-4.col-sm-6.col-xs-12.box_right>div>span")
    private WebElement search;

    @FindBy(css="#tblOrphanRecords_wrapper>table>tbody>tr>td")
    private List<WebElement> advisorCodeAndPPVerify;

    @FindBy(css=".box_right>#btnMap")
    private WebElement mapAccount;

    @FindBy(css=".modal.fade.in>.modal-dialog>.modal-content.ui-draggable.ui-draggable-handle")
    private WebElement mapDialog;

    @FindBy(css=".modal-body_MyStyle>#divAdviserMap>#ddlMapWith")
    private WebElement mapWithOptions;

    @FindBy(css="#divAdviserExist>#txtAdviserId")
    private WebElement mapWithAdviserName;

    @FindBy(css="button#btnSaveMapping")
    private WebElement mapDialogSaveButton;

    @FindBy(css=".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content>li")
    private List<WebElement> selectAdviser;

    /**
     * Verify whether search box for unMapped is displayed or not
     * @return
     */
    public boolean searchBox()
    {
        return  unMappedSearchBox.isDisplayed();
    }

    /**
     * Navigate to unMapped page
     */
    public void goToUnMappedPage()
    {
        goToUnMappedAccountsPage.click();
    }

    /**
     * Extract adviser codes to be mapped
     * @param adviserCodes
     * @return
     */
    public List<String > advisorCodeForMapping(Set<String> adviserCodes)
    {
        List<String> codes= adviserCodes.stream().collect(Collectors.toList());
        return codes;
    }

    /**
     * Click search button to search for adviser codes
     */
    public void clickOnSearch()
    {
        search.click();
    }

    /**
     * Map adviser codes and return mapped codes
     * @param advisorCodeForMapping
     * @param productProvider
     * @return
     * @throws InterruptedException
     */
    public List<String> searchAdvisorCodes(List<String> advisorCodeForMapping,String productProvider,String partialAdviserNameForMapping) throws InterruptedException {
        int count =0;
        String adviserCode = null;
        List<String> mappedAdviserCodes = new LinkedList<>();
        for(int i=0;i<advisorCodeForMapping.size();i++){
            Thread.sleep(1000);
            searchBoxTextInput.click();
            Thread.sleep(1000);
            adviserCode=advisorCodeForMapping.get(i);
            searchBoxTextInput.sendKeys(adviserCode);
            clickOnSearch();
            selectToMap(productProvider,advisorCodeForMapping.get(i));
            mapAccount();
            if(adveriserCodeMapDialog())
            { mapOptions(partialAdviserNameForMapping);
              Thread.sleep(5000);}
            count=count +1;
            mappedAdviserCodes.add(advisorCodeForMapping.get(i));
            Thread.sleep(5000);
            searchBoxTextInput.click();
            Thread.sleep(1000);
            searchBoxTextInput.clear();
            clickOnSearch();
            if(count==2) { break; }
        }
        return mappedAdviserCodes;
    }

    /**
     * Click on check box to map adviser codes
     * @param productProvider
     * @param advisorCode
     * @throws InterruptedException
     */
    public void selectToMap(String productProvider,String advisorCode) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> codeToMap = advisorCodeAndPPVerify;
            if(codeToMap.get(2).getText().equals(productProvider) && codeToMap.get(4).getText().equals(advisorCode))
            {
                codeToMap.get(1).click();
            }
    }

    /**
     * Map Account Button is clicked
     */
    public void mapAccount() throws InterruptedException {
        Thread.sleep(1000);
        mapAccount.click();
    }

    /**
     * Confirm on visibility of map dialog
     * @return
     */
    public boolean adveriserCodeMapDialog()
    {
        return  mapDialog.isDisplayed();
    }

    /**
     * Map Adviser codes with some adviser
     * @throws InterruptedException
     */
    public void mapOptions(String partialAdviserNameForMapping) throws InterruptedException {
        Thread.sleep(1000);
        Select mapOptions = new Select(mapWithOptions);
        mapOptions.selectByIndex(1);
        Thread.sleep(1000);
        Select mapAdviserName = new Select(mapWithOptions);
        mapWithAdviserName.sendKeys(partialAdviserNameForMapping);
        if(selectAdviser.isEmpty())
        {
            mapWithAdviserName.sendKeys(partialAdviserNameForMapping);
            selectAdviser.get(0).click();
        }
        else{
            Thread.sleep(1000);
        selectAdviser.get(0).click();}
        mapDialogSaveButton.click();
    }

}
