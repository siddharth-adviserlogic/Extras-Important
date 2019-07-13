package com.adviserlogic.automation.test;

import com.adviserlogic.automation.common.Mappers.MapperForAIAProductProvider;
import com.adviserlogic.automation.common.config.BaseRunner;
import com.adviserlogic.automation.common.config.Properties;
import com.adviserlogic.automation.common.factory.CustomPageFactory;
import com.adviserlogic.automation.common.utils.CommissionFilesReader;
import com.adviserlogic.automation.ui.pages.DashboardPage;
import com.adviserlogic.automation.ui.pages.LoginPage;
import com.adviserlogic.automation.ui.pages.business_setup.*;
import com.adviserlogic.automation.ui.pages.data_management.BouncedRecordsPage;
import com.adviserlogic.automation.ui.pages.data_management.UnMappedAccountsPage;
import com.adviserlogic.automation.ui.pages.data_management.UploadHistoryPage;
import com.adviserlogic.automation.ui.pages.payout_data.FeeTransactionsPage;
import com.adviserlogic.automation.ui.pages.payout_data.ProcessPayoutPage;

import com.adviserlogic.automation.ui.pages.reports.PayoutReportPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class E2eTest extends BaseRunner {

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private UploadHistoryPage uploadHistoryPage;
    private ProcessPayoutPage processPayoutPage;
    private AdvisersPage advisersPage;
    private UnMappedAccountsPage unMappedAccountsPage;
    private AdviserGroupsPage adviserGroupsPage;
    private FixedFeesPage fixedFeesPage;
    private ClientPage clientPage;
    private FeeTransactionsPage feeTransactionsPage;
    private BouncedRecordsPage bouncedRecordsPage;
    private ProductProviderPage productProviderPage;
    private DefineSchemaPage defineSchemaPage;
    private LicenseeReferrersPage licenseeReferrersPage;
    private PayoutReportPage payoutReportPage;

    private String productProvider;
    private LinkedHashMap<String,String> uploadedSummaryData;
    private List<LinkedHashMap<String, String>> uploadTableData;
    private Set<String> feeTypeList;
    LinkedList<Map<String, String>> excelContant=null;

    @BeforeMethod
    public void setupPages() {
        dashboardPage = CustomPageFactory.getPage(driver, DashboardPage.class);
        loginPage = CustomPageFactory.getPage(driver, LoginPage.class);
        uploadHistoryPage = CustomPageFactory.getPage(driver, UploadHistoryPage.class);
        processPayoutPage=CustomPageFactory.getPage(driver, ProcessPayoutPage.class);
        advisersPage = CustomPageFactory.getPage(driver, AdvisersPage.class);
        unMappedAccountsPage =CustomPageFactory.getPage(driver,UnMappedAccountsPage.class);
        adviserGroupsPage=CustomPageFactory.getPage(driver,AdviserGroupsPage.class);
        fixedFeesPage=CustomPageFactory.getPage(driver,FixedFeesPage.class);
        clientPage = CustomPageFactory.getPage(driver, ClientPage.class);
        feeTransactionsPage = CustomPageFactory.getPage(driver,FeeTransactionsPage.class);
        bouncedRecordsPage = CustomPageFactory.getPage(driver, BouncedRecordsPage.class);
        productProviderPage = CustomPageFactory.getPage(driver, ProductProviderPage.class);
        defineSchemaPage = CustomPageFactory.getPage(driver, DefineSchemaPage.class);
        licenseeReferrersPage = CustomPageFactory.getPage(driver, LicenseeReferrersPage.class);
        payoutReportPage = CustomPageFactory.getPage(driver, PayoutReportPage.class);
    }

    /**
     * Deletes all fee mappings for bounced records
     * @throws Exception
     */
    public void deleteMappedFeeTypesForBouncedRecords() throws Exception {
        String productProvider = Properties.getProductProvider(false, true);
        bouncedRecordsPage.logOut();
        loginPage.login(true);
        dashboardPage.goToProductProviderPage();
        productProviderPage.searchProductProviderAndGoToDefineSchemaPage(productProvider);
        defineSchemaPage.selectSchemaFromDropdown(productProvider);
        Thread.sleep(3000);
        defineSchemaPage.selectFeeTypeTab();
        Thread.sleep(2000);
        defineSchemaPage.deleteFeeMapping(feeTypeList.stream().collect(Collectors.toList()), productProvider);
    }

    /**
     * Created By Akshay Chawla
     * @throws Exception
     */
    @Test
    public void uploadFileFlow() throws Exception {
        // Login
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(true, false));
        Thread.sleep(2000);
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

        //Verify the upload dialog
        Assert.assertTrue(uploadHistoryPage.fileUploadedDialog(), "File Uploaded Dialog is not Displayed");
        String Date=uploadHistoryPage.fileUploadedDate();
        Assert.assertTrue((Date.split(":")[1].split("/")[2] + "-" +  Date.split(":")[1]
                        .split("/")[1] + "-" + Date.split(":")[1].split("/")[0]
                        .replace(" ","")).equals(LocalDate.now().toString()),
                "File upload Date is not matched");
        Assert.assertEquals(uploadHistoryPage.fileSuccessfullyUploaded(),"File Uploaded Successfully!",
                "File is not Successfully Uploaded");

        // Extracting Data from File Successfully Uploded Pop-up
        LinkedHashMap<String,String> uploadfileData=uploadHistoryPage.uploadedFileDataInUploadDialog();

        // Close File Successfully Uploded Pop-up
        uploadHistoryPage.dismissFileUploadedDialog();

        //Verify uploaded transactions data
        uploadedSummaryData = uploadHistoryPage.uploadedFileTableSummararyData();
        Assert.assertEquals(uploadfileData.get("Total Net :").split("\\$")[1].replace(" ","")
                .replaceAll(",",""),uploadedSummaryData.get("Fee Amount($)").replace(",",""),"Total Net is not matched with expected");
        Assert.assertEquals(uploadfileData.get("Total GST :").split("\\$")[1].replace(" ","")
                .replace(",",""),uploadedSummaryData.get("GST($)").replace(",",""),"Total Gst is not matched with expected");
        Assert.assertEquals(uploadfileData.get("TOTAL INC :").split("\\$")[1].replace(" ","")
                .replace(",",""),uploadedSummaryData.get("Total($)").replace(",",""),"Total Gst is not matched with expected");

        // Select Already uploaded Commission Sheet
        uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

        //Read data from commission sheet
        excelContant = CommissionFilesReader.readExcel(new File(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

        //Expand all the data status
        uploadHistoryPage.expandProcessPayout();
        uploadHistoryPage.expandUnMapped();
        uploadHistoryPage.expandBouncedStatus();

        //read data from uploaded table in uploaded history page
         uploadTableData =uploadHistoryPage.readUploadedFileRecords();

        //Map the commission sheet headers to uploaded history table headers
        MapperForAIAProductProvider mapperForAIAProductProvider = new MapperForAIAProductProvider();
        int rowMatchcounter = mapperForAIAProductProvider.mapperForAIA(excelContant,uploadTableData);

        // File Records Count match with Excel Records Count
        Assert.assertEquals(excelContant.size() - 1, rowMatchcounter, "Full Data of Excel Sheet is not uploaded");

        //Clean uploaded File
        uploadHistoryPage.commissionSheetCleanUp();
    }

    /**
     * @author : Ankur Vaid
     * Scenario : This TC verifies that the bounced records / transactions when mapped with a fee type are added to
     * either process payout or the un-mapped records on the upload history page
     */
    @Test
    public void bouncedRecordsFlow() throws Exception {
        // Navigate to the login page and login using credentials from properties file
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl),
                "Home Page is not displayed");

        // Navigate to Upload History page and select the desired product provider
        dashboardPage.goToUploadHistory();
        this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(false, true));

        // Upload a file for current month and current date
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForBouncedFlow());
        uploadHistoryPage.dismissFileUploadedDialog();

        // Select uploaded file and read all fee types from bounced records
        uploadHistoryPage.getLatestUploadedFile().click();
        int totalRecordsBeforeMappingBouncedRecords = uploadHistoryPage.getSumOfAllTransactionsForAllStatus();
        feeTypeList = uploadHistoryPage.getFeeTypeFromBouncedRecords();

        // Navigate to bounced fee page and map all fee types
        uploadHistoryPage.goToBouncedRecordsPage();
        bouncedRecordsPage.mapFeeTypes(feeTypeList);

        // Navigate back to upload history page and verify if all bounced records are mapped
        bouncedRecordsPage.goToUploadHistoryPage();
        uploadHistoryPage.selectProductProvider(Properties.getProductProvider(false, true));
        uploadHistoryPage.selectUploadHistoryMonth(LocalDate.now().getMonthValue());
        uploadHistoryPage.getLatestUploadedFile().click();
        Thread.sleep(5000);
        int totalRecordsAfterMappingBouncedRecords = uploadHistoryPage.getSumOfAllTransactionsForAllStatus();
        Assert.assertTrue(totalRecordsBeforeMappingBouncedRecords == totalRecordsAfterMappingBouncedRecords,
                "Total transactions before mapping bounced records and after mapping bounced records does not match");

        // Clean-Up bounced fee mappings and delete uploaded file
        uploadHistoryPage.deleteUploadedFile();
        Thread.sleep(3000);
        deleteMappedFeeTypesForBouncedRecords();
    }

    /**
     * Created By Akshay Chawla
     * @throws Exception
     */
    @Test
    public void unMappedAccountFlow() throws Exception {
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(true, false));
        Thread.sleep(2000);

        //Upload a new File
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

        //Close the upload file confirmation popup
        uploadHistoryPage.dismissFileUploadedDialog();

        //Select uploaded file
        uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

        //Expand UnMapped
        uploadHistoryPage.expandUnMapped();

        //Extract UnMappedAdviserCodes
        Set<String> advisorCodesToBeMapped=uploadHistoryPage.adviserCodeValues();

        // Collapse UnMapped
        uploadHistoryPage.expandUnMapped();

        //Go to UnMapped Page
        unMappedAccountsPage.goToUnMappedPage();
        Thread.sleep(2000);

        //Mapped Adviser Codes
        List<String> mappedAdvisorCode = unMappedAccountsPage.searchAdvisorCodes
                (unMappedAccountsPage.advisorCodeForMapping(advisorCodesToBeMapped),this.productProvider,"JA");

        dashboardPage.goToUploadHistory();
        uploadHistoryPage.selectAlreadyUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()),this.productProvider);

        //Expand Process Payout
        uploadHistoryPage.expandProcessPayout();

        //Extract Adviser Codes From Process Payout
        Set<String> advisorCodesInProcessPayout = uploadHistoryPage.adviserCodeValues();

        //Verify whether all adviser codes mapped are there in process payout
        Assert.assertTrue(advisorCodesInProcessPayout.containsAll(mappedAdvisorCode),
                "Accounts are not mapped and are not reflecting in process payout");

        //Collapse Process Payout
        uploadHistoryPage.expandProcessPayout();

        //Clean up code for UnMapped Accounts
        feeTransactionsPage.cleanUpMappedAdviser(mappedAdvisorCode,this.productProvider);
        dashboardPage.goToUploadHistory();
        uploadHistoryPage.selectAlreadyUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()), this.productProvider);
        uploadHistoryPage.commissionSheetCleanUp();
    }

    /**
     * Created By Akshay Chawla
     * @throws Exception
     */
    @Test
    public void processPayoutFlow() throws Exception {
        // Login
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider=uploadHistoryPage.selectProductProvider(Properties.getProductProvider(true, false));


        uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

        // Close File Successfully Uploded Pop-up
        uploadHistoryPage.dismissFileUploadedDialog();

        // Select Already uploaded Commission Sheet
        uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

        //Expand UnMapped
        uploadHistoryPage.expandUnMapped();

        //Extract UnMappedAdviserCodes
        Set<String> advisorCodesToBeMapped=uploadHistoryPage.adviserCodeValues();

        // Collapse UnMapped
        uploadHistoryPage.expandUnMapped();

        //Go to UnMapped Page
        unMappedAccountsPage.goToUnMappedPage();
        Thread.sleep(2000);

        //Mapped Adviser Codes
        List<String> mappedAdvisorCode = unMappedAccountsPage.searchAdvisorCodes
                (unMappedAccountsPage.advisorCodeForMapping(advisorCodesToBeMapped),this.productProvider,"JA");

        dashboardPage.goToUploadHistory();
        uploadHistoryPage.selectAlreadyUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()), this.productProvider);

        //Expand Process Payout
        uploadHistoryPage.expandProcessPayout();

        //read data from uploaded table in uploaded history page
        uploadTableData=uploadHistoryPage.readProcessPayoutData();

        //Collaspe Process Payout
        uploadHistoryPage.expandProcessPayout();

        //Navigate To Process Payout Page
        uploadHistoryPage.goToProcessPayoutPage();

        /*processPayoutPage.productProviderSelection(this.productProvider);*/
        processPayoutPage.selectProductProvider(this.productProvider);

        //Choose from date in process payout page
        processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

        //Click on Search Payout
        processPayoutPage.clickOnSearchPayout();

        //Extract Process Payout Data
        List<HashMap<String,String>> payoutData=processPayoutPage.readprocessPayoutData();

        //Verify data under process payout status in upload file page is there in process payout page
        Assert.assertTrue(processPayoutPage.isUploadDataInPayout(uploadTableData,payoutData),
                "Process Payout Data is not matched");

        //Clean up Code for Process Payout Flow
        feeTransactionsPage.cleanUpMappedAdviser(mappedAdvisorCode,this.productProvider);
        dashboardPage.goToUploadHistory();
        uploadHistoryPage.selectAlreadyUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()), this.productProvider);
        uploadHistoryPage.commissionSheetCleanUp();
    }


    /**
     * Created By Akshay Chawla
     * @throws Exception
     */
    @Test
    public void adviserGroupSpiltRatioFlow() throws Exception {

        // Login
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider=uploadHistoryPage.selectProductProvider(Properties.getProductProvider(true, false));
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

        // Close File Successfully Uploded Pop-up
        uploadHistoryPage.dismissFileUploadedDialog();

        // Select Already uploaded Commission Sheet
        uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

        //Expand Process Payout
        uploadHistoryPage.expandProcessPayout();

        //read data from uploaded table in uploaded history page
        uploadTableData=uploadHistoryPage.readProcessPayoutData();

        //Collaspe Process Payout
        uploadHistoryPage.expandProcessPayout();

        //Navigate To Process Payout Page
        uploadHistoryPage.goToProcessPayoutPage();

        //Filter Product Provider in process payout dropdown
        processPayoutPage.selectProductProvider(this.productProvider);

        //Choose from date in process payout page
        processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

        //Click on Search Payout
        processPayoutPage.clickOnSearchPayout();

        //Extract Process Payout Data
        List<LinkedHashMap<String,String>> payoutData=processPayoutPage.readprocessPayoutDataWithGroupAssociation();

        //All Data from upload and process payout page
        List<HashMap<String,String>> allData=processPayoutPage.getAllDataFromUploadAndProcessPayout(uploadTableData,payoutData);

        //Navigate To Client Page
        processPayoutPage.goToClientsPage();

        //Read the Client Rule from Client Page and append the split Ratio
        List<HashMap<String,String>> clientDataWithCR=clientPage.clientDataWithClientRule(allData,this.productProvider);

        //Adding Client Rule and Referrer Names if found any in CLient Page
        List<HashMap<String,String>> clientDataWithCRAndReferrerName=clientPage.modifiyClientRuleDataWithReferrerName(clientDataWithCR);

        //Go To Adviser Group Page
        clientPage.goToAdviserGroupsPage();

        //Extract the Split Ratios for those advisers/Client who don't have any client rulw assoicated
        List<HashMap<String,String>> splitRatiosForAllAdviserGroups = adviserGroupsPage.getAdviserGroupSplitRatio(clientDataWithCRAndReferrerName,this.productProvider);

        //Navigate to Process Payout Page
        adviserGroupsPage.goToProcessPayoutPage();

        //Filter Product Provider in process payout dropdown
        processPayoutPage.selectProductProvider(this.productProvider);

        //Choose from date in process payout page
        processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

        //Click on Search Payout
        processPayoutPage.clickOnSearchPayout();

        //Verification for split Ratios in Process Payout Page and various split Ratios extracted from Rules
        Assert.assertTrue(processPayoutPage.verifyAdviserGroupsSplitRatios(splitRatiosForAllAdviserGroups),
                "For All the Records Adviser Group Split Ratio is not matched.");

        //Clean-up Code
        dashboardPage.goToUploadHistory();
        uploadHistoryPage.selectAlreadyUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()), this.productProvider);
        uploadHistoryPage.commissionSheetCleanUp();
    }

    /**
     * @author: Ankur Vaid
     * Scenario: Get all clients from upload history which are in process payout status.Check Licensee Referrer Name
     * and Licensee Referrer split ratio mapped with each client on the basis of client rules, split fee and
     * fixed split. Verify same for each client in process payout page.
     **/
    @Test
    public void licenseeReferrerSplitRatioFlow() throws Exception {
        // Navigate to the login page and login using credentials from properties file
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl),
                "Home Page is not displayed");

        // Navigate to Upload History page and select the desired product provider
        dashboardPage.goToUploadHistory();
        this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(false, true));

        // Upload a file for current month and current date
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForBouncedFlow());
        uploadHistoryPage.dismissFileUploadedDialog();

        //Select the uploaded file and get Client Name and Client Code from upload history table for process
        // payout status records
        uploadHistoryPage.getLatestUploadedFile().click();
        Thread.sleep(3000);
        HashMap clientNameClientCodeProcessPayoutTable = uploadHistoryPage.getClientNameClientCodeProcessPayoutTable();
        Thread.sleep(2000);
        List<String> subProductListUploadHistorytable = uploadHistoryPage.getSubProductListUploadHistoryTable();
        List<String> feeAmountUploadHistorytable = uploadHistoryPage.getFeeAmountListUploadHistoryTable();

        uploadHistoryPage.goToProcessPayoutPage();
        Thread.sleep(3000);
        processPayoutPage.selectFromDate(LocalDateTime.now().getDayOfMonth());
        processPayoutPage.selectProductProvider(this.productProvider);
        processPayoutPage.clickOnSearchPayout();
        Thread.sleep(3000);
        List<LinkedHashMap<String, String>> requiredDataFromProcessPayoutPage = processPayoutPage.getRequiredDataFromProcessPayoutPage(
                clientNameClientCodeProcessPayoutTable, subProductListUploadHistorytable, feeAmountUploadHistorytable);
        Thread.sleep(2000);
        processPayoutPage.goToClientsPage();
        Thread.sleep(4000);
        List<LinkedHashMap<String, String>> clientsMappedWithReferrer = clientPage.checkReferrerMappingWithClients(
                requiredDataFromProcessPayoutPage, this.productProvider);
        Thread.sleep(3000);
        List<LinkedHashMap<String, String>> clientsMappedWithReferrerAndClientRule = clientPage.checkIfClientRuleExistsForClientsMappedWithReferrer(
                clientsMappedWithReferrer, this.productProvider);
        System.out.println(clientsMappedWithReferrerAndClientRule);
        Thread.sleep(3000);
        List<LinkedHashMap<String, String>> dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio = clientPage.getSplitRatioForClients(
                clientsMappedWithReferrerAndClientRule, clientsMappedWithReferrer, this.productProvider);
        Thread.sleep(3000);
        clientsMappedWithReferrer.removeAll(dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio);
        clientPage.goToLicenseeReferrersPage();
        Thread.sleep(4000);
        List<LinkedHashMap<String, String>> dataForClientsMappedWithReferrerWithSplitRatio = licenseeReferrersPage.getSplitRatioForClients(
                clientsMappedWithReferrer, this.productProvider);
        Thread.sleep(3000);
        licenseeReferrersPage.goToProcessPayoutPage();
        Thread.sleep(5000);
        processPayoutPage.selectFromDate(LocalDateTime.now().getDayOfMonth());
        processPayoutPage.selectProductProvider(this.productProvider);
        processPayoutPage.clickOnSearchPayout();
        Thread.sleep(3000);
        List<LinkedHashMap<String, String>> consolidatedDataFromProcessPayoutPage = processPayoutPage.readprocessPayoutDataWithGroupAssociation();
        Thread.sleep(3000);
        Assert.assertTrue(processPayoutPage.matchLicenseeReferrerSplitRatiosFromProcessPayoutPage(dataForClientsMappedWithReferrerWithSplitRatio,
                dataForClientsMappedWithReferrerAndClientRuleWithSplitRatio, consolidatedDataFromProcessPayoutPage),
                "Licensee Referrer Split Ratio Does Not Match With Process Payout Table Data");

        // Clean-up Code
        processPayoutPage.goToUploadHistoryPage();
        Thread.sleep(5000);
        uploadHistoryPage.selectAlreadyUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()), this.productProvider);
        uploadHistoryPage.commissionSheetCleanUp();
    }


    /**
     * Created By Akshay Chawla and Ankur Vaid
     * @throws Exception
     */
    @Test
    public void proportionFeeFlow() throws Exception {
        // Login
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider=uploadHistoryPage.selectProductProvider(Properties.getProductProvider(true, false));
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

        // Close File Successfully Uploded Pop-up
        uploadHistoryPage.dismissFileUploadedDialog();

        // Select Already uploaded Commission Sheet
        uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

        //Navigate To Process Payout Page
        uploadHistoryPage.goToProcessPayoutPage();

        //Filter Product Provider in process payout dropdown
        processPayoutPage.selectProductProvider(this.productProvider);

        //Choose from date in process payout page
        processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

        //Click on Search Payout
        processPayoutPage.clickOnSearchPayout();

        //Store Adviser Group Name and Adviser Rep ID
        HashMap<String, String> adNameRepId = processPayoutPage.getAdviserGroupAndRepId();

        // Read and store proportion fees
        processPayoutPage.goToAdviserGroupPage();
        List<LinkedHashMap<String, String>> proportionalFeeData = adviserGroupsPage.getAllProportionFee(adNameRepId);
        adviserGroupsPage.goToProcessPayoutPage();

        //Filter Product Provider in process payout dropdown
        processPayoutPage.selectProductProvider(this.productProvider);

        //Choose from date in process payout page
        processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

        //Click on Search Payout
        processPayoutPage.clickOnSearchPayout();

        List<LinkedHashMap<String, String>> processPayoutProportionalData = processPayoutPage.getProportionalDataOfAdvisers();

        Assert.assertTrue(processPayoutPage.isProportionalDataAccurate(proportionalFeeData, processPayoutProportionalData),"Data not correct");

        //Clean-up Code
        dashboardPage.goToUploadHistory();
        uploadHistoryPage.selectAlreadyUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()), this.productProvider);
        uploadHistoryPage.commissionSheetCleanUp();
    }

    @Test
    public void test() throws Exception {
        //Login to Paylogic
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider = "AIA";
//        uploadHistoryPage.selectProductProvider();
        uploadHistoryPage.selectUploadHistoryMonth(LocalDate.now().getMonthValue());
        Thread.sleep(1000);
       /* uploadHistoryPage.selectPresentDateToUploadFile(LocalDate.now().getDayOfMonth());
        Thread.sleep(2000);
        uploadHistoryPage.uploadFile(filePath);*/

        //Verify the upload dialog
    /*    Assert.assertTrue(uploadHistoryPage.fileUploadedDialog(), "File Uploaded Dialog is not Displayed");
        String Date=uploadHistoryPage.fileUploadedDate();
        Assert.assertTrue((Date.split(":")[1].split("/")[2] + "-" +  Date.split(":")[1]
                .split("/")[1] + "-" + Date.split(":")[1].split("/")[0]
                .replace(" ","")).equals(LocalDate.now().toString()),
                "File upload Date is not matched");
        Assert.assertEquals(uploadHistoryPage.fileSuccessfullyUploaded(),"File Uploaded Successfully!",
                "File is not Successfully Uploaded");
        LinkedHashMap<String,String> uploadfileData=uploadHistoryPage.uploadedFileDataInUploadDialog();
        for(String Key :uploadfileData.keySet()) {
            System.out.println(Key + " " + uploadfileData.get(Key));
        }*/

        //uploadHistoryPage.fileUploadDialogCloseButton();

        //Verify uploaded transactions data
        uploadedSummaryData = uploadHistoryPage.uploadedFileTableSummararyData();
       /* System.out.println("Entries which are in Process Payout Status : " + uploadedSummaryData.get("Payable"));
        System.out.println("Entries which are in Confirmed Status : " + uploadedSummaryData.get("Paid"));
        System.out.println("Entries which are in UnMapped Status : " + uploadedSummaryData.get("Un Mapped"));
        System.out.println("Entries which are in Bounced Status : " + uploadedSummaryData.get("Not Processed"));*/

     /*   Assert.assertEquals(uploadfileData.get("Total Net :").split("\\$")[1].replace(" ","")
                ,uploadedSummaryData.get("Fee Amount($)"),"Total Net is not matched with expected");
        Assert.assertEquals(uploadfileData.get("Total GST :").split("\\$")[1].replace(" ","")
                ,uploadedSummaryData.get("GST($)"),"Total Gst is not matched with expected");
        Assert.assertEquals(uploadfileData.get("TOTAL INC :").split("\\$")[1].replace(" ","")
                ,uploadedSummaryData.get("Total($)"),"Total Gst is not matched with expected");*/
//        String[] filePathDivision = filePath.toString().split("/");
//        String fileName = filePathDivision[filePathDivision.length - 1];
//        uploadHistoryPage.selectUploadedFile(fileName);
//
//
//        //Read data drom commission sheet
//        final LinkedList<Map<String, String>> excelContant = CommissionFilesReader.readExcel(new File(filePath));
       /* for(int i = 0; i <= excelContant.size()-1; i++){
            System.out.println(excelContant.get(i));
        }*/

        //read data from uploaded table in uploaded history page
//        uploadTableData = uploadHistoryPage.readTableData(uploadedSummaryData);

        Set<String> advisorCodesToBeMapped = new LinkedHashSet<>();
        for (int i = Integer.valueOf(uploadedSummaryData.get("Payable")); i < Integer.valueOf(uploadedSummaryData.get("Total Rows")); i++) {
            advisorCodesToBeMapped.add(uploadTableData.get(i).get("Adviser Code"));
        }
        unMappedAccountsPage.goToUnMappedPage();
        Thread.sleep(2000);
        List<String> mappedAdvisorCode = unMappedAccountsPage.searchAdvisorCodes(unMappedAccountsPage.advisorCodeForMapping(advisorCodesToBeMapped), this.productProvider,"J");
        dashboardPage.goToUploadHistory();
        this.productProvider = "AIA";
//        uploadHistoryPage.selectProductProvider();
        uploadHistoryPage.selectUploadHistoryMonth(LocalDate.now().getMonthValue());
        Thread.sleep(1000);
//        uploadHistoryPage.selectUploadedFile(fileName);

        uploadedSummaryData = uploadHistoryPage.uploadedFileTableSummararyData();
//        uploadTableData = uploadHistoryPage.readTableData(uploadedSummaryData);
        int mappedEntries = 0;
        for (int i = 0; i < Integer.valueOf(uploadedSummaryData.get("Payable")); i++) {
            if (uploadTableData.get(i).get("Adviser Code").equals(mappedAdvisorCode)) {
                mappedEntries = mappedEntries + 1;
            }
        }
        System.out.println("Number of Records added in Process Payout after mapping " + mappedAdvisorCode + " Adviser Code is "
                + mappedEntries);

        for (int i = Integer.valueOf(uploadedSummaryData.get("Payable")); i < Integer.valueOf(uploadedSummaryData.get("Total Rows")); i++) {
            if (uploadTableData.get(i).get("Adviser Code").equals(mappedAdvisorCode)) {
                System.out.println("Adviser Code still exist Mapping Failed");
            }
        }



      /*  for(int i = 0; i <= uploadTableData.size()-1; i++){
            System.out.println(uploadTableData.get(i));
        }*/

        //Map the commission sheet headers to uploaded history table headers
        /*MapperForAsteronProductProvider mapperForAsteronProductProvider = new MapperForAsteronProductProvider();
        LinkedHashMap<String, String> mapper = mapperForAsteronProductProvider.mapperForAsteron(excelContant);*/

        MapperForAIAProductProvider mapperForAIAProductProvider = new MapperForAIAProductProvider();
//        LinkedHashMap<String, String> mapper = mapperForAIAProductProvider.mapperForAIA(excelContant);
        int rowMatchcounter = mapperForAIAProductProvider.mapperForAIA(excelContant, uploadTableData);

        //Verify commission sheet data with upload history table
      /*  int rowMatchcounter= 0;
        for(int j=0;j<excelContant.size()-1;j++)
        {
            Map<String,String> excelTableRows = excelContant.get(j);
            LinkedHashMap<String,String> uploadedTableRows= uploadTableData.get(j);
            if(excelTableRows.get("Rep Name").equals(uploadedTableRows.get(mapper.get("Rep Name")))
                    && excelTableRows.get("Rep No").equals(uploadedTableRows.get(mapper.get("Rep No")))
                    && excelTableRows.get("Life Insured").replace("'","")
                    .equals(uploadedTableRows.get(mapper.get("Life Insured")))
                    && excelTableRows.get("Policy No").equals(uploadedTableRows.get(mapper.get("Policy No")))
                    && excelTableRows.get("Policy Type").equals(uploadedTableRows.get(mapper.get("Policy Type")))
                    && excelTableRows.get("Comm Type").equals(uploadedTableRows.get(mapper.get("Comm Type")))
                    && String.format("%.2f",(Float.valueOf(excelTableRows.get("Comm Amt").replaceAll("^\\d+\\.\\d{1}$+",
                    excelTableRows.get("Comm Amt") + "0")))).equals(uploadedTableRows.get(mapper.get("Comm Amt")))
                    && String.format("%.2f",((Float.valueOf(excelTableRows.get("Comm GST").replaceAll("^\\d+\\.\\d{1}$+",
                    excelTableRows.get("Comm GST") + "0"))) + Float.valueOf(excelTableRows.get("GST Adj"))))
                    .equals(uploadedTableRows.get(mapper.get("Comm GST"))))

                rowMatchcounter++;
        }*/

  /*      int rowMatchcounter = 0;
        for (int j = 0; j < excelContant.size() - 1; j++) {
            Map<String, String> excelTableRows = excelContant.get(j);
            LinkedHashMap<String, String> uploadedTableRows = uploadTableData.get(j);
            if (excelTableRows.get("Advisor Name").replace(":", "").equals(uploadedTableRows.get(mapper.get("Advisor Name")))
                    && excelTableRows.get("Advisor Id").equals(uploadedTableRows.get(mapper.get("Advisor Id")))
                    && excelTableRows.get("Insured Name").replace(",", "").replace("'", "")
                    .equals(uploadedTableRows.get(mapper.get("Insured Name")))
                    && excelTableRows.get("Policy No").equals(uploadedTableRows.get(mapper.get("Policy No")))
                    && excelTableRows.get("Product Name").equals(uploadedTableRows.get(mapper.get("Product Name")))
                    && excelTableRows.get("Type").equals(uploadedTableRows.get(mapper.get("Type")))
                    && String.format("%.2f", (Float.valueOf(excelTableRows.get("Commission").replaceAll("^\\d+\\.\\d{1}$+",
                    excelTableRows.get("Commission") + "0")))).equals(uploadedTableRows.get(mapper.get("Commission")))
                    && String.format("%.2f", (Float.valueOf(excelTableRows.get("Gst").replaceAll("^\\d+\\.\\d{1}$+",
                    excelTableRows.get("Gst") + "0")))).equals(uploadedTableRows.get(mapper.get("Gst"))))

                rowMatchcounter++;
        }*/

//        System.out.println("Excel Size is " + Integer.valueOf(excelContant.size() - 1) + " and Row Matched with Uploaded Data is " + rowMatchcounter);
//        Assert.assertEquals(excelContant.size() - 1, rowMatchcounter, "Full Data of Excel Sheet is not uploaded");
//
//        //Navigate and verify process payout data with upload history transactions data
//        uploadHistoryPage.goToProcessPayoutPage();
//        Thread.sleep(5000);
//
//        processPayoutPage.selectFromDate(uploadHistoryPage.getUploadDateForCommissionStatement().getDayOfMonth());
//        processPayoutPage.clickOnSearchPayout();
//        Thread.sleep(3000);

//       List<LinkedHashMap<String, String>> payoutTableDataAdvisorGroup1 = processPayoutPage.readDataFromFirstAdvisorGroupTable();
//        List<LinkedHashMap<String, String>> payoutTableDataAdvisorGroup2 = processPayoutPage.readDataFromSecondAdvisorGroupTable();
//        List<LinkedHashMap<String, String>> payoutTableData = new LinkedList<LinkedHashMap<String, String>>();
//        payoutTableData.addAll(payoutTableDataAdvisorGroup1);
//        payoutTableData.addAll(payoutTableDataAdvisorGroup2);
//        for(int i = 0; i <= payoutTableData.size()-1; i++){
//            System.out.println(payoutTableData.get(i));
//        }
//        Assert.assertTrue(processPayoutPage.isDataSimilarInGivenTables(uploadTableData, payoutTableData),
//                "Upload table and Payout table do not match");


//        processPayoutPage.goToAdvisersPage();
//        Assert.assertTrue(advisersPage.areAllTransactionsRelevantToAdviserGroup(1,
//                processPayoutPage.listOfAdvisersForAdviserGroupSearch(1, payoutTableDataAdvisorGroup1, uploadTableData)));
//        Assert.assertTrue(advisersPage.areAllTransactionsRelevantToAdviserGroup(2,
//                processPayoutPage.listOfAdvisersForAdviserGroupSearch(2, payoutTableDataAdvisorGroup2, uploadTableData)));
//    }
//    }
    }


    @Test
    public void adviserGroupsMapping() throws InterruptedException {
        loginPage.login(false);
        adviserGroupsPage.goToAdviserGroupsPage();
        adviserGroupsPage.adviserGroupHeaderList();
        adviserGroupsPage.adviserGroupsRow();
        adviserGroupsPage.adviserGroupData();

        List<HashMap<String,String>> spiltRatioFixedOrTiered=adviserGroupsPage.goToActionsAndExtractFixedSpiltRatio("AdviserGroup1","1234");
        Thread.sleep(1000);
        adviserGroupsPage.gotToSpiltFeesTab();
        List<HashMap<String,String>> splitRulesRow= adviserGroupsPage.spiltFeesRulesTables();
        List<HashMap<String,String>> splitFeesRules=adviserGroupsPage.getSplitValuesFromSplitFeesRule(splitRulesRow);
    }

    @Test
    public  void fixedFeesRules() throws InterruptedException {
        loginPage.login(false);
        adviserGroupsPage.goToAdviserGroupsPage();
        adviserGroupsPage.adviserGroupHeaderList();
        adviserGroupsPage.adviserGroupsRow();
        List<HashMap<String,String>> adviserGroupData=adviserGroupsPage.adviserGroupData();
        List<HashMap<String,String>> fixedFeesTableData=fixedFeesPage.goToActionsAndExtractFixedFeesRule("AdviserGroup1","1234",adviserGroupData);
        fixedFeesPage.fixedFeesRuleScheduledDates(fixedFeesTableData);
    }


    @Test
    public void processPayoutCalculation()throws Exception
    {
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(false, true));
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

        // Close File Successfully Uploded Pop-up
        uploadHistoryPage.dismissFileUploadedDialog();

        // Select Already uploaded Commission Sheet
        uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

        //Navigate To Process Payout Page
        uploadHistoryPage.goToProcessPayoutPage();

        //Filter Product Provider in process payout dropdown
        processPayoutPage.selectProductProvider(this.productProvider);

        //Choose from date in process payout page
        processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

        //Click on Search Payout
        processPayoutPage.clickOnSearchPayout();

        List<LinkedHashMap<String, String>> processPayoutTableData = processPayoutPage.readprocessPayoutDataWithGroupAssociation();




        Assert.assertTrue(processPayoutPage.verifyPayoutForLicenseeAdviserGroupAndReferrer(processPayoutTableData),"Table data not correct");

        Assert.assertTrue(processPayoutPage.isTotalPayoutDataCalculationCorrect(processPayoutTableData), "Total data not correct");

    }

    @Test
    public void FixedFeeFlow() throws Exception
    {
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

        //Go to uploadHistory and upload a commission sheet
        dashboardPage.goToUploadHistory();
        this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(false, true));
        uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

        // Close File Successfully Uploded Pop-up
        uploadHistoryPage.dismissFileUploadedDialog();

        // Select Already uploaded Commission Sheet
        uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));


        //Navigate To Process Payout Page
        uploadHistoryPage.goToProcessPayoutPage();

        //Filter Product Provider in process payout dropdown
        processPayoutPage.selectProductProvider(this.productProvider);

        //Choose from date in process payout page
        processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

        //Click on Search Payout
        processPayoutPage.clickOnSearchPayout();

        //Store Adviser Group Name and Adviser Rep ID
        HashMap<String, String> adNameRepId = processPayoutPage.getAdviserGroupAndRepId();

        // Read and store fixed fee
        processPayoutPage.goToAdviserGroupPage();



    }
        @Test()
        public void PayoutReportFlow() throws Exception
        {
            loginPage.login(false);
            Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");

            //Go to uploadHistory and upload a commission sheet
            dashboardPage.goToUploadHistory();
            this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(true, false));
            Thread.sleep(2000);
            uploadHistoryPage.uploadNewFile(Properties.getFilePathForUnMappedAndProcessPayoutFlow());

            // Close File Successfully Uploded Pop-up
            uploadHistoryPage.dismissFileUploadedDialog();

            // Select Already uploaded Commission Sheet
            uploadHistoryPage.selectUploadedFile(uploadHistoryPage.fileName(Properties.getFilePathForUnMappedAndProcessPayoutFlow()));

            //Navigate To Process Payout Page
            uploadHistoryPage.goToProcessPayoutPage();

            //Filter Product Provider in process payout dropdown
            processPayoutPage.selectProductProvider(this.productProvider);

            //Choose from date in process payout page
            processPayoutPage.selectFromDate(LocalDate.now().getDayOfMonth());

            //Click on Search Payout
            processPayoutPage.clickOnSearchPayout();

            Thread.sleep(1000);

            //Extract Process Payout Data
            List<LinkedHashMap<String,String>> payoutData=processPayoutPage.readprocessPayoutDataWithGroupAssociation();

            //Extract Fixed and Proportion Fee Data
            List<LinkedHashMap<String, String>>  fixedProportionFeedata= processPayoutPage.readFixedFeeDataAndPayableAmountCalculationDataWithAdvisorGroupAssociation();

            //Click on Process All Section
            processPayoutPage.clickOnProcessAllSection();

            //Click on confirm
            processPayoutPage.clickOnConfirm();

            //Confirm Process Payout Dialog box
            processPayoutPage.confirmPayoutProcessDialog();

            //Navigate To Payout Report Page
            processPayoutPage.goToPayoutReportPage();

            payoutReportPage.selectDateforPayoutReport();

            //Extract Total Payment Table Data
            List<LinkedHashMap<String, String>> totalPayoutData=payoutReportPage.readTotalPaymentTableDataWithAdvisorGroup();

            Assert.assertTrue(payoutReportPage.isFixedAndProportionFeeDataOnPayoutReportPageCorrect(totalPayoutData, fixedProportionFeedata), "Table data is not correct");



//            payoutReportPage.verifyfixedFeeWithPayoutReportData();



//            processPayoutPage.datepicker();
//
//            dashboardPage.goToUploadHistory();
//            this.productProvider = uploadHistoryPage.selectProductProvider(Properties.getProductProvider(true, false));
//            uploadHistoryPage.box();
//
//            uploadHistoryPage.commissionSheetCleanUp();


















        }




}
