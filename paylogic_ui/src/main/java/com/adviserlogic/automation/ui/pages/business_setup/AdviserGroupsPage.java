package com.adviserlogic.automation.ui.pages.business_setup;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.stream.Collectors;

public class AdviserGroupsPage extends BasePage {
    public AdviserGroupsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#tblAdviserGroup>thead>tr>th")
    private List<WebElement> adviserGroupHeader;

    @FindBy(css = "#tblAdviserGroup>tbody>tr")
    private List<WebElement> adviserGroups;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(6)>a")
    private WebElement businessSetupPanel;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(6)>ul>li:nth-of-type(2)>a")
    private WebElement adviserGroupPanel;

    @FindBy(css = ".form-group>#txtFixedFee")
    private WebElement fixedSpiltRatio;

    @FindBy(css = ".form-group>#ddlFeeTypes")
    private WebElement splitType;

    @FindBy(css = ".form-group>#txtdateaccumulated")
    private WebElement startDateforTieredSplit;

    @FindBy(css = "#divFlatTieredFee>.row")
    private List<WebElement> tieredFeeRows;

    @FindBy(css = "label[for='lblSplitFee']")
    private WebElement fixedSplitRatioLabel;

    @FindBy(css = ".wrap_template_header>div:nth-of-type(5)")
    private WebElement spiltFeesTabLink;

    @FindBy(css = "#tblPayoutRules>thead>tr>th")
    private List<WebElement> splitFeesRuleHeader;

    @FindBy(css = "#tblPayoutRules>tbody>tr")
    private List<WebElement> spiltFeesRuleRows;

    @FindBy(css = ".wrap_adviser_form>#divConditions>.row")
    private List<WebElement> splitFeesRuleData;

    @FindBy(css = "div[id*='divReferredClient_0'] button")
    private WebElement referredClientsButton;

    @FindBy(css = "#divOtherClient_0 button")
    private WebElement otherClientsButton;

    @FindBy(css = ".pace-done>div:nth-of-type(14) div>input")
    private WebElement referredClientsFilterTxtBx;

    @FindBy(css = ".pace-done>div:nth-of-type(11) div>input")
    private WebElement otherClientsFilterTxtBx;

    @FindBy(css = "li[id*='ui-multiselect-ddlReferredClient']")
    private List<WebElement> referredClientsDropDown;

    @FindBy(css = "li[id*='ui-multiselect-ddlOtherClient_0']")
    private List<WebElement> otherClientsDropDown;

    @FindBy(css="div>#txtSplitRatio_0")
    private WebElement splitRatioTxtBx;

    @FindBy(css = "[onclick = 'reloadSplitFees();']")
    private WebElement backButton;

    @FindBy(css = "#liAdviserGroup>a")
    private WebElement adviserGroupsLink;

    @FindBy(css = "#divAction0>div.no_wrap")
    private WebElement informationTab;

    @FindBy(css = "div>#txtFixedFee")
    private WebElement defaultSplitRatioTxtBx;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(4)>ul>li:nth-of-type(2)>a")
    WebElement processPayoutLinkLeftRail;
    @FindBy(css = "#tblAdviserGroup_filter input")
    private WebElement searchTxtBx;

    @FindBy(css = "tr[id*='trAdviserGroup']")
    private List<WebElement> adviserGroupTableRows;

    By tableRowColumns = By.cssSelector("td");

    By goToActionsLink = By.cssSelector("a");

    @FindBy(css = "tr[id*='trPayoutRules']")
    private List<WebElement> splitFeesTableRows;

    @FindBy(css = "div>#ddlFeeType_0")
    private WebElement FeeType;

    @FindBy(css = "#divProductProvider_0 button")
    private WebElement productProvidersButton;

    @FindBy(css = ".pace-done>div:nth-of-type(12) div>input")
    private WebElement productProviderFilterTxtBx;

    @FindBy(css = "[for *= 'ui-multiselect-ddlProductProvider_0-option']")
    private List<WebElement> productProvidersDropDown;

    By productProvidersDropDownValue = By.cssSelector("span");

    By productProvidersDropDownCheckBox = By.cssSelector("input");

    @FindBy(css = "#divReferredClient_0 [type = 'button']")
    private WebElement referredClientsDropdown;

    @FindBy(css = "[for *= 'ui-multiselect-ddlReferredClient_0-option']")
    private List<WebElement> referredClientsDropDownList;

    By referredClientsDropDownValue = By.cssSelector("span");

    By referredClientsDropDownCheckBox = By.cssSelector("input");

    @FindBy(css = "[name = 'SplitRatio']")
    private WebElement splitRatio;

    @FindBy(css = "tbody#lstVariableFees tr td:nth-of-type(1)")
    private List<WebElement> feeNameProportionalTable;

    @FindBy(css = "tbody#lstVariableFees tr td:nth-of-type(2)")
    private List<WebElement> proportionPercentProportionalTable;

    @FindBy(css = "tbody#lstVariableFees tr td:nth-of-type(3)")
    private List<WebElement> startDateProportionalTable;

    @FindBy(css = "tbody#lstVariableFees tr td:nth-of-type(4)")
    private List<WebElement> endDateProportionalTable;

    @FindBy(css ="table#tblVariableFee thead th:nth-of-type(1)")
    private WebElement feeName;

    @FindBy(css ="table#tblVariableFee thead th:nth-of-type(2)")
    private WebElement proportion;

    @FindBy(css ="table#tblVariableFee thead th:nth-of-type(3)")
    private WebElement startDate;

    @FindBy(css ="table#tblVariableFee thead th:nth-of-type(4)")
    private WebElement endDate;

    @FindBy(css = "ol.breadcrumb a")
    private WebElement goBackToAdviserGroupFromTabs;

    @FindBy(css="#tblAdviserGroup_filter > label > input[type='text']")
    private WebElement searchFilterAdvisorGroupPage;

    @FindBy(css="#btnExpand")
    private WebElement advisorGroupExpandbutton;

    @FindBy(css="#btnExpand")
    private List<WebElement> advisorGroupbutton;

    @FindBy(css="#dvAdvg > div.panel.panel-default.panelNew > div")
    private  List<WebElement> PanelAdvisorgroup;

    @FindBy(css=".col-xs-12 .panel-heading a")
    private List<WebElement> adviserGroupsExpandLinks;

    @FindBy(css = "tbody#lstAdviserGroups tr td.sorting_1")
    public List<WebElement> adviserGroupNamesAdviserGroupPage;

    @FindBy(css = "div.wrap_template_header div.single_temp:nth-of-type(9)")
    public WebElement proportionalFeeLink;

    @FindBy(css = "tbody#lstVariableFees tr")
    public List<WebElement> proportionalFeeTableBodyRows;

    @FindBy(css = "tbody#lstAdviserGroups tr td:nth-child(2)")
    public List<WebElement> adviserGroupRepIdAdviserGroupPage;

    @FindBy(css = "div>#ddlFeeType_0")
    private WebElement feeTypeDropDown;

    By value = By.cssSelector("span");

    By checkBox = By.cssSelector("input");


    public void goToAdviserGroupsPage() throws InterruptedException {
        businessSetupPanel.click();
        Thread.sleep(1000);
        adviserGroupPanel.click();
    }

    public void goBackToAdviserGroupFromTabs(){
        goBackToAdviserGroupFromTabs.click();
    }

    public List<String> adviserGroupHeaderList()
    {
        List<String> adviserGroupsHeader=adviserGroupHeader.stream().map(m->m.getText()).collect(Collectors.toList());
        return adviserGroupsHeader;
    }

    public List<List<String>> adviserGroupsRow() {
        List<List<String>> values = new LinkedList<>();
        List<String> rowData =new LinkedList<>();
        for(int i=1;i<=adviserGroups.size();i++) {
            List<WebElement> groupRowData = driver.findElements(By.cssSelector("#tblAdviserGroup>tbody>tr:nth-of-type(" + i + ")>td"));
            rowData = groupRowData.stream().map(m -> m.getText()).collect(Collectors.toList());
            values.add(rowData);
        }
        return values;
    }

    public List<HashMap<String, String>> adviserGroupData() {
        List<HashMap<String, String>> adviserGroups = new LinkedList<>();
        HashMap<String, String> map = null;
        List<String> header = adviserGroupHeaderList();
        List<List<String>> rows = adviserGroupsRow();

        for (int i = 0; i < rows.size(); i++) {
            map = new HashMap<>();
            for (int j = 0; j < header.size(); j++) {
                map.put(header.get(j), rows.get(i).get(j));
            }
            adviserGroups.add(map);
        }
        return adviserGroups;
    }

    public WebElement getGoToActionsLink(int rowNumber){
        rowNumber = rowNumber + 1;
        String row = "tbody#lstAdviserGroups tr";
        String goToLink = row+":nth-of-type(" +rowNumber+ ") td:last-child";
        By goToLinkForRowLocator = By.cssSelector(goToLink);
        WebElement goToActionForAdviser = driver.findElement(goToLinkForRowLocator);
        return goToActionForAdviser;
    }

    public List<HashMap<String,String>> goToActionsAndExtractFixedSpiltRatio(String adviserGroupName, String authorizedRepId) throws InterruptedException {
        List<HashMap<String,String>> actions= adviserGroupData();
        List<HashMap<String,String>> fixedSpiltRatio=null;
        for(int i=0;i<actions.size();i++)
        {
            if(actions.get(i).get("Adviser Group Name").equals(adviserGroupName) &&
                    actions.get(i).get("Authorised Rep No").equals(authorizedRepId))
            {
                driver.findElement(By.cssSelector("#tblAdviserGroup>tbody>tr:nth-of-type(" + (i+1) + ")>td:nth-of-type(11)>a")).click();
                Thread.sleep(2000);
                String spilt=splitType.getAttribute("value");
                fixedSpiltRatio=fixedSpiltRatioForGroup(spilt);
            }
        }
        return fixedSpiltRatio;
    }

    public List<HashMap<String, String>> fixedSpiltRatioForGroup(String spiltType) {
        List<HashMap<String, String>> splitRatioValues = new LinkedList<>();
        HashMap<String, String> splitRatio;
        if (spiltType.toString().equals("1")) {
            splitRatio = new HashMap<>();
            System.out.println("Spilt Type is : " + "Fixed Spilt" + " and Spilt Percentage is:" + fixedSpiltRatio.getAttribute("value").toString());
            splitRatio.put(fixedSplitRatioLabel.getText(), fixedSpiltRatio.getAttribute("value").toString());
            splitRatioValues.add(splitRatio);
            return splitRatioValues;
        } else if (spiltType.toString().equals("2")) {
            //System.out.println("Spilt Type is : " + "Tiered Spilt" + " and Spilt Percentage is:" + fixedSpiltRatio.getAttribute("value").toString());
            startDateforTieredSplit.getAttribute("value").toString();
            for (int i = 1; i <= tieredFeeRows.size(); i++) {
                splitRatio = new HashMap<>();
                splitRatio.put(driver.findElement(By.cssSelector("#divFlatTieredFee>.row:nth-of-type(" + i + ")>div>div>label[for='lblFlatTierFrom']"))
                        .getText(), driver.findElement(By.cssSelector("#divFlatTieredFee>.row:nth-of-type(" + i + ")>div>div>[id^=txtFlatTierFrom]"))
                        .getAttribute("value"));
                splitRatio.put(driver.findElement(By.cssSelector("#divFlatTieredFee>.row:nth-of-type(" + i + ")>div>div>label[for='lblFlatTierTo']"))
                        .getText(), driver.findElement(By.cssSelector("#divFlatTieredFee>.row:nth-of-type(" + i + ")>div>div>[id^=txtFlatTierTo]"))
                        .getAttribute("value"));

                splitRatio.put(driver.findElement(By.cssSelector("#divFlatTieredFee>.row:nth-of-type(" + i + ")>div>div>label[for='lblFlatTierBaseSplit']"))
                        .getText(), driver.findElement(By.cssSelector("#divFlatTieredFee>.row:nth-of-type(" + i + ")>div>div>[id^=txtFlatTierBaseSplit]"))
                        .getAttribute("value"));

                splitRatioValues.add(splitRatio);
            }

        }
        return splitRatioValues;
    }

    public void gotToSpiltFeesTab() {
        spiltFeesTabLink.click();
    }

    public List<HashMap<String,String>> spiltFeesRulesTables()
    {
        List<String> header = splitFeesRuleHeader.stream().map(m->m.getText()).collect(Collectors.toList());
        List<List<String>> rowData= new LinkedList<>();
        List<HashMap<String,String>> splitRulesData = new LinkedList<>();
        HashMap<String,String> rowValues;
        for(int i=1;i<=spiltFeesRuleRows.size();i++) {
            List<WebElement> splitFeeRulesRowData = driver.findElements(By.cssSelector("#tblPayoutRules>tbody>tr:nth-of-type("+ i +")>td"));
            rowData.add(splitFeeRulesRowData.stream().map(m->m.getText()).collect(Collectors.toList())); }

        for(int i=0;i<rowData.size();i++) {
            rowValues = new LinkedHashMap<>();
            for(int j=0;j<header.size();j++) {
                rowValues.put(header.get(j),rowData.get(i).get(j)); }
            splitRulesData.add(rowValues); }

        return splitRulesData;
    }

    public Select selectFromDropdown(WebElement element) {
        Select select = new Select(element);
        return select;
    }

    public List<HashMap<String, String>> getSplitValuesFromSplitFeesRule(List<HashMap<String, String>> splitRulesData) {
        List<HashMap<String, String>> spiltFeesRules = new LinkedList<>();
        HashMap<String, String> data = null;
        for (int i = 1; i <= splitRulesData.size(); i++) {
            data = new LinkedHashMap<>();
            driver.findElement(By.cssSelector("#tblPayoutRules>tbody>tr:nth-of-type(" + i + ")")).click();

            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(1)>div>label")).getText(),
                    driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(1)>div>button>span:nth-of-type(2)")).getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(2)>div>label")).getText(),
                    selectFromDropdown(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(2)>div>select"))).getFirstSelectedOption().getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(3)>div>label")).getText(),
                    selectFromDropdown(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(3)>div>select"))).getFirstSelectedOption().getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(4)>div>label")).getText(),
                    selectFromDropdown(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(1)>div:nth-of-type(4)>div>select"))).getFirstSelectedOption().getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(1)>div>label")).getText(),
                    driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(1)>div>button")).getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(2)>div>label")).getText(),
                    driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(2)>div>button")).getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(3)>div>label")).getText(),
                    driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(3)>div>button")).getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(4)>div>label")).getText(),
                    driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(2)>div:nth-of-type(4)>div>button")).getText());
            data.put(driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(3)>div:nth-of-type(1)>div>label")).getText(),
                    driver.findElement(By.cssSelector(".wrap_adviser_form>#divConditions>.row:nth-of-type(3)>div:nth-of-type(1)>div>input")).getAttribute("value"));
            spiltFeesRules.add(data);
            gotToSpiltFeesTab();
        }
        return spiltFeesRules;
    }

    public void getSplitRatioForClients(List<LinkedHashMap<String,String>> clientsMappedWithReferrer, String ProductProviderName) throws InterruptedException {

        for(int i = 0 ; i<clientsMappedWithReferrer.size(); i++){
            searchTxtBx.clear();
            searchTxtBx.sendKeys(clientsMappedWithReferrer.get(i).get("Adviser Group Name"));
            for(int adviserGroupTableRow = 0; adviserGroupTableRow < adviserGroupTableRows.size(); adviserGroupTableRow++){
                if(adviserGroupTableRows.get(adviserGroupTableRow).findElements(tableRowColumns).get(0).getText().equals(
                        clientsMappedWithReferrer.get(i).get("Adviser Group Name")) &&
                        adviserGroupTableRows.get(adviserGroupTableRow).findElements(tableRowColumns).get(1).getText().equals(
                                clientsMappedWithReferrer.get(i).get("Adviser Group ARP"))){
                    adviserGroupTableRows.get(i).findElement(goToActionsLink).click();
                    Thread.sleep(4000);
                    spiltFeesTabLink.click();
                    Thread.sleep(5000);
                    for(int splitFeesTableRow = 0 ; splitFeesTableRow < spiltFeesRuleRows.size(); splitFeesTableRow++){
                        spiltFeesRuleRows.get(splitFeesTableRow).click();
                        Thread.sleep(5000);
                        matchSplitFeesRule(clientsMappedWithReferrer,i,ProductProviderName);

                    }
                }
            }
        }
    }

    public void matchSplitFeesRule(List<LinkedHashMap<String,String>> clientsMappedWithReferrer, int Count, String ProductProviderName) throws InterruptedException {
        productProvidersButton.click();
        productProviderFilterTxtBx.sendKeys(ProductProviderName);
        Thread.sleep(2000);
        for (int productProvidersCount = 0; productProvidersCount < productProvidersDropDown.size(); productProvidersCount++) {
            if (productProvidersDropDown.get(productProvidersCount).findElement(productProvidersDropDownValue).getText().equals(
                    ProductProviderName) && productProvidersDropDown.get(productProvidersCount).findElement(
                    productProvidersDropDownCheckBox).isSelected()) {
                Select sel = new Select(FeeType);
                if (sel.getFirstSelectedOption().equals(clientsMappedWithReferrer.get(Count).get("Fee Type")) ||
                        sel.getFirstSelectedOption().getText().equals("All")) {
                    referredClientsDropdown.click();
                    referredClientsFilterTxtBx.sendKeys(clientsMappedWithReferrer.get(Count).get("Client Account Code"));
                    Thread.sleep(2000);
                    List<String> referrerList = referredClientsDropdown.findElements(referredClientsDropDownValue).stream().map(
                            m -> m.getText()).filter(f -> !f.isEmpty()).collect(Collectors.toList());
                    for (int referrerClientsCount = 0; referrerClientsCount < referredClientsDropDownList.size(); referrerClientsCount++) {
                        if(referrerList.get(referrerClientsCount).contains(clientsMappedWithReferrer.get(
                                        Count).get("Client Account Code"))){
                            String clientAccountCode = referredClientsDropDownList.get(referrerClientsCount).findElement(
                                    referredClientsDropDownValue).getText().split("\\(")[1].replace(")", "");
                            String clientName = referredClientsDropDownList.get(referrerClientsCount).findElement(
                                    referredClientsDropDownValue).getText().split("\\(")[0];
                            if(clientAccountCode.equals(clientsMappedWithReferrer.get(Count).get("Client Account Code")) &&
                                    clientName.equals(clientsMappedWithReferrer.get(Count).get("Client Name")) &&
                                    referredClientsDropDownList.get(referrerClientsCount).findElement(
                                            referredClientsDropDownCheckBox).isSelected()){
                                String splitRatioText = splitRatio.getText();
                            }
                        }
                        else{
                            continue;
                        }

                    }
                }
            }
        }
    }



    /**
     * @param allData is the List of HashMap which consist of client wise all data which is present in process payout page.
     * @param ProductProviderName is the product provider for which file was uploaded in Upload Data File.
     * @return
     * @throws InterruptedException
     *
     * @Scenario Check each Split Fees for each Adviser Group and compare Product Provider, Fee Type and Referred Clients. If it get match then get
     * the Split Ratio of that split fee rule else get default split ratio from information tab.
     */

    public List<HashMap<String,String>> getAdviserGroupSplitRatio(List<HashMap<String,String>> allData, String ProductProviderName) throws InterruptedException {
        String splitRatio="";
        List<HashMap<String,String>> clientDataWithoutSplitFee = new LinkedList<HashMap<String, String>>();
        Set<String> AdviserGroupARP = new LinkedHashSet<String>();
        for (HashMap<String, String> clientData1 : allData) {
            AdviserGroupARP.add(clientData1.get("Adviser Rep Id"));
        }
        //Iterate each Adviser Group in Adviser Groups Page.
        for(String arp : AdviserGroupARP) {
            List<HashMap<String,String>> clientDataToGetSplitRatio = new LinkedList<HashMap<String, String>>();
            searchTxtBx.clear();
            searchTxtBx.sendKeys(arp);
            Thread.sleep(3000);
            for (int adviserGroupTableRow = 0; adviserGroupTableRow < adviserGroupTableRows.size(); adviserGroupTableRow++) {
                String AdviserGroupName = adviserGroupTableRows.get(adviserGroupTableRow).findElements(tableRowColumns).get(0).getText();
                String AdviserGroupAuthRepNo = adviserGroupTableRows.get(adviserGroupTableRow).findElements(tableRowColumns).get(1).getText();
                for(int k=0;k<allData.size();k++){
                    if (AdviserGroupName.equals(allData.get(k).get("Adviser Group")) && AdviserGroupAuthRepNo.equals(
                            allData.get(k).get("Adviser Rep Id")) && !allData.get(k).containsKey("Adviser Group Split Ratio")) {
                        clientDataToGetSplitRatio.add(allData.get(k));
                    }
                }
                if (arp.equals(AdviserGroupAuthRepNo) && !clientDataToGetSplitRatio.isEmpty()) {
                    adviserGroupTableRows.get(adviserGroupTableRow).findElement(goToActionsLink).click();
                    Thread.sleep(2000);
                    //Get List<HashMap> which consist of Adviser Group Name as AdviserGroupName and do not contain Adviser Group Split Ratio.
                    if (!clientDataToGetSplitRatio.isEmpty()) {
                        //Check each Split Fees for all clients of this Adviser Group and get Split Ratio.
                        spiltFeesTabLink.click();
                        Thread.sleep(5000);
                        for (int splitFeesTableRow = 0; splitFeesTableRow < spiltFeesRuleRows.size(); splitFeesTableRow++) {
                            if (!spiltFeesRuleRows.get(0).findElement(tableRowColumns).getText().contains("No data available in table")) {
                                spiltFeesRuleRows.get(splitFeesTableRow).click();
                                Thread.sleep(5000);
                                List<HashMap<String, String>> clientReadRuleData = readRule(clientDataToGetSplitRatio, ProductProviderName);
                                backButton.click();
                                Thread.sleep(3000);
                            }
                        }
                    }
                    clientDataWithoutSplitFee = clientDataToGetSplitRatio.stream().filter(m-> !m.containsKey("Adviser Group Split Ratio")).collect(Collectors.toList());
                    if (!clientDataWithoutSplitFee.isEmpty()) {
                        informationTab.click();
                        Thread.sleep(5000);
                        splitRatio = defaultSplitRatioTxtBx.getAttribute("value");
                        for (HashMap clientData : allData) {
                            if (AdviserGroupName.equals(clientData.get("Adviser Group")) && AdviserGroupAuthRepNo.equals(clientData.get("Adviser Rep Id")) && !clientData.containsKey("Adviser Group Split Ratio")) {
                                clientData.put("Adviser Group Split Ratio", splitRatio);
                            }
                        }
                    }
                    Thread.sleep(4000);
                    adviserGroupsLink.click();
                    Thread.sleep(4000);
                    break;

                }
            }
        }
        return allData;
    }

    /**
     *
     * @param clientDataToGetSplitRatio
     * @param ProductProviderName
     * @return
     * @throws InterruptedException
     *
     * @Scenario Check Split Fee Rule with Adviser Group for all clients in List<HashMap> and match Product Provider, Fee Type and Referred Clients(if
     * HashMap consist of Referrer Name) or Other clients(if HashMap do not consist of Referrer Name) drop down. If get match then append Adviser group
     * Split Ratio for that HashMap.
     */

    public List<HashMap<String,String>> readRule(List<HashMap<String,String>> clientDataToGetSplitRatio, String ProductProviderName) throws InterruptedException {
        String splitRatio="";
        String clientAccountCode="";
        List<HashMap<String,String>> clientDataToCheckRule = new LinkedList<HashMap<String, String>>();
        List<String> checkedRuleForClients = new LinkedList<String>() ;


        for(HashMap clientData: clientDataToGetSplitRatio){
            if(!clientData.containsKey("Adviser Group Split Ratio")){
                clientDataToCheckRule.add(clientData);
            }
        }
        //Check for all clients present in clientDataToCheckRule in each Rule.
        for(int i=0;i<clientDataToCheckRule.size();i++) {
            if (!checkedRuleForClients.contains(clientDataToCheckRule.get(i).get("Client Account Code"))) {
                checkedRuleForClients.add(clientDataToCheckRule.get(i).get("Client Account Code"));
                productProvidersButton.click();
                productProviderFilterTxtBx.clear();
                productProviderFilterTxtBx.sendKeys(ProductProviderName);
                Thread.sleep(2000);
                for (int j = 0; j < productProvidersDropDown.size(); j++) {
                    //Check the product Provider name in drop down and is it selected or not.
                    if (productProvidersDropDown.get(j).findElement(value).getText().equals(
                            ProductProviderName) && productProvidersDropDown.get(j).findElement(
                            checkBox).isSelected()) {
                        productProvidersButton.click();
                        Select sel = new Select(feeTypeDropDown);
                        //Compare the Fee type from Fee Type drop down.
                        if (sel.getFirstSelectedOption().getText().equals(clientDataToCheckRule.get(i).get("Fee Type")) || sel.getFirstSelectedOption().getText().equals("All")
                                && clientDataToCheckRule.get(i).containsKey("Referrer Name")) {
                            //Compare the referred clients from Referred Client drop down.
                            referredClientsButton.click();
                            referredClientsFilterTxtBx.clear();
                            referredClientsFilterTxtBx.sendKeys(clientDataToCheckRule.get(i).get("Client Account Code"));
                            Thread.sleep(2000);
                            for (int k = 0; k < referredClientsDropDown.size(); k++) {
                                //Get client account code from the drop down values to compare with Client Account Code of HashMap clientData
                                String clientNameClientAccountCode = referredClientsDropDown.get(k).findElement(value).getText();
                                if (!clientNameClientAccountCode.equals("")) {
                                    if (clientNameClientAccountCode.contains(clientNameClientAccountCode)) {
                                        clientAccountCode = clientNameClientAccountCode.split("\\(")[1].replace(")", "");

                                        if (clientAccountCode.equals(clientDataToCheckRule.get(i).get("Client Account Code")) && referredClientsDropDown.get(k).findElement(
                                                checkBox).isSelected()) {
                                            referredClientsButton.click();
                                            Thread.sleep(2000);
                                            //get the split ratio from Split Ratio Text box.
                                            splitRatio = splitRatioTxtBx.getAttribute("value");
                                            for (HashMap<String, String> data : clientDataToCheckRule) {
                                                if (data.get("Client Account Code").equals(clientDataToCheckRule.get(i).get("Client Account Code"))) {
                                                    data.put("Adviser Group Split Ratio", splitRatio);
                                                }
                                            }

                                        }
                                        break;
                                    }
                                }
                            }
                            break;
                        } else if (sel.getFirstSelectedOption().getText().equals(clientDataToCheckRule.get(i).get("Fee Type")) || sel.getFirstSelectedOption().getText().equals("All")
                                && !clientDataToCheckRule.get(i).containsKey("Referrer Name")) {
                            //Compare the other clients from other Client drop down.
                            otherClientsButton.click();
                            Thread.sleep(2000);
                            otherClientsFilterTxtBx.clear();
                            otherClientsFilterTxtBx.sendKeys(clientDataToCheckRule.get(i).get("Client Account Code"));
                            Thread.sleep(2000);
                            for (int k = 0; k < otherClientsDropDown.size(); k++) {
                                //Get client account code from the other clients drop down values to compare with Client Account Code of HasMap clientData
                                String clientNameClientAccountCode = otherClientsDropDown.get(k).findElement(value).getText();

                                if (!clientNameClientAccountCode.equals("")) {
                                    if (clientNameClientAccountCode.contains(clientNameClientAccountCode)) {
                                        clientAccountCode = clientNameClientAccountCode.split("\\(")[1].replace(")", "");
                                        checkedRuleForClients.add(clientAccountCode);
                                        if (clientAccountCode.equals(clientDataToCheckRule.get(i).get("Client Account Code")) && otherClientsDropDown.get(k).findElement(
                                                checkBox).isSelected()) {
                                            otherClientsButton.click();
                                            Thread.sleep(2000);
                                            //get the split ratio from Split Ratio Text box.
                                            splitRatio = splitRatioTxtBx.getAttribute("value");
                                            for (HashMap<String, String> data : clientDataToCheckRule) {
                                                if (data.get("Client Account Code").equals(clientDataToCheckRule.get(i).get("Client Account Code"))) {
                                                    data.put("Adviser Group Split Ratio", splitRatio);
                                                }
                                            }
                                        }

                                        break;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
        return clientDataToGetSplitRatio;
    }

    /**
     * Clicks on process payout link displayed in the left rail
     */
    public void goToProcessPayoutPage() throws InterruptedException {
        processPayoutLinkLeftRail.click();
        Thread.sleep(3000);
    }

    public List<LinkedHashMap<String, String>> getAllProportionFee(HashMap<String, String> adviserGroupNameRepIdProcessPayoutPage) throws InterruptedException {

        List<LinkedHashMap<String,  String>> allProportionFee = new LinkedList<>();
        LinkedList<String> adviserGroupNameProcessPayoutPage = new LinkedList<>();
        LinkedList<String> adviserGroupRepIdProcessPayoutPage = new LinkedList<>();
        HashMap<String, String> adGroupAndCode = adviserGroupNameRepIdProcessPayoutPage;
        for (String group: adGroupAndCode.keySet()){

            String key = group;
            String value = adGroupAndCode.get(group);
            adviserGroupNameProcessPayoutPage.add(key);
            adviserGroupRepIdProcessPayoutPage.add(value);
        }
        for(int i = 0; i < adviserGroupNameProcessPayoutPage.size(); i++){
            LinkedHashMap<String, String> dataOfAdviser = new LinkedHashMap<>();
            dataOfAdviser.put("Adviser Name", adviserGroupNameProcessPayoutPage.get(i));
            dataOfAdviser.put("Adviser RepId", adviserGroupRepIdProcessPayoutPage.get(i));
            searchFilterAdvisorGroupPage.sendKeys(adviserGroupNameProcessPayoutPage.get(i));
            for(int j = 0; j < adviserGroupNamesAdviserGroupPage.size(); j++){
                if(adviserGroupNamesAdviserGroupPage.get(j).getText().equals(adviserGroupNameProcessPayoutPage.get(i)) &&
                        adviserGroupRepIdAdviserGroupPage.get(j).getText().equals(adviserGroupRepIdProcessPayoutPage.get(i))){
                    getGoToActionsLink(j).click();
                    Thread.sleep(5000);
                    proportionalFeeLink.click();
                    Thread.sleep(3000);
                    try{
                        if(proportionalFeeTableBodyRows.size() > 0)
                        {
                            for(int k = 0; k < proportionalFeeTableBodyRows.size(); k++){
                                LinkedHashMap<String, String> dataToCollect = new LinkedHashMap<>();
                                dataToCollect.put(feeName.getText(), feeNameProportionalTable.get(k).getText());
                                dataToCollect.put(proportion.getText(), proportionPercentProportionalTable.get(k).getText());
                                dataToCollect.put(startDate.getText(), startDateProportionalTable.get(k).getText());
                                dataToCollect.put(endDate.getText(), endDateProportionalTable.get(k).getText());
                                allProportionFee.add(dataToCollect);
                            }
                            allProportionFee.add(dataOfAdviser);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            goBackToAdviserGroupFromTabs();
        }
        return allProportionFee;
    }
}