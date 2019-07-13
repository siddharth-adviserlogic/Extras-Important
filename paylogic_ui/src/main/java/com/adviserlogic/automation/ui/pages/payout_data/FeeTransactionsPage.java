package com.adviserlogic.automation.ui.pages.payout_data;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FeeTransactionsPage extends BasePage {

    public FeeTransactionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="#ulLeftPanel>li:nth-of-type(4)>ul>li:nth-of-type(1)>a")
    private WebElement feeTransactionPage;

    @FindBy(css="#searchPayout #txtFDate")
    private WebElement forDate;

    @FindBy(css=".datepicker-days>table>tbody>tr>td[class='day']")
    private List<WebElement> calenderDates;

    @FindBy(css="#DivCompulsaryDate #btnSearchPayouts")
    private WebElement searchTransactions;

    @FindBy(css="#dvFeetran #searchPayouts")
    private WebElement searchTransactionsTextBox;

    @FindBy(css="#dvFeetran .btn.btn-default")
    private WebElement searchTransactionsButton;

    @FindBy(css="#lstFeeTransactions>tr:nth-of-type(1)>td>input")
    private WebElement clickProductProviderCheckBox;

    @FindBy(css=".col-xs-12 #btnUnmap")
    private WebElement selectUnMap;

    @FindBy(css=".row.PayoutSearch>div:nth-of-type(3) button")
    private WebElement productProviderFilter;

    @FindBy(css=".ui-multiselect-menu.ui-widget.ui-widget-content.ui-corner-all .ui-helper-reset>li:nth-of-type(2)>a")
    private List<WebElement> unCheckAllButton;

    @FindBy(css=".ui-multiselect-menu.ui-widget.ui-widget-content.ui-corner-all .ui-multiselect-filter>input")
    private List<WebElement> keywordFilter;

    @FindBy(css="#ui-multiselect-ddlProductProviders input")
    private List<WebElement> selectProductProvider;

    @FindBy(css="#confirmBox:nth-of-type(1) input:nth-of-type(1)")
    private WebElement confirmRemoveAdviserCodeButton;

    /**
     * Navigate to fee transaction page
     */
    public void goToFeeTransactionPage()
    {
        feeTransactionPage.click();
    }

    /**
     * Select specific product provider by removing all other selected product provider
     * @param productProvider
     */
    public void selectCurrentProductProvider(String productProvider) throws Exception {
        productProviderFilter.click();
        Thread.sleep(3000);
        try {
            for(WebElement uncheck:unCheckAllButton)
                    if(uncheck.isDisplayed()){
                        uncheck.click();
                }
        }
        catch (Exception e){
            throw  new Exception("Uncheck button is not displayed");
        }
        try{
            for(WebElement SearchBox: keywordFilter)
                if(SearchBox.isDisplayed()){
                    SearchBox.sendKeys(productProvider);}
        }
        catch (Exception e)
        {throw  new Exception("Search Box is not displayed");}
        Thread.sleep(2000);
        selectProductProvider.get(0).click();
    }

    /**
     * Clicks on Search Transaction link
     */
    public void clickOnSearchTransactions()
    {
        searchTransactions.click();
    }


    /**
     *  Select from Date From dropdown
     */
    public void selectForDate()
    {
        forDate.click();
        for(WebElement element:calenderDates)
        {
            if(Integer.valueOf(element.getText()).equals(LocalDate.now().getDayOfMonth()))
            {
                element.click();
                break;
            }
        }
    }

    /**
     *
     * Clean up code which umap with mapped adviser codes
     * @param mappedAdvisers
     * @param productProvider
     * @throws InterruptedException
     */
    public void cleanUpMappedAdviser(List<String> mappedAdvisers,String productProvider) throws Exception {
        goToFeeTransactionPage();
        Thread.sleep(5000);
        selectCurrentProductProvider(productProvider);
        Thread.sleep(3000);
        selectForDate();
        clickOnSearchTransactions();
        for(String adviserCode: mappedAdvisers)
        {
            searchTransactionsTextBox.sendKeys(adviserCode);
            searchTransactionsButton.click();
            Thread.sleep(2000);
            clickProductProviderCheckBox.click();
            Thread.sleep(1000);
            selectUnMap.click();
            confirmRemoveAdviserCodeButton.click();
            Thread.sleep(6000);
        }
    }
}
