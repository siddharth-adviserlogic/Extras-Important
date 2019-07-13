package com.adviserlogic.automation.ui.pages.business_setup;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientPage extends BasePage {

    public ClientPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name = 'search']")
    WebElement searchField;

    @FindBy(css = ".nameWrap>div")
    WebElement clientNameField;

    @FindBy(css = ".logoNameWrap>div:nth-child(2)")
    WebElement productProviderNameField;

    @FindBy(css = ".rShape>span")
    WebElement clientRuleLogo;

    @FindBy(css = "div#mgridScrollHeight>div")
    List<WebElement> clientTableRows;

    By clientTableColumns = By.cssSelector("div>div");
    By optionsIcon = By.cssSelector("div:nth-of-type(7)>i");

    @FindBy(css = "input[type=search]")
    WebElement manageClientSearchBox;
    @FindBy(css = "div.fontB.ng-binding")
    WebElement clientName;

    @FindBy(css = "div.gridRow.col-sm-12.pl-no-paddingLR div:nth-child(3)")
    WebElement clientCode;

    @FindBy(css = "div.gridCell.col-sm-2.ng-binding")
    WebElement clientReferrerName;

    @FindBy(css = "div.plFloatL.pl-marginB.ng-binding")
    WebElement productProviderName;

    By addRuleLink = By.cssSelector("div:nth-of-type(7)>div>ul>li:nth-of-type(2)>a");

    @FindBy(css = ".manageWrap .gridParent.col-sm-12.pl-no-paddingLR.ng-scope")
    List<WebElement> clientsTableRows;

    By clientsTableColumns = By.cssSelector("div>div.ng-binding");

    By adviserGroupsLinkBusinessSetup = By.cssSelector("a[href *= '/Apps/AdviserGroups.aspx']");


    @FindBy(css=".advancedSearch [name='search']")
    private WebElement clientSearchBox;

    @FindBy(css=".gridParent .gridRow")
    private List<WebElement> clientSearchedRows;

    @FindBy(css=".rBar")
    private WebElement ruleBar;

    @FindBy(css=".fa.fa-ellipsis-v")
    private WebElement addRuleNavigation;

    @FindBy(css="[class*='rightNav arrow']>ul>li:nth-of-type(2)>a")
    private WebElement addRule;

    @FindBy(css=".rulesList>div")
    private List<WebElement> rulesList;

    @FindBy(css=".rulesList>div .faIconWrap a")
    private List<WebElement> selectRule;

    @FindBy(css=".panelFormBody div:nth-of-type(2) button")
    private WebElement productProviderDropDwnBtn;

    @FindBy(css=".panelFormBody div:nth-of-type(2) .msdd-search input")
    private WebElement searchProductProvider;

    @FindBy(css=".panelFormBody div:nth-of-type(2) ul li[class*='selected'] label")
    private List<WebElement> productProviderDropDownLabels;

    @FindBy(css=".panelFormBody div:nth-of-type(2) ul li[class*='selected'] label input")
    private List<WebElement> productProviderDropDownCheckBox;

    @FindBy(css=".panelFormBody div:nth-of-type(3) button")
    private WebElement accountCodeButton;

    @FindBy(css=".panelFormBody div:nth-of-type(3) ul li[class*='selected'] label")
    private List<WebElement> accountCodeDropDownLabels;

    @FindBy(css=".panelFormBody div:nth-of-type(3) ul li[class*='selected'] label input")
    private List<WebElement> accountCodeDropDownCheckBox;

    @FindBy(css=".panelFormBody div:nth-of-type(4) select ")
    private WebElement feeTypeOption;

    @FindBy(css="#dvSplits div:nth-of-type(1) .splitBox select")
    private List<WebElement> advisersNamesForSpiltRatio;

    @FindBy(css="#dvSplits div:nth-of-type(1) .splitBox input")
    private List<WebElement> advisersSpiltRatioValue;

    @FindBy(css=".pageHeading .fa.fa-angle-left")
    private WebElement clientPageToManageClient;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(6)")
    private WebElement businessSetupLink;

    By clientTableFields = By.cssSelector(".gridCell");

    @FindBy(css= "#dvPayoutRules>div i")
    private  List<WebElement> rule;

    @FindBy(css=".col-sm-4.pl-no-paddingL:nth-of-type(3) div>input")
    private WebElement searchAccountCode;

    @FindBy(css=".col-sm-4.pl-no-paddingL:nth-of-type(2) li>label")
    private List<WebElement> productProviderValue;

    By productProviderCheckBox =By.cssSelector("input");

    @FindBy(css=".col-sm-4.pl-no-paddingL:nth-of-type(3) li>label")
    private List<WebElement> accountCodeValue;

    By accountCodeCheckBox =By.cssSelector("input");

    @FindBy(css=".col-sm-6.pl-no-paddingL:nth-of-type(2) input")
    private WebElement splitRatioTxtBx;

    @FindBy(css="#collapse1 > div > div:nth-child(3) > div > div > button > span")
    private WebElement accountCodeDropDwn;

    @FindBy(css="#ddlFeeType")
    private WebElement FeeTypeDropDwn;

    @FindBy(css = ".pageHeading i")
    private WebElement backButton;

    By licenseeReferrersLink = By.cssSelector("a[href *= '/LicenseeReferrers']");






    public WebElement getTableRow(int rowNumber){
        String locatorToRow = clientTableRows.get(rowNumber).toString().split(
                "css selector: ")[1].replace("]", "") + ":nth-of-type(" + rowNumber + ")";
        return driver.findElement(By.cssSelector(locatorToRow));
    }

    public List<WebElement> getClientTableColumns(int row){
        List<WebElement> columnLocator = getTableRow(row).findElements(clientTableColumns);
        return columnLocator;
    }

    public WebElement getOptionsIcon(int row){
        return getTableRow(row).findElement(optionsIcon);
    }

    public WebElement getAddRuleLink(int row){
        return getTableRow(row).findElement(addRuleLink);
    }

    public void searchClientNameAndAddRule(String clientName, String productProviderName, String clientCode) {
        searchField.sendKeys(clientName);
        searchField.sendKeys(Keys.ENTER);
        Set tableColumns = new HashSet();
        if (clientTableRows.size() - 2 > 1) {
            for (int j = 1; j < clientTableRows.size() - 2; j++) {
                tableColumns = getClientTableColumns(j).stream().map(m -> m.getText()).collect(Collectors.toSet());
                if (tableColumns.contains(clientName) && tableColumns.contains(productProviderName) &&
                        tableColumns.contains(clientCode)) {
                    if (clientRuleLogo.isDisplayed()) {
                        getOptionsIcon(j).click();
                        getAddRuleLink(j).click();
                        break;
                    }
                }
            }
        } else {
            if (tableColumns.contains(clientName) && tableColumns.contains(productProviderName) &&
                    tableColumns.contains(clientCode)) {
                if (clientRuleLogo.isDisplayed()) {
                    getOptionsIcon(1).click();
                    getAddRuleLink(1).click();
                }
            }
        }
    }

    /**
     * @author Ankita Kulshrestha
     * @param requiredDataFromProcessPayoutPage is a List<HashMap<String,String>> which consist of Client Name, Client Account Code,
     * Fee Type, Adviser Group Name and Adviser Group ARP for each client.
     * @param ProductProvider is the name of product provider for which file was uploaded.
     * @Scenario Get client account code from each HashMap and Search in Clients Page. Compare Client
     * Name and Product Provider which are in the List for that client account code with that of Clients page
     * table and then check for referrer mapping for that client account code. If referrer is not mapped then
     * remove that List from List<List<String>>.
     */
    public List<LinkedHashMap<String,String>> checkReferrerMappingWithClients(List<LinkedHashMap<String,String>> requiredDataFromProcessPayoutPage, String ProductProvider) throws InterruptedException {
        List<LinkedHashMap<String,String>> clientsMappedWithReferrer = new LinkedList<LinkedHashMap<String,String>>();
        for(int i=0; i < requiredDataFromProcessPayoutPage.size();i++) {
            searchField.clear();
            searchField.sendKeys(requiredDataFromProcessPayoutPage.get(i).get("Client Account Code"));
            searchField.sendKeys(Keys.ENTER);
            Thread.sleep(5000);
            for (int j = 0; j < clientsTableRows.size(); j++) {
                //comparing ClientName, Client Account Code and Product Provider for each row in Clients Table
                // with each list stored in clientsData.
                if(clientsTableRows.get(j).findElements(clientsTableColumns).get(0).getText().
                        equals(requiredDataFromProcessPayoutPage.get(i).get("Client Name")) &&
                        clientsTableRows.get(j).findElements(clientsTableColumns).get(1).getText().
                                equals(requiredDataFromProcessPayoutPage.get(i).get("Client Account Code") )&&
                        clientTableRows.get(j).findElements(clientsTableColumns).get(5).getText().
                                equals(ProductProvider)){
                    //Check if referrer is mapped with client
                    if(!clientTableRows.get(j).findElements(clientsTableColumns).get(9).getText().isEmpty()){
                        //write a method to check client rule for that client account code OR continue.
                        requiredDataFromProcessPayoutPage.get(i).put("Referrer Name",
                                clientTableRows.get(j).findElements(clientsTableColumns).get(9).getText());
                        clientsMappedWithReferrer.add(requiredDataFromProcessPayoutPage.get(i));
                    }
                    else{
                        continue;
                    }
                }
            }
        }
        return clientsMappedWithReferrer;
    }

    /**
     * @author: Krishna
     * Search each client account code from List<LinkedHashMap<String,String>> for each List. Check Client rule for each client
     * account code, if rule donot exist then remove it. Returns the updated List<LinkedHashMap<String,String>> which consist
     * of client rules.
     * @param clientsMappedWithReferrer
     * @param productProviderName
     * @throws InterruptedException
     */


    public List<LinkedHashMap<String,String>> checkIfClientRuleExistsForClientsMappedWithReferrer(
            List<LinkedHashMap<String,String>> clientsMappedWithReferrer, String productProviderName) throws InterruptedException {
        List<LinkedHashMap<String,String>> clientsMappedWithReferrerAndClientRule = new LinkedList<LinkedHashMap<String, String>>();
        for (int i = 0; i < clientsMappedWithReferrer.size(); i++) {
            searchField.clear();
            searchField.sendKeys(clientsMappedWithReferrer.get(i).get("Client Account Code"));
            searchField.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            for (int j = 0; j < clientsTableRows.size(); j++) {
                if (clientsMappedWithReferrer.get(i).get("Client Name").equals(clientsTableRows.get(j).findElements(
                        clientsTableColumns).get(0).getText()) && clientsMappedWithReferrer.get(i).get(
                                "Client Account Code").equals(clientsTableRows.get(j).findElements(
                                        clientsTableColumns).get(1).getText()) && productProviderName.equals(
                                                clientTableRows.get(j).findElements(clientsTableColumns).get(5).getText())) {
                    try{
                        if (clientRuleLogo.isDisplayed()) {
                            clientsMappedWithReferrerAndClientRule.add(clientsMappedWithReferrer.get(i));
                        }
                    }catch (Exception e) {
                        break;
                    }
                }
            }
        }
        return clientsMappedWithReferrerAndClientRule;
    }

    /**
     * Gets split ratio added with all other details for comparison with process payout page data
     * @param clientsMappedWithReferrerAndClientRule
     * @param clientsMappedWithReferrer
     * @param productProviderName
     * @return
     * @throws InterruptedException
     */
    public List<LinkedHashMap<String,String>> getSplitRatioForClients(
            List<LinkedHashMap<String,String>> clientsMappedWithReferrerAndClientRule,
            List<LinkedHashMap<String,String>>clientsMappedWithReferrer,
            String productProviderName) throws InterruptedException {
        List<LinkedHashMap<String,String>> clientsMappedWithReferrerAndClientRuleWithSplitRatio =new LinkedList<>();
        String splitRatio = "";
        for (int i = 0; i < clientsMappedWithReferrerAndClientRule.size(); i++) {
            searchField.clear();
            searchField.sendKeys(clientsMappedWithReferrerAndClientRule.get(i).get("Client Account Code"));
            searchField.sendKeys(Keys.ENTER);
            Thread.sleep(4000);
            for (int j = 0; j < clientsTableRows.size(); j++) {
                if (clientsMappedWithReferrerAndClientRule.get(i).get("Client Name").equals(clientsTableRows.get(j).findElements(
                        clientsTableColumns).get(0).getText()) && clientsMappedWithReferrerAndClientRule.get(i).get(
                        "Client Account Code").equals(clientsTableRows.get(j).findElements(clientsTableColumns).get(1).getText()) && productProviderName.equals(
                        clientTableRows.get(j).findElements(clientsTableColumns).get(5).getText())) {
                    WebElement addRule = clientTableRows.get(j).findElement(addRuleLink);
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeScript("arguments[0].click();", addRule);
                    for (int l = 0; l < rule.size(); l++) {
                        Thread.sleep(3000);
                        rule.get(l).click();
                        Thread.sleep(4000);
                        splitRatio = readRule(clientsMappedWithReferrerAndClientRule,i, productProviderName);

                        if (!splitRatio.equals("")) {
                            clientsMappedWithReferrerAndClientRule.get(i).put("Split Ratio", splitRatio);
                            clientsMappedWithReferrerAndClientRuleWithSplitRatio.add(clientsMappedWithReferrerAndClientRule.get(i));
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                    if(!splitRatio.equals("")){
                        backButton.click();
                        Thread.sleep(4000);
                        continue;
                    }
                    else{
                        clientsMappedWithReferrer.add(clientsMappedWithReferrerAndClientRule.get(i));
                        backButton.click();
                        Thread.sleep(4000);
                    }
                }
            }

        }
        return clientsMappedWithReferrerAndClientRuleWithSplitRatio;
    }

    /**
     * Reads and returns split ratio when rule is applied on a client mapped with referrer
     * @param clientsMappedWithReferrerAndClientRule
     * @param count
     * @param productProvider
     * @return
     * @throws InterruptedException
     */
    public String readRule(List<LinkedHashMap<String,String>> clientsMappedWithReferrerAndClientRule,
                           int count, String productProvider) throws InterruptedException {
        String splitRatio = "";
        Actions ac = new Actions(driver);
        ac.moveToElement(productProviderDropDwnBtn).click().build().perform();
        ac.moveToElement(searchProductProvider).sendKeys(productProvider).build().perform();
        for (int k = 0; k < productProviderValue.size(); k++) {
            if (productProvider.equals(productProviderValue.get(k).getText()) &&
                    productProviderValue.get(k).findElement(productProviderCheckBox).isSelected()) {

                ac.moveToElement(accountCodeDropDwn).click().build().perform();
                ac.moveToElement(searchAccountCode).sendKeys(clientsMappedWithReferrerAndClientRule.get(count).get("Client Account Code"))
                        .build().perform();
                for (int l = 0; l < accountCodeValue.size(); l++) {
                    if (clientsMappedWithReferrerAndClientRule.get(count).get("Client Account Code").equals(
                            accountCodeValue.get(l).getText()) && accountCodeValue.get(l).findElement(
                            accountCodeCheckBox).isSelected()) {


                        Select sel = new Select(FeeTypeDropDwn);
                        clientsMappedWithReferrerAndClientRule.get(count).get("Fee Type");
                        sel.getFirstSelectedOption().getText();

                        if (clientsMappedWithReferrerAndClientRule.get(count).get("Fee Type").equals(sel.getFirstSelectedOption().getText()) || sel.getFirstSelectedOption().getText().equals("All")) {
                            Thread.sleep(3000);
                            splitRatio = splitRatioTxtBx.getAttribute("value");
                            break;
                        }
                        break;
                    }

                }
                break;
            }

        }

        return splitRatio;
    }

    /**
     * Takes user to Adviser Group page by clicking on the Adviser Group link displayed in the the left rail under business setup
     * @throws InterruptedException
     */
    public void goToAdviserGroupsPage() throws InterruptedException {
        businessSetupLink.click();
        Thread.sleep(2000);
        businessSetupLink.findElement(adviserGroupsLinkBusinessSetup).click();
        Thread.sleep(4000);
    }

    /**
     * Takes the user to Licensee Referrer page by clicking on Licensee referrer link in the left rail under business setup
     * @throws InterruptedException
     */
    public void goToLicenseeReferrersPage() throws InterruptedException {
        businessSetupLink.click();
        Thread.sleep(2000);
        businessSetupLink.findElement(licenseeReferrersLink).click();
        Thread.sleep(4000);
    }

    public List<HashMap<String,String>> clientDataWithClientRule(List<HashMap<String,String>> allData,String productProvider) throws InterruptedException {
        JavascriptExecutor  jse = (JavascriptExecutor)driver;
        Actions ac= new Actions(driver);
        List<String> clientCodesWithRules= new LinkedList<>();
        List<String> clientCodesWithoutRules= new LinkedList<>();
        for(int i=0;i<allData.size();i++)
        {
            Thread.sleep(1000);
            if(clientCodesWithRules.contains(allData.get(i).get("Client Account Code"))) {
                Thread.sleep(1000);
                continue;
            }

            if(clientCodesWithoutRules.contains(allData.get(i).get("Client Account Code")))
            { Thread.sleep(1000);
                continue; }
            clientSearchBox.sendKeys(allData.get(i).get("Client Account Code"));
            clientSearchBox.sendKeys(Keys.ENTER);

            for(int j=0;j<clientSearchedRows.size();j++){
                try {Thread.sleep(2000);
                    ruleBar.isDisplayed(); }
                catch (Exception e)
                { Thread.sleep(1000);
                    clientSearchBox.clear();
                    List<String> clientFields= clientSearchedRows.get(j).findElements(clientTableFields).stream()
                            .map(m->m.getText()).collect(Collectors.toList());
                    if(!clientFields.get(6).equals("")){allData.get(i).put("Referrer Name",clientFields.get(6));}
                    Thread.sleep(1000);
                    clientCodesWithoutRules.add(allData.get(i).get("Client Account Code"));
                    Thread.sleep(1000);
                    continue; }

                Thread.sleep(3000);
                List<String> clientFields= clientSearchedRows.get(j).findElements(clientTableFields).stream()
                        .map(m->m.getText()).collect(Collectors.toList());
                if(clientFields.contains(allData.get(i).get("Client Account Code")) && clientFields.contains(allData.get(i).get("Client Name"))
                        && clientFields.contains(allData.get(i).get("Adviser Name")) && clientFields.contains(productProvider))
                {
                    Thread.sleep(1000);
                    if(!clientFields.get(6).equals("")){allData.get(i).put("Referrer Name",clientFields.get(6));}
                    Thread.sleep(1000);
                    clientCodesWithRules.add(allData.get(i).get("Client Account Code"));
                    Thread.sleep(1000);
                    addRuleNavigation.click();
                    try{
                        if(addRule.isDisplayed()){ jse.executeScript("arguments[0].click();",addRule);}
                    }
                    catch (Exception e)
                    {
                        ac.moveToElement(addRuleNavigation).build();
                        ac.moveToElement(addRule).build();
                        { jse.executeScript("arguments[0].click();",addRule);}
                        Thread.sleep(2000);
                    }
                    List<HashMap<String,String>> clientSplitRatio=clientRule(allData.get(i),productProvider);
                    for(String key:clientSplitRatio.get(0).keySet())
                    {
                        allData.get(i).put(key,clientSplitRatio.get(0).get(key));
                    }
                    Thread.sleep(1000);
                    clientPageToManageClient.click();
                    Thread.sleep(2000);
                }
            }
            clientSearchBox.clear();
        }
        return allData;
    }


    public List<HashMap<String,String>> clientRule(HashMap<String,String> allData,String productProvider) throws InterruptedException {
        List<HashMap<String,String>> splitRatio = new LinkedList<>();
        HashMap<String,String> advisersSplitRatio= null;
        JavascriptExecutor  jse = (JavascriptExecutor)driver;
        Actions ac= new Actions(driver);

        for(int x=0;x<rulesList.size();x++)
        {
            try { Thread.sleep(2000);
                ac.moveToElement(selectRule.get(x)).click().build().perform();}
            catch (Exception e)
            {
                Thread.sleep(2000);
                selectRule.get(x).click();
            }
            Thread.sleep(10000);
            ac.moveToElement(productProviderDropDwnBtn).click().build().perform();
            Thread.sleep(5000);
            searchProductProvider.sendKeys(productProvider);
            Thread.sleep(2000);
            for(int y=0;y<productProviderDropDownLabels.size();y++)
            {
                if(productProviderDropDownLabels.get(y).getText().equals(productProvider))
                {
                    if(productProviderDropDownCheckBox.get(y).isSelected())
                    {
                        try{Thread.sleep(1000);
                            accountCodeButton.click();}
                        catch (Exception e){Thread.sleep(1000);
                            ac.moveToElement(accountCodeButton).click().build().perform(); }
                        for(int z=0;z<accountCodeDropDownLabels.size();z++) {
                            if (accountCodeDropDownLabels.get(z).getText().equals(allData.get("Client Account Code"))) {
                                if (accountCodeDropDownCheckBox.get(z).isSelected()) {
                                    Select feeType = new Select(feeTypeOption);
                                    if (feeType.getFirstSelectedOption().getText().equals(allData.get("Fee Type"))
                                            || feeType.getFirstSelectedOption().getText().equals("All")) {
                                        advisersSplitRatio = new HashMap<>();
                                        for (int i = 0; i < advisersNamesForSpiltRatio.size(); i++) {
                                            advisersSplitRatio.put(new Select(advisersNamesForSpiltRatio.get(i)).getFirstSelectedOption().getText()
                                                    , advisersSpiltRatioValue.get(i).getAttribute("value"));

                                        }
                                        splitRatio.add(advisersSplitRatio);
                                        break;
                                    }
                                    continue;
                                }
                            }
                        }
                        continue;
                    }
                }
            }
            continue;
        }
        return splitRatio;
    }

    public List<HashMap<String,String>> modifiyClientRuleDataWithReferrerName(List<HashMap<String,String>> clientDataWithCR) {

        for (int i = 0; i < clientDataWithCR.size(); i++) {
            for (String key : clientDataWithCR.get(i).keySet()) {
                for (int j = i + 1; j < clientDataWithCR.size(); j++) {
                    if (clientDataWithCR.get(i).get("Client Account Code").equals(clientDataWithCR.get(j).get("Client Account Code"))) {
                        if(!clientDataWithCR.get(j).containsKey(key)) {
                            clientDataWithCR.get(j).put(key,clientDataWithCR.get(i).get(key));
                        }
                    }
                }
            }

        }

        for(int i=0;i<clientDataWithCR.size();i++)
        {
            int clientSplitRatio= 0;
            for(String key:clientDataWithCR.get(i).keySet())
            {
                if(key.contains(clientDataWithCR.get(i).get("Adviser Group")))
                {
                    clientSplitRatio=clientSplitRatio+Integer.valueOf(clientDataWithCR.get(i).get(key));
                }
            }
            if(!String.valueOf(clientSplitRatio).equals("0")){
                clientDataWithCR.get(i).put("Adviser Group Split Ratio",String.valueOf(clientSplitRatio));}
        }
        return clientDataWithCR;
    }
}

