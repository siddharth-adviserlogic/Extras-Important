package com.adviserlogic.automation.ui.pages.business_setup;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LicenseeReferrersPage extends BasePage {

    @FindBy(css = "#tblLicenseeReferrers_filter input")
    private WebElement searchTxtBx;

    @FindBy(css = "#lstLicenseeReferrers tr")
    private List<WebElement> licenseeReferrerTableRows;

    By licenseeReferrerName = By.cssSelector("td");

    By goToActionsLink = By.cssSelector("a");

    @FindBy(css = "#divAction2>.no_wrap")
    private WebElement splitFeeTab;

    @FindBy(css = "#divAction0>div.no_wrap")
    private WebElement informationTab;

    @FindBy(css = "#lstPayoutRules>tr")
    private List<WebElement> ruleRowCount;

    @FindBy(css = "#divProductProvider_0 button")
    private WebElement productProviderButton;

    @FindBy(css = ".skin-blue.fixed.pace-done>div:nth-of-type(11) .ui-multiselect-filter>input")
    private WebElement productProviderFilterTxtBx;

    @FindBy(css = ".skin-blue.fixed.pace-done>div:nth-of-type(10) .ui-multiselect-filter>input")
    private WebElement referredClientFilterTxtBx;

    @FindBy(css = "#ui-multiselect-ddlProductProvider_0")
    private List<WebElement> productProviderDropDown;

    @FindBy(css = "#ui-multiselect-ddlReferredClient_0")
    private List<WebElement> referredClientDropDown;

    @FindBy(css = "[for *= 'ui-multiselect-ddlReferredClient_0-option']")
    private List<WebElement> referredClientsDropdownList;

    By values = By.cssSelector("span");

    By checkBox = By.cssSelector("input");

    @FindBy(css = "div.form-group>#ddlFeeType_0")
    private WebElement feeTypeDropDown;

    @FindBy(css = "#divReferredClient_0 button")
    private WebElement referredClientButton;

    @FindBy(css = "div>#txtDefaultSplitRatio")
    private WebElement defaultSplitRatioTxtBx;

    @FindBy(css = "div>#txtSplitRatio_0")
    private WebElement splitRatioTxtBx;

    @FindBy(css = "#tblLicenseeReferrers_filter input")
    private WebElement licenseeReferrerTableTxtBx;

    @FindBy(css = "[onclick = 'reloadSplitFees();']")
    private WebElement backButton;

    @FindBy(css = "#liLicenseeReferrer>a")
    private WebElement licenseeReferrersLink;

    @FindBy(css = "[href *= '/Apps/productProcessLayout.aspx?isfor=enlicensee']")
    private WebElement processPayoutPageLink;

    public LicenseeReferrersPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @author Ankita Kulshrestha
     * @param clientsMappedWithReferrer is a List of HashMap which consist of clients data for which Split Fee need to check.
     * @param ProductProviderName is the product provider for which file was uploaded in Upload Data File.
     * @throws InterruptedException
     *
     * @Scenario Check Split Fee for each Client Account Code in List of HasMap. If Split Fee rule exist then get the Split Ratio of that rule, else get
     * default split ratio for that client account code.
     */
    public List<LinkedHashMap<String, String>> getSplitRatioForClients(List<LinkedHashMap<String,String>> clientsMappedWithReferrer,
                                        String ProductProviderName) throws InterruptedException {
        String splitRatio="";
        Thread.sleep(3000);

        //Iterate for each HashMap
        for (int i = 0; i < clientsMappedWithReferrer.size(); i++) {
            licenseeReferrerTableTxtBx.sendKeys(clientsMappedWithReferrer.get(i).get("Referrer Name"));
            Thread.sleep(2000);
            //Iterate for each row in Licensee referrer table
            for (int row = 0; row < licenseeReferrerTableRows.size(); row++) {
                if (licenseeReferrerTableRows.get(row).findElements(licenseeReferrerName).get(0).getText().equals(
                        clientsMappedWithReferrer.get(i).get("Referrer Name"))) {
                    licenseeReferrerTableRows.get(row).findElement(goToActionsLink).click();
                    Thread.sleep(2000);
                    //Go to split fee tab
                    splitFeeTab.click();
                    Thread.sleep(3000);
                    //Iterate for each row in Split Fee table to check each rule.
                    for (int ruleCount = 0; ruleCount < ruleRowCount.size(); ruleCount++) {
                        ruleRowCount.get(ruleCount).click();
                        //Calling readRule method to read each rule.
                        splitRatio = readRule(clientsMappedWithReferrer,i,ProductProviderName);
                        if(!splitRatio.equals("")){
                            break;
                        }
                        else{
                            backButton.click();
                            Thread.sleep(4000);
                            continue;
                        }
                    }
                    if(!splitRatio.equals("")){
                        licenseeReferrersLink.click();
                        Thread.sleep(4000);
                    }
                    else {
                        informationTab.click();
                        Thread.sleep(5000);
                        splitRatio = defaultSplitRatioTxtBx.getAttribute("value");
                        licenseeReferrersLink.click();
                        Thread.sleep(3000);
                    }
                }
                clientsMappedWithReferrer.get(i).put("Split Ratio", splitRatio );
            }
        }
        return clientsMappedWithReferrer;
    }

    /**
     *
     * @param clientsMappedWithReferrer is a List of HashMap which consist of clients data for which Split Fee need to check.
     * @param count to iterate in the same HashMap of clientsMappedWithReferrer which is in getSplitRatioForClients() method.
     * @param ProductProviderName is the product provider for which file was uploaded in Upload Data File.
     * @return
     * @throws InterruptedException
     *
     * @Scenario Compare Product Provider, Fee type and Client Account Code from List of HashMap i.e. clientsMappedWithReferrer in each Split Fee Rule for
     * each client. If these data get match then return Split ratio of that that Rule else return a blank Split Ratio.
     */

    public String readRule(List<LinkedHashMap<String,String>> clientsMappedWithReferrer,
                           int count,String ProductProviderName) throws InterruptedException {
        String splitRatio="";
        String clientAccountCode = "";
        //Comparing the Product Provider from clientsMappedWithReferrer with Product Provider drop down.
        productProviderButton.click();
        productProviderFilterTxtBx.sendKeys(ProductProviderName);
        Thread.sleep(2000);
        for (int j = 0; j < productProviderDropDown.size(); j++) {
            //Check the product Provider name in drop down and is it selected or not.
            if (productProviderDropDown.get(j).findElement(values).getText().equals(
                    ProductProviderName) && productProviderDropDown.get(j).findElement(
                    checkBox).isSelected()) {
                productProviderButton.click();
                Select sel = new Select(feeTypeDropDown);
                //Compare the Fee type from Fee Type drop down.
                if (sel.getFirstSelectedOption().getText().equals(clientsMappedWithReferrer.get(count).get("Fee type"))
                        || sel.getFirstSelectedOption().getText().equals("All")) {
                    //Compare the referred clients from Referred Client drop down.
                    referredClientButton.click();
                    referredClientFilterTxtBx.sendKeys(clientsMappedWithReferrer.get(count).get("Client Account Code"));
                    Thread.sleep(2000);
                    for (int k = 0; k < referredClientsDropdownList.size(); k++) {
                        //Get client account code from the drop down values to compare with Client Account Code of List of
                        // HasMap clientsMappedWithReferrer
                        String clientNameClientAccountCode = referredClientsDropdownList.get(k).findElement(values).getText();
                        if (!clientNameClientAccountCode.equals("")) {
                            if (clientNameClientAccountCode.split("\\(")[1].replace(")", "").equals(
                                    clientsMappedWithReferrer.get(count).get("Client Account Code")) && referredClientsDropdownList.get(k).findElement(
                                    checkBox).isSelected()) {
                                referredClientButton.click();
                                Thread.sleep(2000);
                                //get the split ratio from Split Ratio Text box.
                                splitRatio = splitRatioTxtBx.getAttribute("value");
                                break;
                            }
                        }
                    }
                    break;
                }
                break;
            }
        }
        return splitRatio;
    }

    /**
     * Navigates user to process payout page by clicking on process payout link displayed in left rail under payout data
     */
    public void goToProcessPayoutPage(){
        processPayoutPageLink.click();
    }
}