package com.adviserlogic.automation.ui.pages.payout_data;

import com.adviserlogic.automation.common.pages.BasePage;
import com.adviserlogic.automation.ui.components.Header;
import com.adviserlogic.automation.ui.components.ProcessPayoutTable;
import com.sun.deploy.security.DeployClientAuthCertStore;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessPayoutPage extends BasePage {

    @FindBy(partialLinkText = "Process Payout")
    private WebElement processPayoutLink;

    @FindBy(css = ".datepicker-days>table>tbody>tr")
    private List<WebElement> calRows;

    @FindBy(css = ">td")
    private WebElement calColumnRelative;

    @FindBy(css = "#btnSearchPayouts")
    private WebElement searchPayoutButton;

    @FindBy(css = "div.form-group>#txtFDate")
    private WebElement fromDateField;

    @FindBy(css = "div.col-xs-12 div:nth-of-type(2)>div>a")
    private WebElement advisorGroup2ExpandLink;

    By productProviderExpandLinks = By.cssSelector("[onclick = 'Expand(this)']");

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(6)")
    private WebElement businessSetupLink;

    By clientsLinkBusinessSetup = By.cssSelector("a[href *= '/Client/ManageClient']");

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(6)>ul.treeview-menu>li:nth-of-type(3)>a")
    private WebElement advisorsLink;

    @FindBy(partialLinkText = "Clients")
    private WebElement clientsLink;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(3)>ul.treeview-menu>li:nth-of-type(1)>a")
    private WebElement uploadDataFileLink;

    @FindBy(css = "#searchPayout #divProductProvider button.ui-multiselect")
    WebElement productProvidersDropdown;

    @FindBy(css = "[for *= ui-multiselect-ddlProductProvider-option]")
    List<WebElement> productProviderDropdownList;

    @FindBy(css = "[for *= ui-multiselect-ddlProductProvider-option]>span")
    List<WebElement> productProviderNames;

    @FindBy(css = "#dvAdvg [class = 'panel-heading']")
    List<WebElement> adviserGroupsProcessedPayoutTable;

    By adviserGroupName = By.cssSelector("span");

    By expandAdviserGroupLink = By.cssSelector("a[href *= '#adviserGrp_']");

    By expandProductProviderLink = By.cssSelector("td>div[onclick = 'Expand(this)']");

    By productProviderCheckbox = By.cssSelector("input");

    @FindBy(css = "tbody>tr>th")
    private List<WebElement> columnHeadingsProcessedPayoutTable;

    @FindBy(css = "tbody>[class = 'onpayMainHeadder even']")
    private List<WebElement> productProvidersListAdvisergroup;

    @FindBy(css = "tbody>[transactionId]>td:nth-of-type(4)")
    private List<WebElement> clientNameListProcessedPayoutTable;

    @FindBy(css = "tbody>[transactionId]>td:nth-of-type(3)")
    private List<WebElement> subProductListProcessedPayoutTable;

    @FindBy(css = "#divProductProvider > div > button > span.NoWrap")
    private WebElement productProviderDropdown;

    @FindBy(xpath = "/html/body/div[11]/div/ul/li[2]/a/span[2]")
    private WebElement Uncheck;

    @FindBy(xpath = "/html/body/div[11]/div/div/input[@placeholder='Enter keywords']")
    private WebElement SearchProviderName;

    @FindBy(xpath = "//input[@id=\"txtFDate\"]")
    private WebElement fromDateLocator;

    @FindBy(xpath = "//table[@class='table table-condensed']/tbody/tr/td[@class='day']")
    private List<WebElement> calendarAllDays;

    @FindBy(css = ".col-xs-12 .panel-heading a")
    private List<WebElement> adviserGroupsExpandLinks;

    @FindBy(css = "#dvAdvg [id^='adviserGrp_'] [name='tblPayout'] tbody tr:nth-of-type(1) td:nth-of-type(1) div")
    private List<WebElement> productProviderExpandLink;

    @FindBy(css = "div[id*='adviserGrp_']  tr[transactionid]")
    private List<WebElement> abc;

    @FindBy(css = "#dvAdvg .panelNew:nth-of-type(1) [id*=adviserGrp_] tbody>tr[class*='onpaySubHeadder chiledxvdatarow'] th")
    private List<WebElement> payoutTableHeading;

    @FindBy(css = ".col-xs-12>div.panel.panel-default.panelNew")
    private List<WebElement> processPayoutAdvisersIdentifier;

    @FindBy(css = "#dvAdvg .panel-heading span b")
    private List<WebElement> numberOfAdviserGroups;

    By adviserGroups = By.cssSelector("span");
    @FindBy(css = "tbody>[transactionId]>td:nth-of-type(17)")
    private List<WebElement> feeTypeListProcessedPayoutTable;

    @FindBy(css = "tbody>[transactionId]>td:nth-of-type(5)")
    private List<WebElement> recievedFeeProcessedPayoutTable;

    By processPayoutRowsIdentifier = By.cssSelector("tr[class ^='onpaySubHeadder chiledxvdatarow']");
    By processPayoutRowsValueIdentifier = By.cssSelector("td");

    @FindBy(css = "[href *= '/Apps/UploadHistory.aspx']")
    private WebElement uploadHistoryPageLink;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(6)>ul.treeview-menu>li:nth-of-type(2)>a")
    private WebElement advisorsGroupLink;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] tbody tr td:nth-of-type(2)")
    private List<WebElement> fixedFeeNames;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] tbody tr td:nth-of-type(4)")
    private List<WebElement> fixedFeeAmount;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] tbody tr td:nth-of-type(5)")
    private List<WebElement> fixedFeeGst;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] tbody tr td:nth-of-type(6)")
    private List<WebElement> fixedFeeTotal;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] thead tr th:nth-of-type(2)")
    private WebElement fixedfeeTableHeadName;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] thead tr th:nth-of-type(4)")
    private WebElement fixedfeeTableHeadAmount;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] thead tr th:nth-of-type(5)")
    private WebElement fixedfeeTableHeadGst;

    @FindBy(css = ".panel-collapse.collapse.in table[name=tblFixedFee] thead tr th:nth-of-type(6)")
    private WebElement fixedfeeTableHeadTotal;

    @FindBy(css = "div.panel.panel-default.panelNew span.panel-label b")
    private List<WebElement> adviserGroupPanels;

    @FindBy(css = ".panel-collapse.collapse.in .onpaySubFooter td:nth-of-type(10)")
    private WebElement payoutCell;

    @FindBy(css = ".panel-collapse.collapse.in .onpayMainHeadder td:nth-of-type(7)")
    private WebElement TotalAdvisor;

    @FindBy(css = ".panel-collapse.collapse.in .onpaySubFooter td:nth-of-type(11)")
    private WebElement totalGstCell;

    @FindBy(css = "input#TxtCompulsaryDate")
    private WebElement confirmationDateProcessPayoutPage;

    @FindBy(css = "table[tbl*=\'adviserGrp\'] [class*=\'onpaySubFooter\'] >td")
    private List<WebElement> advisorgroupTotal;

    @FindBy(css = "table[tbl*='adviserGrp']>thead>tr>th:nth-of-type(3)")
    private WebElement clicktest;

    By processPayoutFooterRowIdentifier = By.cssSelector("tr[class ^='onpaySubFooter chiledxvdatarow']");
    By processPayoutFooterRowValueIdentifier = By.cssSelector("td");

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(2)")
    private WebElement totalReceivedFeeCellPayoutTable;

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(3)")
    private WebElement totalReceivedGstCellPayoutTable;

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(4)")
    private WebElement totalLicenseePayoutCellPayoutTable;

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(5)")
    private WebElement totalLicenseeGstCellPayoutTable;

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(7)")
    private WebElement totalRefererrPayoutCellPayoutTable;

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(8)")
    private WebElement totalRefererrGstCellPayoutTable;

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(10)")
    private WebElement totalAdviserGroupPayoutCellPayoutTable;

    @FindBy(css = "div.panel-collapse.collapse.in tr.onpaySubFooter td:nth-of-type(11)")
    private WebElement totalAdviserGroupGstCellPayoutTable;

    @FindBy(css = "input#btnProcessAllSelection")
    private WebElement processAllSectionButton;

    @FindBy(css = "div.col-sm-4.col-xs-12.box_right input[id='btnContinue']")
    private WebElement confirmButton;

    @FindBy(css = "div#advsiergroupdetails>table>thead>tr>th>input")
    private WebElement checkAllAdvisorGroups;

    @FindBy(css = "#btnProceed")
    private WebElement proceedButton;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(5)>ul>li:nth-of-type(1)>a")
    private WebElement payoutReportLinkLeftRail;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(5)")
    private WebElement reportLink;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] tbody tr td:nth-of-type(1)")
    private List<WebElement> payableAmountDescription;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] thead tr th:nth-of-type(1)")
    private WebElement payableAmountCalculationTableHeadDescription;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] thead tr th:nth-of-type(2)")
    private WebElement payableAmountCalculationTableHeadAmount;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] thead tr th:nth-of-type(3)")
    private WebElement payableAmountCalculationTableHeadGST;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] thead tr th:nth-of-type(4)")
    private WebElement payableAmountCalculationTableHeadTotal;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] tbody tr td:nth-of-type(2)")
    private List<WebElement> payableAmountValue;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] tbody tr td:nth-of-type(3)")
    private List<WebElement> payableAmountGST;

    @FindBy(css = "div.panel-collapse.collapse.in table[name=tblTotalPayment] tbody tr td:nth-of-type(4)")
    private List<WebElement> payableAmountTotal;

    @FindBy(css = "table[name=tblFixedFee] thead tr th:nth-of-type(3)")
    private WebElement fixedfeeTableHeadFixedFeeType;

    @FindBy(css = "table[name=tblFixedFee] tbody tr td:nth-of-type(3)")
    private List<WebElement> fixedFeeType;

    @FindBy(css = "table[name=tblFixedFee] thead tr th:nth-of-type(7)")
    private WebElement fixedfeeTableHeadDueDate;

    @FindBy(css = "table[name=tblFixedFee] tbody tr td:nth-of-type(7)")
    private List<WebElement> fixedFeeDueDate;

    @FindBy(css = "table[id='tblCalender'] tbody >tr:nth-of-type(5) >td:nth-of-type(6)")
    private WebElement datepick;

    @FindBy(css = "button#btnUnconfirmAll")
    private WebElement unconfirmAll;

    @FindBy (css = "input#btnconformAll")
    private WebElement unconformAll;


    private static DecimalFormat df2 = new DecimalFormat(".##");


    public ProcessPayoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to advisers page
     *
     * @throws InterruptedException
     */
    public void goToAdvisersPage() throws InterruptedException {
        new Actions(driver).moveToElement(businessSetupLink).click().build().perform();
        Thread.sleep(1000);
        advisorsLink.click();
        Thread.sleep(3000);
    }

    public void datepicker() throws InterruptedException
    {
        datepick.click();
        Thread.sleep(2000);
        unconfirmAll.click();
        Thread.sleep(2000);
        unconformAll.click();

    }

    /**
     * Returns object for Process Payout Table
     *
     * @return
     */
    public ProcessPayoutTable getProcessPayoutTable() {
        return new ProcessPayoutTable(driver);
    }

    public String getCalColumnLocator(int row) {
        String columnLocator = ".datepicker-days>table>tbody>tr";
        return columnLocator + ":nth-of-type(" + row + ")>td";
    }

    /**
     * Click on Search Payout
     *
     * @throws InterruptedException
     */
    public void clickOnSearchPayout() throws InterruptedException {
        searchPayoutButton.click();
        Thread.sleep(3000);
    }

    public void clickOnProcessAllSection() throws InterruptedException {
        Thread.sleep(3000);
        processAllSectionButton.click();
        Thread.sleep(3000);
    }

    public void clickOnConfirm() throws InterruptedException {
        Thread.sleep(2000);
        confirmButton.click();
    }

    public void goToPayoutReportPage() throws InterruptedException {
        reportLink.click();
        Thread.sleep(1000);
        payoutReportLinkLeftRail.click();
        Thread.sleep(2000);
    }
    /**
     * Clicks on product provider dropdown
     */
    public void clickOnProductProviderDropdown() {
        productProvidersDropdown.click();
    }

    /**
     * selecting required product provider from Payout page
     *
     * @param productProviderName
     */
    public void selectProductProvider(String productProviderName) throws InterruptedException {
        Thread.sleep(1000);
        productProviderDropdown.click();
        Thread.sleep(3000);
        Uncheck.click();
        SearchProviderName.sendKeys(productProviderName);
        Thread.sleep(3000);
        for (int productProvider = 0; productProvider < productProviderDropdownList.size(); productProvider++) {
            if (productProviderDropdownList.get(productProvider).findElement(adviserGroupName).getText().equals(productProviderName)) {
                productProviderDropdownList.get(productProvider).findElement(productProviderCheckbox).click();
            } else {
                continue;
            }
        }
    }


    /**
     * Gets number of adviser groups displayed under processed payout section
     *
     * @return
     */
    public int getAdviserGroupRows() {
        return adviserGroupsProcessedPayoutTable.size();
    }

    /**
     * Returns the adviser group names from list of adviser groups displayed under processed payout section
     *
     * @return
     */
    public List<String> getAdviserGroupNames() {
        List<String> adviserGroupNames = new LinkedList<>();
        for (int i = 0; i <= getAdviserGroupRows() - 1; i++) {
            adviserGroupNames.add(adviserGroupsProcessedPayoutTable.get(i).findElements(adviserGroupName).get(0).getText().split(
                    "Adviser Group : : ")[1]);
        }
        return adviserGroupNames;
    }

    /**
     * Returns adviser groups with their respective rep ids
     *
     * @return
     */
    public HashMap<String, String> getAdviserGroupAndRepId() throws InterruptedException {
        Thread.sleep(1000);
        HashMap<String, String> adviserGroupsWithRepId = new LinkedHashMap<>();
        for (int i = 0; i <= getAdviserGroupNames().size() - 1; i++) {
            adviserGroupsWithRepId.put(getAdviserGroupNames().get(i).split("\\(")[0],
                    getAdviserGroupNames().get(i).split("\\(")[1].replace(")", ""));
        }
        return adviserGroupsWithRepId;
    }

    /**
     * Verifying data under process payout status in upload file page is there in process payout page
     *
     * @param table1
     * @param table2
     * @return
     */
    public boolean isDataSimilarInGivenTables(List<LinkedHashMap<String, String>> table1, List<LinkedHashMap<String, String>> table2) {

        boolean isDataSimilar = false;
        int counter = 0;
        for (int i = 0; i <= table1.size() - 1; i++) {
            String table1SubProductCode = table1.get(i).get("Sub Product Code");
            String table1FeeAmount = table1.get(i).get("Fee Amount($)");
            String table1ClientName = table1.get(i).get("Client Name");
            String table1Gst = table1.get(i).get("GST($)");
            for (int j = 0; j <= table2.size() - 1; j++) {
                if (table1SubProductCode.equals(table2.get(j).get("Sub Product")) && table1ClientName.equals(table2.get(j).get("Client Name"))
                        && table1FeeAmount.equals(table2.get(j).get("Fee($)")) && table1Gst.equals(table2.get(j).get("GST($)1"))) {
                    System.out.println("Row " + i + " for upload table matched row " + j + " for payout table");
                    counter++;
                }
            }

        }
        if (counter == table1.size()) {
            isDataSimilar = true;
        }
        return isDataSimilar;
    }

    /**
     * Clicks on clients link displayed under business setup in left rail
     */
    public void goToClientsPage() throws InterruptedException {
        businessSetupLink.click();
        Thread.sleep(2000);
        businessSetupLink.findElement(clientsLinkBusinessSetup).click();
        Thread.sleep(4000);
    }

    /**
     * Select for date from process payout
     *
     * @param date
     * @throws InterruptedException
     */
    public void selectFromDate(int date) throws InterruptedException {
        fromDateField.click();
        List<WebElement> DateCheck = calendarAllDays;
        for (int i = 0; i < DateCheck.size(); i++) {
            if (Integer.valueOf(DateCheck.get(i).getText()) == date) {
                DateCheck.get(i).click();
                break;
            }
        }
    }

    /**
     * Find process payout header and modify header
     *
     * @return
     * @throws InterruptedException
     */
    public List<String> processPayoutHeader() throws InterruptedException {

        Thread.sleep(5000);
        adviserGroupsExpandLinks.get(0).click();

        Thread.sleep(1000);
        productProviderExpandLink.get(0).click();

        Thread.sleep(3000);
        List<String> headers = payoutTableHeading.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> modifiedHeader = new LinkedList<>();
        int gstcount = 0, payoutcount = 0, splitratiocount = 0;

        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).equals("GST($)")) {
                if (gstcount >= 1) {
                    modifiedHeader.add(headers.get(i).replace("GST($)", "GST($)(" + Integer.toString(gstcount) + ")"));
                } else {
                    modifiedHeader.add(headers.get(i));
                }
                gstcount++;
            } else if (headers.get(i).equals("Payout($)")) {
                if (payoutcount >= 1) {
                    modifiedHeader.add(headers.get(i).replace("Payout($)", "Payout($)(" + Integer.toString(payoutcount) + ")"));
                } else {
                    modifiedHeader.add(headers.get(i));
                }
                payoutcount++;
            } else if (headers.get(i).equals("Split Ratio")) {
                if (splitratiocount >= 1) {
                    modifiedHeader.add(headers.get(i).replace("Split Ratio", "Split Ratio(" + Integer.toString(splitratiocount) + ")"));
                } else {
                    modifiedHeader.add(headers.get(i));
                }
                splitratiocount++;
            } else {
                modifiedHeader.add(headers.get(i));
            }
        }

        Thread.sleep(2000);
        productProviderExpandLink.get(0).click();
        Thread.sleep(1000);
        adviserGroupsExpandLinks.get(0).click();
        return modifiedHeader;

    }

    public void confirmPayoutProcessDialog() throws InterruptedException {
        if(checkAllAdvisorGroups.isSelected())
            Thread.sleep(3000);
            checkAllAdvisorGroups.click();
        proceedButton.click();
    }


    /**
     * Extract the process payout rows
     *
     * @return
     * @throws InterruptedException
     */
    public List<List<String>> processPayoutRowValues() throws InterruptedException {
        List<List<String>> values = new LinkedList<>();
        List<String> columnValues = null;
        for (int i = 0; i < adviserGroupsExpandLinks.size(); i++) {
            Thread.sleep(1000);
            adviserGroupsExpandLinks.get(i).click();
            Thread.sleep(1000);
            productProviderExpandLink.get(i).click();
            Thread.sleep(2000);

            int rowsSize = processPayoutAdvisersIdentifier.get(i).findElements(processPayoutRowsIdentifier).size();
            for (int x = 1; x < rowsSize; x++) {
                columnValues = new LinkedList<>();
                columnValues = processPayoutAdvisersIdentifier.get(i).findElements(processPayoutRowsIdentifier).
                        get(x).findElements(processPayoutRowsValueIdentifier).stream().map(WebElement::getText).
                        collect(Collectors.toList());
                values.add(columnValues);
            }

            Thread.sleep(1000);
            productProviderExpandLink.get(i).click();
            Thread.sleep(2000);
            adviserGroupsExpandLinks.get(i).click();

        }
        return values;
    }

    /**
     * Find process payout header and modify header , added group and repid header
     *
     * @return
     * @throws InterruptedException
     */
    public List<String> processPayoutHeaderWithGroupAssociation() throws InterruptedException
    {
        adviserGroupsExpandLinks.get(0).click();
        Thread.sleep(3000);
        productProviderExpandLink.get(0).click();
        Thread.sleep(1000);
        List<String> headers = payoutTableHeading.stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> modifiedHeader = new LinkedList<>();
        int gstcount = 0, payoutcount = 0, splitratiocount = 0;

        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).equals("GST($)")) {
                if (gstcount >= 1) {
                    modifiedHeader.add(headers.get(i).replace("GST($)", "GST($)(" + Integer.toString(gstcount) + ")"));
                } else {
                    modifiedHeader.add(headers.get(i));
                }
                gstcount++;
            } else if (headers.get(i).equals("Payout($)")) {
                if (payoutcount >= 1) {
                    modifiedHeader.add(headers.get(i).replace("Payout($)", "Payout($)(" + Integer.toString(payoutcount) + ")"));
                } else {
                    modifiedHeader.add(headers.get(i));
                }
                payoutcount++;
            } else if (headers.get(i).equals("Split Ratio")) {
                if (splitratiocount >= 1) {
                    modifiedHeader.add(headers.get(i).replace("Split Ratio", "Split Ratio(" + Integer.toString(splitratiocount) + ")"));
                } else {
                    modifiedHeader.add(headers.get(i));
                }
                splitratiocount++;
            } else {
                modifiedHeader.add(headers.get(i));
            }
        }
        modifiedHeader.add("Adviser Group");
        modifiedHeader.add("Adviser Rep Id");

        Thread.sleep(3000);
        productProviderExpandLink.get(0).click();
        Thread.sleep(1000);
        adviserGroupsExpandLinks.get(0).click();
        return modifiedHeader;
    }


    /**
     * Extract the process payout rows added Adviser group and Rep Id
     *
     * @return
     * @throws InterruptedException
     */
    public List<List<String>> processPayoutRowValuesWithGroupAssociation() throws InterruptedException {
        List<List<String>> values = new LinkedList<>();
        List<String> columnValues = null;
        for (int i = 0; i < adviserGroupsExpandLinks.size(); i++) {
            Thread.sleep(1000);
            adviserGroupsExpandLinks.get(i).click();
            Thread.sleep(1000);
//            clicktest.click();
            productProviderExpandLink.get(i).click();
            Thread.sleep(2000);

            String AdviserGroup = numberOfAdviserGroups.get(i).getText().replaceAll(" ", "")
                    .split("::")[1].split("\\(")[0];
            String AdviserGroupRepId = numberOfAdviserGroups.get(i).getText().replaceAll(" ", "")
                    .split("::")[1].split("\\(")[1].replaceAll("\\)", "");
            int rowsSize = processPayoutAdvisersIdentifier.get(i).findElements(processPayoutRowsIdentifier).size();
            for (int x = 1; x < rowsSize; x++) {
                columnValues = new LinkedList<>();
                columnValues = processPayoutAdvisersIdentifier.get(i).findElements(processPayoutRowsIdentifier).
                        get(x).findElements(processPayoutRowsValueIdentifier).stream().map(WebElement::getText).
                        collect(Collectors.toList());
                columnValues.add(AdviserGroup);
                columnValues.add(AdviserGroupRepId);
                values.add(columnValues);
            }

            Thread.sleep(1000);
            productProviderExpandLink.get(i).click();
            Thread.sleep(2000);
            adviserGroupsExpandLinks.get(i).click();

        }
        return values;
    }

    /**
     * Read Process Payout data
     *
     * @return
     * @throws InterruptedException
     */
    public List<HashMap<String, String>> readprocessPayoutData() throws InterruptedException {
        List<HashMap<String, String>> processPayoutData = new LinkedList<>();
        HashMap<String, String> rowData = null;
        List<String> modifiedHeader = processPayoutHeader();
        List<List<String>> values = processPayoutRowValues();
        for (int i = 0; i < values.size(); i++) {
            rowData = new LinkedHashMap<>();
            for (int j = 2; j < modifiedHeader.size(); j++) {
                rowData.put(modifiedHeader.get(j), values.get(i).get(j));
            }
            processPayoutData.add(rowData);
        }

        return processPayoutData;
    }

    public boolean verifyPayoutForLicenseeAdviserGroupAndReferrer(List<LinkedHashMap<String, String>> data) {
        boolean isPayoutCalculationCorrect = false;

        try{
            for (int i = 0; i <= data.size() - 1; i++) {
                if(((Double.parseDouble(data.get(i).get("Payout($)(1)")))==(Double.parseDouble(df2.format(((Double.parseDouble(data.get(i).get("Split Ratio"))) * (Double.parseDouble(data.get(i).get("Fee($)")))) / 100.0))))
                        ||((Math.abs((Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("Split Ratio"))) *(Double.parseDouble(data.get(i).get("Fee($)")))  / 100.0))) - (Double.parseDouble(data.get(i).get("Payout($)(1)"))))) <= 0.02)
                        &&(((Double.parseDouble(data.get(i).get("GST($)(2)")))==(Double.parseDouble(df2.format(((Double.parseDouble(data.get(i).get("Split Ratio"))) * (Double.parseDouble(data.get(i).get("GST($)")))) / 100.0))))
                        ||(Math.abs((Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("Split Ratio"))) *(Double.parseDouble(data.get(i).get("GST($)")))  / 100.0))) - (Double.parseDouble(data.get(i).get("GST($)(2)")))) <= 0.02))
                        &&(((Double.parseDouble(data.get(i).get("Payout($)(2)")))==(Double.parseDouble(df2.format(((Double.parseDouble(data.get(i).get("Split Ratio(1)"))) * (Double.parseDouble(data.get(i).get("GST($)")))) / 100.0))))
                        ||(Math.abs((Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("Split Ratio(1)"))) *(Double.parseDouble(data.get(i).get("GST($)")))  / 100.0))) - (Double.parseDouble(data.get(i).get("Payout($)(2)")))) <= 0.02))
                        &&(((Double.parseDouble(data.get(i).get("GST($)(3)")))==(Double.parseDouble(df2.format(((Double.parseDouble(data.get(i).get("Split Ratio(1)"))) * (Double.parseDouble(data.get(i).get("GST($)")))) / 100.0))))
                        ||(Math.abs((Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("Split Ratio(1)"))) *(Double.parseDouble(data.get(i).get("GST($)")))  / 100.0))) - (Double.parseDouble(data.get(i).get("GST($)(3)")))) <= 0.02))
                        &&((Double.parseDouble(data.get(i).get("Payout($)")))==(Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("Fee($)"))) - ((Double.parseDouble(data.get(i).get("Payout($)(1)"))) +  (Double.parseDouble(data.get(i).get("Payout($)(2)")))))))
                        ||((Math.abs((Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("Fee($)")))-((Double.parseDouble(data.get(i).get("Payout($)(1)"))) +  (Double.parseDouble(data.get(i).get("Payout($)(2)")))) - (Double.parseDouble(data.get(i).get("Payout($)"))))))) <= 0.02)))
                        && ((Double.parseDouble(data.get(i).get("GST($)(1)")))==(Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("GST($)"))) - ((Double.parseDouble(data.get(i).get("GST($)(2)"))) +  (Double.parseDouble(data.get(i).get("GST($)(3)")))))))
                        ||(Math.abs((Double.parseDouble(df2.format((Double.parseDouble(data.get(i).get("GST($)")))-((Double.parseDouble(data.get(i).get("GST($)(2)"))) +  (Double.parseDouble(data.get(i).get("GST($)(3))")))) - (Double.parseDouble(data.get(i).get("GST($)(1)"))))))) <= 0.02)))
                {

                    isPayoutCalculationCorrect = true;
                }else {
                    throw new Exception("Check failed for adviser group "+ data.get(i).get("Adviser Group") + " for client" + data.get(i).get(""));
                }
                //return isPayoutCalculationCorrect;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isPayoutCalculationCorrect;
    }

    public boolean isTotalPayoutDataCalculationCorrect(List<LinkedHashMap<String, String>> data) throws InterruptedException {
        try
        {
            for (int i = 0; i < adviserGroupsExpandLinks.size(); i++) {
                double sumOfReceivedAmount = 0, sumOfReceivedGst = 0, sumOfLicenseePayout = 0, sumOfLicenseeGst = 0,
                        sumOfReferrerPayout = 0, sumOfReferrerGst = 0, sumOfAdviserGroupPayout = 0, sumOfAdviserGroupGst = 0;
                HashMap<String, String> dataToCollect = new LinkedHashMap<>();
                Thread.sleep(1000);
                String[] panelParts = adviserNameAndRedIdFromPanelHeading(adviserGroupPanels.get(i).getText());
                String adviserName = panelParts[0];
                String adviserRepId = panelParts[1];
                adviserGroupsExpandLinks.get(i).click();
                Thread.sleep(1000);
                productProviderExpandLink.get(i).click();
                Thread.sleep(1000);
                for (int j = 0; j < data.size(); j++) {
                    if (adviserName.equals(data.get(j).get("Adviser Group"))
                            && adviserRepId.equals(data.get(j).get("Adviser Rep Id"))) {

                        sumOfReceivedAmount = sumOfReceivedAmount + Double.parseDouble(data.get(j).get("Fee($)"));
                        sumOfReceivedGst = sumOfReceivedGst + Double.parseDouble(data.get(j).get("GST($)"));
                        sumOfLicenseePayout = sumOfLicenseePayout + Double.parseDouble(data.get(j).get("Payout($)"));
                        sumOfLicenseeGst = sumOfLicenseeGst + Double.parseDouble(data.get(j).get("GST($)(1)"));
                        sumOfReferrerPayout = sumOfReferrerPayout + Double.parseDouble(data.get(j).get("Payout($)(1)"));
                        sumOfReferrerGst = sumOfReferrerGst + Double.parseDouble(data.get(j).get("GST($)(2)"));
                        sumOfAdviserGroupPayout = sumOfAdviserGroupPayout + Double.parseDouble(data.get(j).get("Payout($)(2)"));
                        sumOfAdviserGroupGst = sumOfAdviserGroupGst + Double.parseDouble(data.get(j).get("GST($)(3)"));
                    }
                }
                if (((Double.parseDouble(totalReceivedFeeCellPayoutTable.getText()) == sumOfReceivedAmount) || (Math.abs(Double.parseDouble(totalReceivedFeeCellPayoutTable.getText()) - sumOfReceivedAmount) <= 0.02))
                        && ((Double.parseDouble(totalReceivedGstCellPayoutTable.getText()) == sumOfReceivedGst) || (Math.abs(Double.parseDouble(totalReceivedGstCellPayoutTable.getText()) - sumOfReceivedGst) <= 0.02))
                        && ((Double.parseDouble(totalLicenseePayoutCellPayoutTable.getText()) == sumOfLicenseePayout) || (Math.abs(Double.parseDouble(totalLicenseePayoutCellPayoutTable.getText()) - sumOfLicenseePayout) <= 0.02))
                        && ((Double.parseDouble(totalLicenseeGstCellPayoutTable.getText()) == sumOfLicenseeGst) || (Math.abs(Double.parseDouble(totalLicenseeGstCellPayoutTable.getText()) - sumOfLicenseeGst) <= 0.02))
                        && ((Double.parseDouble(totalRefererrPayoutCellPayoutTable.getText()) == sumOfReferrerPayout) || (Math.abs(Double.parseDouble(totalRefererrPayoutCellPayoutTable.getText()) - sumOfReferrerPayout) <= 0.02))
                        && ((Double.parseDouble(totalRefererrGstCellPayoutTable.getText()) == sumOfReferrerGst) || (Math.abs(Double.parseDouble(totalRefererrGstCellPayoutTable.getText()) - sumOfReferrerGst) <= 0.02))
                        && ((Double.parseDouble(totalAdviserGroupPayoutCellPayoutTable.getText()) == sumOfAdviserGroupPayout) || (Math.abs(Double.parseDouble(totalAdviserGroupPayoutCellPayoutTable.getText()) - sumOfAdviserGroupPayout) <= 0.02))
                        && ((Double.parseDouble(totalAdviserGroupGstCellPayoutTable.getText()) == sumOfAdviserGroupGst) || (Math.abs(Double.parseDouble(totalAdviserGroupGstCellPayoutTable.getText()) - sumOfAdviserGroupGst) <= 0.02))) {

                }else{
                    throw new Exception("Total payout data failed for adviser group " + adviserName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Read Process Payout data With Adviser Name and Group Information
     * @return
     * @throws InterruptedException
     */
    public List<LinkedHashMap<String, String>> readprocessPayoutDataWithGroupAssociation() throws InterruptedException {
        List<LinkedHashMap<String, String>> processPayoutData = new LinkedList<>();
        LinkedHashMap<String, String> rowData = null;
        List<String> modifiedHeader = processPayoutHeaderWithGroupAssociation();
        List<List<String>> values = processPayoutRowValuesWithGroupAssociation();
        for (int i = 0; i < values.size(); i++) {
            rowData = new LinkedHashMap<>();
            for (int j = 2; j < modifiedHeader.size(); j++) {
                rowData.put(modifiedHeader.get(j), values.get(i).get(j));
            }
            processPayoutData.add(rowData);
        }
        return processPayoutData;
    }

    public List<LinkedHashMap<String, String>> readpayableAmountCalculationDataWithAdvisorGroupAssociation() throws InterruptedException {
        List<LinkedHashMap<String, String>> payableAmountData=new LinkedList<>();
        int length=adviserGroupPanels.size();
        for(int i=0;i<length;i++)
        {
            LinkedHashMap<String, String> adviserGroupDetails = new LinkedHashMap<>();
            adviserGroupsExpandLinks.get(i).click();
            String[] panelParts = adviserNameAndRedIdFromPanelHeading(adviserGroupPanels.get(i).getText());
            String adviserName = panelParts[0];
            String adviserRepId = panelParts[1];
//            adviserGroupDetails.put("Adviser Name", adviserName);
//            adviserGroupDetails.put("Adviser RepId", adviserRepId);
            Thread.sleep(2000);
            for(int j=0;j<payableAmountDescription.size();j++)
            {
                LinkedHashMap<String, String> payableAmountDataCollect = new LinkedHashMap<>();
                payableAmountDataCollect.put(payableAmountCalculationTableHeadDescription.getText(), payableAmountDescription.get(j).getText());
                payableAmountDataCollect.put(payableAmountCalculationTableHeadAmount.getText(), payableAmountValue.get(j).getText());
                payableAmountDataCollect.put(payableAmountCalculationTableHeadGST.getText(), payableAmountGST.get(j).getText());
                payableAmountDataCollect.put(payableAmountCalculationTableHeadTotal.getText(), payableAmountTotal.get(j).getText());
                payableAmountDataCollect.put("Adviser Name", adviserName);
                payableAmountDataCollect.put("Adviser RepId", adviserRepId);
                payableAmountData.add(payableAmountDataCollect);
            }
//            payableAmountData.add(adviserGroupDetails);
            Thread.sleep(2000);
            adviserGroupsExpandLinks.get(i).click();
        }

        return payableAmountData;
    }

    public List<LinkedHashMap<String, String>> readFixedFeeDataAndPayableAmountCalculationDataWithAdvisorGroupAssociation() throws InterruptedException{
        List<LinkedHashMap<String, String>> fixedProportionFeeAndPayableAmountCalculationDataData = new LinkedList<>();
        for (int i = 0; i < adviserGroupPanels.size(); i++) {
            Thread.sleep(2000);
            adviserGroupsExpandLinks.get(i).click();
            Thread.sleep(2000);
            String[] panelParts = adviserNameAndRedIdFromPanelHeading(adviserGroupPanels.get(i).getText());
            String adviserName = panelParts[0];
            String adviserRepId = panelParts[1];
            for (int j = 0; j < fixedFeeNames.size() - 1; j++) {
                LinkedHashMap<String, String> fixedFeeDataCollect = new LinkedHashMap<>();
                if (fixedFeeNames.get(j).getText().contains(":")) {
                    fixedFeeDataCollect.put(fixedfeeTableHeadName.getText(), fixedFeeNames.get(j).getText());
                    fixedFeeDataCollect.put(fixedfeeTableHeadFixedFeeType.getText(), fixedFeeType.get(j).getText());
                    fixedFeeDataCollect.put(fixedfeeTableHeadAmount.getText(), fixedFeeAmount.get(j).getText());
                    fixedFeeDataCollect.put(fixedfeeTableHeadGst.getText(), fixedFeeGst.get(j).getText());
                    fixedFeeDataCollect.put(fixedfeeTableHeadTotal.getText(), fixedFeeTotal.get(j).getText());
                    fixedFeeDataCollect.put(fixedfeeTableHeadDueDate.getText(), fixedFeeDueDate.get(j).getText());
                    fixedFeeDataCollect.put("Adviser Name", adviserName);
                    fixedFeeDataCollect.put("Adviser RepId", adviserRepId);

                }
                fixedProportionFeeAndPayableAmountCalculationDataData.add(fixedFeeDataCollect);
            }
            Thread.sleep(2000);
            for (int j = 0; j < payableAmountDescription.size(); j++) {
                LinkedHashMap<String, String> payableAmountDataCollect = new LinkedHashMap<>();
                payableAmountDataCollect.put(payableAmountCalculationTableHeadDescription.getText(), payableAmountDescription.get(j).getText());
                payableAmountDataCollect.put(payableAmountCalculationTableHeadAmount.getText(), payableAmountValue.get(j).getText());
                payableAmountDataCollect.put(payableAmountCalculationTableHeadGST.getText(), payableAmountGST.get(j).getText());
                payableAmountDataCollect.put(payableAmountCalculationTableHeadTotal.getText(), payableAmountTotal.get(j).getText());
                payableAmountDataCollect.put("Adviser Name", adviserName);
                payableAmountDataCollect.put("Adviser RepId", adviserRepId);
                fixedProportionFeeAndPayableAmountCalculationDataData.add(payableAmountDataCollect);
            }
            Thread.sleep(2000);
            adviserGroupsExpandLinks.get(i).click();
        }
        return fixedProportionFeeAndPayableAmountCalculationDataData;
    }



        /**
         * Verify upload data with process payout data
         * @param uploadData
         * @param payoutData
         * @return
         */
        public boolean isUploadDataInPayout(List<LinkedHashMap<String,String >> uploadData
                ,List<HashMap<String,String>> payoutData){
            int rowMatched = 0;
            for (int i = 0; i < uploadData.size(); i++) {
                for (int j = 0; j < payoutData.size(); j++) {
                    if (uploadData.get(i).get("Sub Product Code").equals(payoutData.get(j).get("Sub Product"))
                            && uploadData.get(i).get("Client Name").equals(payoutData.get(j).get("Client Name"))
                            && uploadData.get(i).get("Fee Amount($)").equals(payoutData.get(j).get("Fee($)"))
                            && uploadData.get(i).get("GST($)").equals(payoutData.get(j).get("GST($)"))) {
                        rowMatched++;
                    }
                }
            }
            return (rowMatched == uploadData.size());
        }


    /**
     * Gets the required data from processed payout table
     * @param clientNameClientCodeUpoloadHistoryTable
     * @param subProductListUploadHistorytable
     * @param feeAmountUploadHistorytable
     * @return
     * @throws InterruptedException
     */
    public List<LinkedHashMap<String, String>> getRequiredDataFromProcessPayoutPage(HashMap clientNameClientCodeUpoloadHistoryTable,
                                                                   List<String> subProductListUploadHistorytable,
                                                                   List<String> feeAmountUploadHistorytable) throws InterruptedException {

        List consolidatedDataProcessPayoutPage = new LinkedList<>();
        HashMap<String, String> dataFromProcessPayoutPage = null;
        String adviserGroup;
        String adviserGroupARP;

        //Expand each Adviser Group
        for(int i=0; i <= adviserGroupsProcessedPayoutTable.size()-1;i++){
            adviserGroup = adviserGroupsProcessedPayoutTable.get(i).findElement(adviserGroupName).getText().replace(
                    " ","").split("::")[1].split("\\(")[0];
            adviserGroupARP = adviserGroupsProcessedPayoutTable.get(i).findElement(adviserGroupName).getText().replace(
                    " ","").split("::")[1].split("\\(")[1].replace(")", "");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView()",adviserGroupsProcessedPayoutTable.get(i));
            try {
                adviserGroupsProcessedPayoutTable.get(i).findElement(expandAdviserGroupLink).click();
                Thread.sleep(3000);
                int CountOfProductProvidersInGroup = productProvidersListAdvisergroup.size();

                //Expand each product provider in an Adviser Group
                for (int j=0; j<CountOfProductProvidersInGroup;j++){

                    WebElement rowCountOfProductProviderInGroup = productProvidersListAdvisergroup.get(j);

                    rowCountOfProductProviderInGroup.findElement(expandProductProviderLink).click();
                    Thread.sleep(3000);

                    List<String> clientAccountCodesUploadHistory = new LinkedList<>();
                    clientAccountCodesUploadHistory.addAll(clientNameClientCodeUpoloadHistoryTable.keySet());

                    for(int clientCode = 0; clientCode <= clientAccountCodesUploadHistory.size()-1; clientCode++){
                        for(int clientName = 0; clientName <= clientNameListProcessedPayoutTable.size()-1; clientName++){
                            if(clientNameClientCodeUpoloadHistoryTable.get(clientAccountCodesUploadHistory.get(clientCode)).equals(
                                    clientNameListProcessedPayoutTable.get(clientName).getText()) && subProductListProcessedPayoutTable.get(clientName).getText().equals(
                                            subProductListUploadHistorytable.get(clientCode)) && recievedFeeProcessedPayoutTable.get(clientName).getText().equals(
                                                    feeAmountUploadHistorytable.get(clientCode))){
                                Thread.sleep(2000);
                                    dataFromProcessPayoutPage = new LinkedHashMap<>();
                                    dataFromProcessPayoutPage.put(columnHeadingsProcessedPayoutTable.get(3).getText(),
                                            clientNameListProcessedPayoutTable.get(clientName).getText());
                                    dataFromProcessPayoutPage.put("Client Account Code", clientAccountCodesUploadHistory.get(clientCode));
                                    dataFromProcessPayoutPage.put(columnHeadingsProcessedPayoutTable.get(16).getText(), feeTypeListProcessedPayoutTable.get(clientName).getText());
                                    dataFromProcessPayoutPage.put("Adviser Group Name", adviserGroup);
                                    dataFromProcessPayoutPage.put("Adviser Group ARP", adviserGroupARP);
                                    dataFromProcessPayoutPage.put("Sub Product", subProductListProcessedPayoutTable.get(clientName).getText());
                                    clientNameListProcessedPayoutTable.remove(clientName);
                                    subProductListProcessedPayoutTable.remove(clientName);
                                    feeTypeListProcessedPayoutTable.remove(clientName);
                                    break;
                                }
                            else{
                                continue;
                            }
                        }
                        consolidatedDataProcessPayoutPage.add(dataFromProcessPayoutPage);
                    }
                }

                // Close Product Provider and Adviser Group
                productProvidersListAdvisergroup.get(i).findElement(expandProductProviderLink).click();
                adviserGroupsProcessedPayoutTable.get(i).findElement(expandAdviserGroupLink).click();
                Thread.sleep(4000);
            }catch (Exception e){
                System.out.println("No Adviser Group Found.");
            }
        }
        return consolidatedDataProcessPayoutPage;
    }

    public List<HashMap<String,String>> getAllDataFromUploadAndProcessPayout(List<LinkedHashMap<String,String >> uploadData
            ,List<LinkedHashMap<String,String>> payoutData) {

        List<HashMap<String,String>> allDataForAdviserSpiltRatio = new LinkedList<>();
        HashMap<String,String> clientData= null;

        for (int i = 0; i < uploadData.size(); i++) {
            for (int j = 0; j < payoutData.size(); j++) {
                if (uploadData.get(i).get("Sub Product Code").equals(payoutData.get(j).get("Sub Product"))
                        && uploadData.get(i).get("Client Name").equals(payoutData.get(j).get("Client Name"))
                        && uploadData.get(i).get("Fee Amount($)").equals(payoutData.get(j).get("Fee($)"))
                        && uploadData.get(i).get("GST($)").equals(payoutData.get(j).get("GST($)"))) {

                    clientData=new LinkedHashMap<>();
                    clientData.putAll(uploadData.get(i));
                    clientData.putAll(payoutData.get(j));
                    allDataForAdviserSpiltRatio.add(clientData);
                }
            }
        }
        return allDataForAdviserSpiltRatio;
    }

    public boolean verifyAdviserGroupsSplitRatios(List<HashMap<String,String>> splitRatiosForAllAdviserGroups)
    {
        boolean splitRatio = false;
        int matchedSplitRatioCounter = 0;
        for(HashMap<String, String> record:splitRatiosForAllAdviserGroups)
        {
            if(Double.valueOf(record.get("Split Ratio(1)")).equals(Double.valueOf(record.get("Adviser Group Split Ratio"))))
            {
                splitRatio = true;
                matchedSplitRatioCounter++;
            }
        }
        if(splitRatio=true && matchedSplitRatioCounter==splitRatiosForAllAdviserGroups.size())
        {
            splitRatio=true;
        }
        return splitRatio;
    }

    public boolean isProportionalDataAccurate(List<LinkedHashMap<String, String>> proportionalFeeData,List<LinkedHashMap<String, String>> processPayoutProportionalData) throws ParseException {
        boolean isSimilar = false;
        List<Boolean> checkResults = new LinkedList<>();
        int index = -1, indexProportion = -1;
        int k = 0 , k2 = 0;
        while (k < processPayoutProportionalData.size() && k2 < proportionalFeeData.size()){
            for(int i = k; i < processPayoutProportionalData.size(); i++){
                LinkedHashMap<String, String> map = processPayoutProportionalData.get(i);
                if(map.containsKey("Adviser Name")){
                    index = i;
                    break;
                }
            }

            for(int i = k2; i < proportionalFeeData.size(); i++){
                LinkedHashMap<String, String> map = proportionalFeeData.get(i);
                if(map.containsKey("Adviser Name")){
                    indexProportion = i;
                    break;
                }
            }
            boolean result = checksOnData(proportionalFeeData, indexProportion, k2, processPayoutProportionalData, index, k);
            checkResults.add(result);
            k = index + 1;
            k2 = indexProportion + 1;
        }
        if(checkResults.contains(false)){
            isSimilar = false;
        }
        else{
            isSimilar = true;
        }
        return isSimilar;
    }



    public boolean checksOnData(List<LinkedHashMap<String, String>> proportionalFeeData, int indexProportion, int startProportion,
                                List<LinkedHashMap<String, String>> processPayoutProportionalData, int index, int start) throws ParseException {
        int count = startProportion;
        List<Integer> flags = new LinkedList<>();
        for(int i = startProportion; i < indexProportion; i++){
            int flag = -1;
            LinkedHashMap<String, String> feeDetailsMap = proportionalFeeData.get(i);
            String feeName = feeDetailsMap.get("Fee Name");
            String proportion = feeDetailsMap.get("Proportion(%)");
            String proportionStartDate = feeDetailsMap.get("Start Date");
            Date proportionFeeStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(proportionStartDate);
            String payout = processPayoutProportionalData.get(start).get("Payout");
            String totalGST = processPayoutProportionalData.get(start).get("Total Gst");
            String totalGstPlusPayout = processPayoutProportionalData.get(start).get("TotalGstPlusPayout");
            for(int j = start; j < index; j++){
                LinkedHashMap<String, String> proportionalDataMap = processPayoutProportionalData.get(j);
                if(proportionalDataMap.containsKey("Name")){
                    if(proportionalDataMap.get("Name").contains(feeName) && proportionFeeStartDate.before(getConfirmationData())){
                        flag = 0;
                        String amount = proportionalDataMap.get("Amount($)");
                        String gst = proportionalDataMap.get("GST($)");
                        String total = proportionalDataMap.get("Total($)");
                        double totalDouble = Double.parseDouble(total);
                        double amountD = Double.parseDouble(amount);
                        double gstD = Double.parseDouble(gst);
                        double proportionD = Double.parseDouble(proportion);
                        double payoutD = Double.parseDouble(payout);
                        double totalGSTD = Double.parseDouble(totalGST);
                        double totalGstPlusPayoutD = Double.parseDouble(totalGstPlusPayout);
                        double sum = payoutD + totalGSTD;
                        double netAmount = Double.parseDouble(df2.format((proportionD * payoutD)/100));
                        double netGST = Double.parseDouble(df2.format((proportionD * totalGSTD)/100));
                        double netTotal = netAmount + netGST;
                        double netTotalGstPlusPayout = Double.parseDouble(df2.format((proportionD * totalGstPlusPayoutD)/100));
                        if(((sum == totalGstPlusPayoutD) || Math.round(Math.abs(sum - totalGstPlusPayoutD)*100.0)/100.0 <= 0.01)
                                && ((amountD == netAmount) || Math.round(Math.abs(netAmount - amountD)*100.0)/100.0 <= 0.01)
                                && ((gstD == netGST) || Math.round(Math.abs(netAmount - amountD)*100.0)/100.0 <= 0.01)
                                && ((totalDouble == netTotal) || Math.round(Math.abs(netTotal - totalDouble)*100.0)/100.0 <= 0.01)
                                && ((netTotalGstPlusPayout == totalDouble) || Math.round(Math.abs(netTotalGstPlusPayout - totalDouble)*100.0)/100.0 <= 0.01)){
                            count = count + 1;
                            flag = 1;
                            break;
                        }
                    }
                }
            }
            if(flag == 1 || flag == -1){
                flags.add(flag);
            }else if(flag == 0){
                flags.add(flag);
            }
        }
        if(!flags.contains(0)){
            return true;
        }else{
            return false;
        }
    }

    public Date getConfirmationData() throws ParseException {
        Date confirmationDate = new SimpleDateFormat("dd/MM/yyyy").parse(confirmationDateProcessPayoutPage.getAttribute("value"));
        return confirmationDate;
    }
    public List<LinkedHashMap<String, String>> getProportionalDataOfAdvisers() throws InterruptedException {
        List<LinkedHashMap<String, String>> data = new LinkedList<>();
        int length = adviserGroupPanels.size();
        for (int i = 0; i < length; i++) {
            LinkedHashMap<String, String> adviserDetails = new LinkedHashMap<>();
            LinkedHashMap<String, String> payoutAndGstDetails = new LinkedHashMap<>();
            Thread.sleep(1000);
            adviserGroupsExpandLinks.get(i).click();
            Thread.sleep(2000);
            productProviderExpandLink.get(i).click();
            Thread.sleep(2000);
            String[] panelParts = adviserNameAndRedIdFromPanelHeading(adviserGroupPanels.get(i).getText());
            String adviserName = panelParts[0];
            String adviserRepId = panelParts[1];
            adviserDetails.put("Adviser Name", adviserName);
            adviserDetails.put("Adviser RepId", adviserRepId);
            payoutAndGstDetails.put("Payout", payoutCell.getText());
            payoutAndGstDetails.put("Total Gst", totalGstCell.getText());
            payoutAndGstDetails.put("TotalGstPlusPayout", TotalAdvisor.getText());

            data.add(payoutAndGstDetails);
            for (int j = 0; j < fixedFeeNames.size(); j++) {
                LinkedHashMap<String, String> dataToCollect = new LinkedHashMap<>();
                if (fixedFeeNames.get(j).getText().contains("Proportion Fee")) {
                    dataToCollect.put(fixedfeeTableHeadName.getText(), fixedFeeNames.get(j).getText());
                    dataToCollect.put(fixedfeeTableHeadAmount.getText(), fixedFeeAmount.get(j).getText());
                    dataToCollect.put(fixedfeeTableHeadGst.getText(), fixedFeeGst.get(j).getText());
                    dataToCollect.put(fixedfeeTableHeadTotal.getText(), fixedFeeTotal.get(j).getText());
                    data.add(dataToCollect);
                }
            }
            data.add(adviserDetails);
            Thread.sleep(2000);
            productProviderExpandLink.get(i).click();
            Thread.sleep(1000);
            adviserGroupsExpandLinks.get(i).click();
        }
        return data;
    }

    public String[] adviserNameAndRedIdFromPanelHeading(String panelHeading) {
        panelHeading = panelHeading.replace("Adviser Group : : ", "");
        panelHeading = panelHeading.replace("(", " ");
        panelHeading = panelHeading.replace(")", "");
        String[] panelParts = panelHeading.split(" ");
        return panelParts;
    }

    public String fixedFeeDescriptionFromTotalPaymentTable(String description) {
        String[] descriptionParts = description.split("[/, :]");
        String payoutTableDescription;
        payoutTableDescription = descriptionParts[0] + ":" + " " + descriptionParts[2];
        return payoutTableDescription;
    }

    public String proportionFeeDescriptionFromTotalPaymentTable(String description)
    {
        String[] descriptionParts = description.split("[:]");
        String payoutTableDescription;
        payoutTableDescription = descriptionParts[0] + ":" + " " + descriptionParts[1];
        return payoutTableDescription;
    }

    /**
     * Takes user to Upload History Page by clicking on Upload Data File Link under Data Management
     */
    public void goToUploadHistoryPage(){
        uploadHistoryPageLink.click();
    }

    public boolean matchLicenseeReferrerSplitRatiosFromProcessPayoutPage(List<LinkedHashMap<String, String>> dataForClientsMappedWithReferrerWithSplitRatio,
                                                                         List<LinkedHashMap<String, String>> dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio,
                                                                         List<LinkedHashMap<String, String>> consolidatedDataFromProcessPayoutPage){
        boolean dataMatch = false;
        dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.addAll(dataForClientsMappedWithReferrerWithSplitRatio);
        for(int parentLoop = 0; parentLoop < dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.size(); parentLoop++){
            for(int childLoop = 0; childLoop < consolidatedDataFromProcessPayoutPage.size(); childLoop++) {
                if (dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.get(parentLoop).get("Adviser Group Name").equals(consolidatedDataFromProcessPayoutPage.get(childLoop).get("Adviser Group"))
                        && dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.get(parentLoop).get("Sub Product").equals(consolidatedDataFromProcessPayoutPage.get(childLoop).get("Sub Product"))
                        && dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.get(parentLoop).get("Client Name").equals(consolidatedDataFromProcessPayoutPage.get(childLoop).get("Client Name"))
                        && dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.get(parentLoop).get("Fee Type").equals(consolidatedDataFromProcessPayoutPage.get(childLoop).get("Fee Type"))) {
                    if(consolidatedDataFromProcessPayoutPage.get(childLoop).get("Name").equals("NA")){
                        continue;
                    }
                    else{
                        dataMatch = dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.get(parentLoop).get("Referrer Name").equals(
                                consolidatedDataFromProcessPayoutPage.get(childLoop).get("Name")) && dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio.get(
                                parentLoop).get("Split Ratio").equals(String.valueOf(Math.round(Double.valueOf(consolidatedDataFromProcessPayoutPage.get(childLoop).get("Split Ratio")))));
                        break;
                    }
                }
            }
            if(dataMatch){
                continue;
            }
            else{
                break;
            }
        }
        return dataMatch;
    }

    public void goToAdviserGroupPage() throws InterruptedException {
        new Actions(driver).moveToElement(businessSetupLink).click().build().perform();
        Thread.sleep(1000);
        advisorsGroupLink.click();
        Thread.sleep(3000);
    }

}
