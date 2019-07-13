package com.adviserlogic.automation.ui.pages.data_management;

import com.adviserlogic.automation.common.pages.BasePage;
import com.adviserlogic.automation.common.config.Properties;
import com.adviserlogic.automation.ui.components.UploadedFileTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class UploadHistoryPage extends BasePage {

    private String productProvider;

    UploadedFileTable tableContents = new UploadedFileTable(driver);

    public UploadHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".input-group .form-control.ui-autocomplete-input")
    private WebElement searchBox;

    @FindBy(css = ".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content>li")
    private List<WebElement> providerDropdown;

    @FindBy(css = ".table.custom_calander>tbody>tr:nth-of-type(2)>td")
    private List<WebElement> monthList;

    @FindBy(css = "#tblUploadedFiles>tbody>tr>td:nth-of-type(1)")
    private List<WebElement> uploadedFile;

    @FindBy(css="#tblUploadedFiles>tbody>tr>td:nth-of-type(11)>a")
    private WebElement deleteUploadedFile;

    @FindBy(css="#confirmBox:nth-of-type(1)>.warning_control>#btnconfirm")
    private WebElement deleteConfirmButton;

    @FindBy(css = "#tblUploadedFiles>tbody>tr")
    private List<WebElement> uploadedFilesList;

    @FindBy(css = "#tblHistory>tbody>tr[id^=gvFee2_DXGroupRowExp]>td:nth-of-type(2)")
    private List<WebElement> uploadHistoryTableStatusList;

    @FindBy(css = "#tblUploadedFiles>thead>tr>th")
    private List<WebElement> alreadyUploadedFileHeader;

    @FindBy(css = "#tblUploadedFiles>tbody>tr>td")
    private List<WebElement> alreadyUploadedFileContent;

    @FindBy(css = "#gvFee2_DXGroupRowExp0>td:nth-of-type(1)>span")
    private static WebElement expandedViewButton;

    @FindBy(css = "div.datepicker_custom>#tblCalender>tbody>tr>td[class='day']")
    private List<WebElement> calDates;

    @FindBy(css = "#browseButton")
    private WebElement browseFileButton;

    @FindBy(css = "div.modal-footer>#btnUploadFile")
    private WebElement uploadButton;

    @FindBy(css = "div.modal-content #btnClose")
    private WebElement closeButton;

    @FindBy(css = "#divUploadFile>.modal-dialog>.modal-content")
    private WebElement fileSuccessfullyUploadedDialog;

    @FindBy(css = "#lblmessageUploadFile label:nth-child(1)")
    private WebElement duplicateFileUploadMessage;

    @FindBy(css = "#divUploadFile>.modal-dialog>.modal-content> .modal-header")
    private WebElement fileUploadedModelHeader;

    @FindBy(css = "#divUploadFile>.modal-dialog>.modal-content> .modal-body>.row>.col-xs-12.col-sm-6.col-md-12>.data-popup-uploadfile>div:nth-of-type(1)>label")
    private List<WebElement> fileSuccessfulyUploadedMessage;

    @FindBy(css = "#divUploadFile>.modal-dialog>.modal-content> .modal-body>.row>.col-xs-12.col-sm-6.col-md-12>.data-popup-uploadfile>div")
    private WebElement uploadedFileData;

    @FindBy(css = "#divUploadFile>.modal-dialog>.modal-content> .modal-footer>#btnClose")
    private WebElement dialogCloseButton;

    @FindBy(css = "#tblHistory [id *= 'gvFee2_DXGroupRowExp']")
    private List<WebElement> uploadHistoryRowsList;

    @FindBy(css = "[class *= 'child chiledxvdatarow']")
    private List<WebElement> uploadHistoryTransactionRows;

    @FindBy(css = "#tblHistory>tbody>tr[id^=gvFee2_DXGroupRowExp]>td:nth-of-type(1)>span")
    private List<WebElement> uploadHistoryTableStatusExpandLinks;

    @FindBy(css = "#tblHistory>tbody>tr[id^=gvFee2_DXGroupRowExp]>td:nth-of-type(3)")
    private List<WebElement> uploadHistoryTransactionCountList;

    @FindBy(css = "[id *= 'DXDataRow']>td:nth-of-type(8)")
    private List<WebElement> bouncedTransactionsFeeTypes;

    @FindBy(css = "a[href *= 'Apps/BouncedRecords.aspx']")
    private WebElement bouncedRecordsPageLink;

    @FindBy(css = "[alt = 'Delete File']")
    private WebElement deleteFileIcon;

    @FindBy(css = ".rightWrap #confirmBox [id = 'btnconfirm']")
    private WebElement fileDeleteConfirmationButton;

    By uploadHistoryTableStatus = By.cssSelector("td:nth-of-type(2)");

    By uplaodHistoryTableTransactionCount = By.cssSelector("td:nth-of-type(3)");

    By uploadHistoryTableFeeType = By.cssSelector("td:nth-of-type(8)");

    @FindBy(css = "#tblHistory>tbody>tr[id^=gvFee2_DXGroupRowExp]>td:nth-of-type(2)")
    private List<WebElement> all_status;

    @FindBy(css = "#tblHistory>tbody>tr[id^=gvFee2_DXGroupRowExp]>td:nth-of-type(1)")
    private  List<WebElement> all_status_expand;

    @FindBy(css = "#tblHistory>tbody>tr[id^=gvFee2_DXGroupRowExp]>td:nth-of-type(3)")
    private  List<WebElement> all_status_count;

    @FindBy(css = "#tblHistory>tbody>tr[id^=gvFee2_DXDataRow]  td:nth-of-type(4)")
    private List<WebElement> unMappedAdviserCodes;

    @FindBy(css = "#gvFee2_DXGroupRowExp0>td")
    private static List<WebElement> tableStatusRow1;

    @FindBy(css = "#ulLeftPanel>li:nth-of-type(4)>ul>li:nth-of-type(2)>a")
    WebElement processPayoutLinkLeftRail;

    @FindBy(css="tr[id*=gvFee2_DXGroupRowExp]")
    private List<WebElement> statusRows;

    @FindBy(css = "tr[id*=gvFee2_DXDataRow]")
    private List<WebElement> transactionRows;

    @FindBy(css = "[id *= 'DXDataRow']>td:nth-of-type(7)")
    private List<WebElement> subProductListUploadHistoryTable;

    @FindBy(css = "[id *= 'DXDataRow']>td:nth-of-type(9)")
    private List<WebElement> feeAmountList;

    @FindBy(css = "table.table.custom_calander >tbody >tr:nth-of-type(2) >td:nth-of-type(2)")
    private WebElement box;

    By rowColumn = By.cssSelector("td");
    public static final String tableId = "lstUploadDataFile";
    public static final String tableRowStatus = "lstUploadDataFile>tr:nth-of-type(1)>td";
    By monthTitle = By.cssSelector("div");

    public static String uploadTableHeader="table table-bordered table-striped dataTable table1 table-condensed onpaytable";

    /**
     * Selects product provider from the product provider dropdown
     * @throws Exception
     */
    public String selectProductProvider(String productProvider) throws Exception {
        List<WebElement> dropwndown;
        searchBox.click();
        searchBox.sendKeys(productProvider);
        Thread.sleep(3000);
        try{
            providerDropdown.get(0).isDisplayed();
            dropwndown = providerDropdown;
        }
        catch (Exception e){
            throw new Exception("Product Provider dropdown is not displayed");
        }

        for (WebElement element : dropwndown) {
            if (element.getText().equals(productProvider)) {
                element.click();
                break;
            } else {
                throw new Exception("Product Provider is not displayed in the dropdown list");
            }
        }
        return productProvider;
    }

    @FindBy(css="#tblHistory tr[class*='child chiledxvdatarow0'] td:nth-of-type(5)")
    private List<WebElement> clientNames;

    @FindBy(css="#tblHistory tr[class*='child chiledxvdatarow0'] td:nth-of-type(6)")
    private List<WebElement> clientCodes;
    /**
     * Checks if bounced status section is displayed on upload history page
     * @return
     */
    public boolean isGivenStatusDisplayed(String status){
        boolean isGivenStatusDisplayed = false;
        for(int i = 0; i <= uploadHistoryRowsList.size()-1; i++){
            if(uploadHistoryRowsList.get(i).findElement(uploadHistoryTableStatus).getText().equalsIgnoreCase(status)){
                isGivenStatusDisplayed = true;
                break;
            }
        }
        return isGivenStatusDisplayed;
    }

    public void box() throws InterruptedException
    {
        box.click();
    }

    /**
     * Gets the duplicate file upload message
     * @return
     */
    public WebElement getDuplicateFileUploadMessage(){
        return duplicateFileUploadMessage;
    }

    /**
     * Gets the latest uploaded file from list of uploaded file
     * @return
     */
    public WebElement getLatestUploadedFile(){
        return uploadedFilesList.get(uploadedFilesList.size()-1);
    }

    /**
     * Clicks on the expand link for the Bounced Status from Upload History table
     */
    public void expandBouncedStatus() {
        List<WebElement> statusLinks = uploadHistoryTableStatusList;
        List<WebElement> expandLinks = uploadHistoryTableStatusExpandLinks;
        for (int i = 0; i < statusLinks.size(); i++) {
            if (statusLinks.get(i).getText().equalsIgnoreCase("bounced")) {
                expandLinks.get(i).click();
            }
        }
    }

    /**
     * Gets the lst of fee types of bounced table under upload history table
     */
    public Set<String> getFeeTypeFromBouncedRecords() throws Exception {
        Set<String> bouncedFeeTypes = new LinkedHashSet<>();
        if(isGivenStatusDisplayed("bounced")){
            expandBouncedStatus();
            bouncedFeeTypes = bouncedTransactionsFeeTypes.stream().map(m -> m.getText()).filter(
                    f -> !f.isEmpty()).collect(Collectors.toSet());
        }
        else{
            throw new Exception("Bounced Records are not displayed ! Please upload another file");
        }
        return bouncedFeeTypes;
    }

    public static List<WebElement> tableRowStatus() {
        List<WebElement> statusRow = tableStatusRow1;
        return statusRow;
    }

    /**
     * Selects the current month of the year from calender table
     * @param month
     */
    public void selectUploadHistoryMonth(int month) {
        List<WebElement> months = monthList;
        for (WebElement mon : months) {
            if (Integer.valueOf(mon.getAttribute("month").toString()).equals(month)) {
                mon.click();
                break;
            }
        }
    }

    public boolean verifyFileAlreadyUploadedForCurrentMonth(int month) {
        boolean fileUploaded=false;
        List<WebElement> months = monthList;
        for (WebElement mon : months) {
            if (Integer.valueOf(mon.getAttribute("month").toString()).equals(month)) {
                if(mon.findElement(monthTitle).getAttribute("title").contains("File will be uploaded in earlier month")) {
                    fileUploaded = false;
                    break;
                }
                else if(mon.findElement(monthTitle).getAttribute("title").contains("file(s) have been uploaded for this month"))
                {
                    fileUploaded =true;
                    break;
                }
            }
        }
        return fileUploaded;
    }


    public void selectAlreadyUploadedFile(String fileName, String productProvider) throws Exception {
        selectProductProvider(productProvider);
        Thread.sleep(2000);
        selectUploadHistoryMonth(LocalDate.now().getMonthValue());
        selectUploadedFile(fileName);
    }

    public void uploadNewFile(String filePath) throws Exception {
        Thread.sleep(2000);
        selectUploadHistoryMonth(LocalDate.now().getMonthValue());
        Thread.sleep(2000);
        selectPresentDateToUploadFile(LocalDate.now().getDayOfMonth());
        Thread.sleep(2000);
        uploadFile(filePath);
        Thread.sleep(2000);
    }

    /**
     * Selects the current date of the month to upload a file
     * @param date
     */
    public void selectPresentDateToUploadFile(int date) throws InterruptedException {
        List<WebElement> dates = calDates;
        for (int i = 0; i < dates.size(); i++) {
            if (!dates.get(i).getText().equals("") ) {
                if (Integer.valueOf(dates.get(i).getText()).equals(date)) {
                    dates.get(i).click();
                    break;
                }
            }
        }
    }

    /**
     * Uploads a file from a given filepath
     * @param filePath
     * @throws InterruptedException
     */
    public void uploadFile(String filePath) throws Exception {
        browseFileButton.sendKeys(filePath);
        Thread.sleep(3000);
        uploadButton.click();
        Thread.sleep(10000);
        if(getDuplicateFileUploadMessage().getText().contains("You are trying to upload a file with same name, no of" +
                " records and Fee Amount which has been uploaded previously!")){
            throw new Exception("Duplicate File Found ! Please Upload a New File which has not been uploaded before.");
        }
    }

    /**
     * Deletes an uploaded file
     * @throws InterruptedException
     */
    public void deleteUploadedFile() throws InterruptedException {
        deleteFileIcon.click();
        Thread.sleep(2000);
        fileDeleteConfirmationButton.click();
    }

    /**
     * File successfully uploaded dialog confirmation pop-up
     * @return
     */
    public boolean fileUploadedDialog() {
        boolean isPresent = fileSuccessfullyUploadedDialog.isDisplayed();
        return isPresent;
    }

    /**
     * Extract file uploded date from File successfully uploaded dialog
     * @return
     */
    public String fileUploadedDate() {
        return fileUploadedModelHeader.getText();
    }

    public String fileSuccessfullyUploaded() {
        List<WebElement> message = fileSuccessfulyUploadedMessage;
        String confirmation = null;
        for (WebElement m : message) {
            if (m.getText().equals("File Uploaded Successfully!")) {
                confirmation = m.getText();
            }
        }
        return confirmation;
    }

    public LinkedHashMap<String, String> uploadedFileDataInUploadDialog() {
        LinkedHashMap<String, String> labels = new LinkedHashMap<>();
        for (int i = 2; i <= 6; i++) {
            List<WebElement> uploadedDatalabel = driver.findElements(By.cssSelector("#divUploadFile>.modal-dialog>.modal-content> .modal-body>.row>.col-xs-12.col-sm-6.col-md-12>.data-popup-uploadfile>div:nth-of-type(" + i + ")>label"));
            List<WebElement> uploadedDataSpan = driver.findElements(By.cssSelector("#divUploadFile>.modal-dialog>.modal-content> .modal-body>.row>.col-xs-12.col-sm-6.col-md-12>.data-popup-uploadfile>div:nth-of-type(" + i + ")>span"));
            for (int j = 0; j < uploadedDatalabel.size(); j++) {
                labels.put(uploadedDatalabel.get(j).getText(), uploadedDataSpan.get(j).getText());
            }
        }
        return labels;
    }

    /**
     * Dismiss successfully uploaded file pop-up
     * @throws InterruptedException
     */
    public void dismissFileUploadedDialog() throws InterruptedException {
        try{
            dialogCloseButton.click();
        }
        catch(Exception e){
            System.out.println("File could not be uploaded");
        }
    }

    /**
     * Select already uploaded file
     * @param fileName
     */
    public void selectUploadedFile(String fileName) {
        for(WebElement element : uploadedFile) {
            if (element.getAttribute("title").equals(fileName)) {
                element.click();
                break; }
        }
    }

    /**
     * Reads the data for uploaded files summary table
     *
     * @return Hashmap for uploaded files summary data
     */
    public LinkedHashMap<String, String> uploadedFileTableSummararyData() {
        LinkedHashMap<String, String> alreadyUploadedFile = new LinkedHashMap<>();
        List<WebElement> heading = alreadyUploadedFileHeader;
        List<WebElement> values = alreadyUploadedFileContent;
        for (int i = 1; i < alreadyUploadedFileHeader.size() - 2; i++) {
            alreadyUploadedFile.put(heading.get(i).getText(), values.get(i).getText());
        }
        return alreadyUploadedFile;
    }

    /**
     * Gets the upload date for the uploaded commission statement
     *
     * @return
     */
    public LocalDateTime getUploadDateForCommissionStatement() {
        LocalDateTime uploadDate = LocalDateTime.parse(uploadedFileTableSummararyData().get("Upload Date"),
                DateTimeFormatter.ofPattern("DD/MM/YYYY"));
        return uploadDate;
    }

    /**
     * Read upload table data
     * @param uploadedSummaryData
     * @return
     * @throws InterruptedException
     */
    public List<LinkedHashMap<String, String>> readTableData(LinkedHashMap<String,String> uploadedSummaryData) throws InterruptedException {
        return tableContents.getTableData(uploadedSummaryData);
    }


    /**
     * Clicks on process payout link displayed in the left rail
     */
    public void goToProcessPayoutPage() throws InterruptedException {
        processPayoutLinkLeftRail.click();
        Thread.sleep(3000);
    }




    /**
     * Expand unMapped Records
     */
    public void expandUnMapped() {
        List<WebElement> statusLinks = all_status;
        List<WebElement> expandLinks = all_status_expand;

        for (int i = 0; i < statusLinks.size(); i++) {
            if (statusLinks.get(i).getText().equals("UnMapped")) {
                expandLinks.get(i).click();
            }
        }
    }

    /**
     * Expand process payout records
     */
    public void expandProcessPayout()
    {
        List<WebElement> statusLinks = all_status;
        List<WebElement> expandLinks = all_status_expand;

        for (int i = 0; i < statusLinks.size(); i++) {
            if (statusLinks.get(i).getText().equals("Process Payout")) {
                expandLinks.get(i).click();
            }
        }
    }

    /**
     * Extracting adviser Code values for unMapped Records
     * @return
     */
    public Set<String> adviserCodeValues(){
        Set<String> adviserCodes = unMappedAdviserCodes.stream().map(m->m.getText()).filter(m-> !(m.isEmpty())).collect(Collectors.toSet());
        return adviserCodes;
    }

    /**
     * Extracting file name from file path
     * @param filePath
     * @return
     */
    public String fileName(String filePath)
    {
        String[] filePathDivision = filePath.toString().split("/");
        String fileName = filePathDivision[filePathDivision.length - 1];
        return fileName;
    }

    /**
     * Storing Upload table data into data structure
     * @return
     * @throws InterruptedException
     */
    public List<LinkedHashMap<String, String>> readUploadedFileRecords() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        List<WebElement> tableRowHeader = (List<WebElement>) js.executeScript("return " +
                "document.getElementsByClassName('" +uploadTableHeader+"')[0].children[0].children[0].children");
        List<String> tableRowSubHeading = tableRowHeader.stream().map(m->m.getText()).collect(Collectors.toList());
        List<LinkedHashMap<String, String>> tableData = new LinkedList<LinkedHashMap<String, String>>();
        LinkedHashMap<String, String> valueMapping;
        List<List<String>> tableRowData = new LinkedList<List<String>>();
        List<String> values = null;
        Thread.sleep(2000);
        List<WebElement> totalTableRows = (List<WebElement>) js.executeScript("return document.getElementById('" + tableId + "').children;");
        List<WebElement> rowValues = (List<WebElement>) js.executeScript("return document.getElementById('" + tableId + "').children[1].children;");
        for(int i=0;i<totalTableRows.size();i++)
        {
           values= new LinkedList<>();
           for(int j=2;j<rowValues.size();j++)
           {
               values.add(js.executeScript("return document.getElementById('" + tableId + "').children["+ i +"].children["+ j+"].innerHTML;").toString());
           }
           if(!(values.get(4).equals("")) && !(values.get(6).equals("")) && !(values.get(8).equals(""))){
           tableRowData.add(values);}
        }

        for (int i = 0; i < tableRowData.size(); i++) {
            valueMapping = new LinkedHashMap<String, String>();
            for (int j = 2; j < tableRowSubHeading.size(); j++) {
                valueMapping.put(tableRowSubHeading.get(j), tableRowData.get(i).get(j-2));
            }
            tableData.add(valueMapping);
        }
        return tableData;
    }

    /**
     * Extract only process payout records from upload file table data
     */
    public List<LinkedHashMap<String, String>> readProcessPayoutData() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        List<LinkedHashMap<String, String>> tableData = new LinkedList<LinkedHashMap<String, String>>();
        LinkedHashMap<String, String> valueMapping;
        List<WebElement> tableRowHeader = (List<WebElement>) js.executeScript("return " +
                "document.getElementsByClassName('" +uploadTableHeader+"')[0].children[0].children[0].children");
        List<String> tableRowSubHeading = tableRowHeader.stream().map(m->m.getText()).collect(Collectors.toList());

        List<WebElement> statusHeader = (List<WebElement>) js.executeScript
                ("return document.getElementById('lstUploadDataFile').getElementsByClassName('parent')");

        List<List<String>> tableRowData = new LinkedList<List<String>>();

        for(int i=0;i<statusHeader.size();i++) {
           if(js.executeScript("return document.getElementById('lstUploadDataFile').getElementsByClassName('parent')["+i+"].children[1].innerHTML").toString().equals("Process Payout"))
           {
               List<WebElement> payoutRows = (List<WebElement>) js.executeScript("return document.getElementById('lstUploadDataFile').getElementsByClassName('child chiledxvdatarow"+ i +"')");
               for(int j=0;j<payoutRows.size();j++)
               {
                   List<WebElement> rowValues = (List<WebElement>) js.executeScript("return document.getElementById('lstUploadDataFile').getElementsByClassName('child chiledxvdatarow"+ i +"')["+j+"].children");
                   tableRowData.add(rowValues.stream().map(m->m.getText()).collect(Collectors.toList()));
               }
           }
        }

        for (int i = 0; i < tableRowData.size(); i++) {
            valueMapping = new LinkedHashMap<String, String>();
            for (int j = 2; j < tableRowSubHeading.size(); j++) {
                valueMapping.put(tableRowSubHeading.get(j), tableRowData.get(i).get(j));
            }
            tableData.add(valueMapping);
        }
        return tableData;

    }

    /**
     * Clean up code to delete already uploaded file
     * @throws InterruptedException
     */
    public void commissionSheetCleanUp() throws InterruptedException {
        Thread.sleep(1000);
        deleteUploadedFile.click();
        Thread.sleep(2000);
        deleteConfirmButton.click();
        Thread.sleep(2000);
    }

    /**
     * Clicks on bounced records page link displayed in the left rail
     */
    public void goToBouncedRecordsPage(){
        bouncedRecordsPageLink.click();
    }

    /**
     * Gets the total count of all transactions displayed in all statuses
     * @return
     */
    public int getSumOfAllTransactionsForAllStatus(){
        List<Integer> transactionCountList = new LinkedList<>();
        int totalTransactionCount = 0;
        transactionCountList.addAll(uploadHistoryTransactionCountList.stream().map(m -> Integer.valueOf(
                m.getText())).collect(Collectors.toList()));
        for(int i = 0; i <= transactionCountList.size()-1; i++){
            totalTransactionCount = totalTransactionCount + transactionCountList.get(i);
        }
        return totalTransactionCount;
    }

    //Get client account code and client name as Key and Value in hashmap for records in process payout status
    // from upload history.
    public HashMap getClientNameClientCodeProcessPayoutTable() throws InterruptedException {
        HashMap<String,String> Clients = new LinkedHashMap();
        int transCount = 0;
        for(int i =0; i<statusRows.size();i++){
            if(statusRows.get(i).findElements(rowColumn).get(1).getText().equals("Process Payout")){
                int processPayoutTransCount = Integer.valueOf(statusRows.get(i).findElements(rowColumn).get(2).getText());
                statusRows.get(i).findElements(rowColumn).get(0).click();
                Thread.sleep(2000);
                for(int j=transCount;j<processPayoutTransCount+transCount;j++){

                    String clientName = transactionRows.get(j).findElements(rowColumn).get(4).getText();
                    String clientCode = transactionRows.get(j).findElements(rowColumn).get(5).getText();
                    Clients.put(clientCode,clientName);
                }
                break;
            }
            transCount = transCount+Integer.valueOf(statusRows.get(i).findElements(rowColumn).get(2).getText());
        }

        return Clients;
    }

    /**
     * Returns a list of sub products from upload history table
     * @return
     */
    public List<String> getSubProductListUploadHistoryTable() {
        return subProductListUploadHistoryTable.stream().map(m -> m.getText()).filter(f -> !f.isEmpty()).collect(Collectors.toList());
    }

    /**
     * Returns the list of fee amount from upload history table
     * @return
     */
    public List<String> getFeeAmountListUploadHistoryTable(){
        return feeAmountList.stream().map(m -> m.getText()).filter(f -> !f.isEmpty()).collect(Collectors.toList());
    }


    /**
     * Get the client names from upload page
     * @return
     */
    public List<String> getClientNames() {
        return clientNames.stream().map(m->m.getText()).collect(Collectors.toList()); }

    /**
     * Get the client codes from upload page
     * @return
     */
    public List<String> getClientCodes() {
        return clientCodes.stream().map(m->m.getText()).collect(Collectors.toList()); }
}

