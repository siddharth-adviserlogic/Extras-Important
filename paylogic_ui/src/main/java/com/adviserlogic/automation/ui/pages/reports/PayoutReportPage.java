package com.adviserlogic.automation.ui.pages.reports;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDate;
import java.util.*;
import org.openqa.selenium.By;
import java.util.stream.Collectors;

public class PayoutReportPage extends BasePage {
    public PayoutReportPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.box.plFloatL a[data-toggle='collapse']")
    private List<WebElement> adviserGroupsExpandLinks;

    @FindBy(css = "div.panel-collapse.collapse.in table#tblFixedFees tr td:nth-of-type(1)")
    private List<WebElement> totalPaymentDescriptions;

    @FindBy(css = "div.box.plFloatL tr[class='parent'] td >span")
    private List<WebElement> productProviderExpandLink;

    @FindBy(css = "div.box.plFloatL tr[class*='child chiledxvdatarow'] td:nth-of-type(4)")
    private List<WebElement> totalPaymentClientNames;

    @FindBy(css = "div.datepicker_custom>#tblCalender>tbody>tr>td[class='day']")
    private List<WebElement> calDates;

    @FindBy(css = " div#divCommitPayouts a[data-toggle='collapse']")
    private List<WebElement> adviserGroupPanels;

    @FindBy(css = "div.panel-collapse.collapse.in table[id='tblFixedFees'] >thead>tr> th:nth-of-type(1)")
    private WebElement totalPaymentsTableDescriptionHead;

    @FindBy(css = "div.panel-collapse.collapse.in table[id='tblFixedFees'] >thead>tr> th:nth-of-type(2)")
    private WebElement totalPaymentsTableFrequencyHead;

    @FindBy(css = "div.panel-collapse.collapse.in table[id='tblFixedFees'] >thead>tr> th:nth-of-type(3)")
    private WebElement totalPaymentsTableDateHead;

    @FindBy(css = "div.panel-collapse.collapse.in table[id='tblFixedFees'] >thead>tr> th:nth-of-type(4)")
    private WebElement totalPaymentsTableAmountHead;

    @FindBy(css = "div.panel-collapse.collapse.in table[id='tblFixedFees'] >thead>tr> th:nth-of-type(5)")
    private WebElement totalPaymentsTableGSTHead;

    @FindBy(css = "div.panel-collapse.collapse.in table[id='tblFixedFees'] >thead>tr> th:nth-of-type(6)")
    private WebElement totalPaymentsTableTotalHead;

    @FindBy(css = " div.panel-collapse.collapse.in table[id='tblFixedFees'] >tbody>tr> td:nth-of-type(1)")
    private List<WebElement> totalPaymentsTableDescription;

    @FindBy(css = " div.panel-collapse.collapse.in table[id='tblFixedFees'] >tbody>tr> td:nth-of-type(2)")
    private List<WebElement> totalPaymentsTableFrequency;

    @FindBy(css = " div.panel-collapse.collapse.in table[id='tblFixedFees'] >tbody>tr> td:nth-of-type(3)")
    private List<WebElement> totalPaymentsTableDate;

    @FindBy(css = " div.panel-collapse.collapse.in table[id='tblFixedFees'] >tbody>tr> td:nth-of-type(4)")
    private List<WebElement> totalPaymentsTableAmount;

    @FindBy(css = " div.panel-collapse.collapse.in table[id='tblFixedFees'] >tbody>tr> td:nth-of-type(5)")
    private List<WebElement> totalPaymentsTableGST;

    @FindBy(css = " div.panel-collapse.collapse.in table[id='tblFixedFees'] >tbody>tr> td:nth-of-type(6)")
    private List<WebElement> totalPaymentsTableTotal;



    By payoutReportRowsIdentifier = By.cssSelector("div.box.plFloatL tr[class*='child chiledxvdatarow']");

    By payoutReportClientNameRowsValueIdentifier = By.cssSelector("table#tblFixedFees tr td:nth-of-type(1");


    public boolean verifyfixedFeeWithPayoutReportData() throws InterruptedException
    {
        List<List<String>> values = new LinkedList<>();
        List<String> columnValues = null;
        boolean isSame=false;
        for (int i = 0; i < adviserGroupsExpandLinks.size(); i++)
        {
            productProviderExpandLink.get(i).click();
            for(int j=0;j<totalPaymentClientNames.size();j++)
            {
                columnValues = adviserGroupsExpandLinks.get(i).findElements(payoutReportRowsIdentifier).
                        get(j).findElements(payoutReportClientNameRowsValueIdentifier).stream().map(WebElement::getText).
                        collect(Collectors.toList());
                System.out.println(columnValues);
            }
        }
        return isSame;
    }


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

    public void selectDateforPayoutReport () throws InterruptedException
    {
        Thread.sleep(2000);
        selectPresentDateToUploadFile(LocalDate.now().getDayOfMonth());
    }


    public List<LinkedHashMap<String, String>> readTotalPaymentTableDataWithAdvisorGroup() throws InterruptedException
    {
        List<LinkedHashMap<String, String>> totalPaymentsData=new LinkedList<>();
        for(int i=0;i<adviserGroupPanels.size();i++) {
//            LinkedHashMap<String, String> adviserGroupDetails = new LinkedHashMap<>();
            String[] panelParts = adviserNameAndRedIdFromPanelHeading(adviserGroupPanels.get(i).getText());
            String adviserName = panelParts[0];
            String adviserRepId = panelParts[1];
//            adviserGroupDetails.put("Adviser Name", adviserName);
//            adviserGroupDetails.put("Adviser RepId", adviserRepId);
            Thread.sleep(2000);
            adviserGroupPanels.get(i).click();
            for (int j = 0; j < totalPaymentsTableDate.size(); j++)
            {
                LinkedHashMap<String, String> totalPaymentDataCollect = new LinkedHashMap<>();
                totalPaymentDataCollect.put(totalPaymentsTableDescriptionHead.getText(), totalPaymentsTableDescription.get(j+1).getText());
                totalPaymentDataCollect.put(totalPaymentsTableFrequencyHead.getText(), totalPaymentsTableFrequency.get(j+1).getText());
                Thread.sleep(3000);
                totalPaymentDataCollect.put(totalPaymentsTableDateHead.getText(), totalPaymentsTableDate.get(j).getText());
                totalPaymentDataCollect.put(totalPaymentsTableAmountHead.getText(), totalPaymentsTableAmount.get(j).getText());
                totalPaymentDataCollect.put(totalPaymentsTableGSTHead.getText(), totalPaymentsTableGST.get(j).getText());
                totalPaymentDataCollect.put(totalPaymentsTableTotalHead.getText(), totalPaymentsTableTotal.get(j).getText());
                totalPaymentDataCollect.put("Adviser Name", adviserName);
                totalPaymentDataCollect.put("Adviser RepId", adviserRepId);
                totalPaymentsData.add(totalPaymentDataCollect);
            }
//            totalPaymentsData.add(adviserGroupDetails);
            Thread.sleep(2000);
            adviserGroupPanels.get(i).click();
        }
        return totalPaymentsData;
    }

    public boolean isFixedAndProportionFeeDataOnPayoutReportPageCorrect(List<LinkedHashMap<String, String>> totalPayoutData, List<LinkedHashMap<String, String>> fixedProportionFeedata) throws InterruptedException
    {
        boolean isFixedAndProportionFeeDataCorrect = false;


            for (int i = 0; i <= totalPayoutData.size() - 1; i++) {
                if ((totalPayoutData.get(i).get("Description").contains("Proportion Fee")) || (totalPayoutData.get(i).get("Description").contains("Fixed Fee"))) {
                    String description;
                    for (int j = 0; j <= fixedProportionFeedata.size() - 1; j++) {
                        if (totalPayoutData.get(i).get("Description").contains("Fee/Payment")) {
                            description = fixedFeeDescriptionFromTotalPaymentTable(totalPayoutData.get(i).get("Description"));
                        } else {
                            description = proportionFeeDescriptionFromTotalPaymentTable(totalPayoutData.get(i).get("Description"));
                        }
//                        try {
                            if ((description.equals(fixedProportionFeedata.get(j).get("Name")))
                                    && ((totalPayoutData.get(i).get("Frequency")).equals(fixedProportionFeedata.get(j).get("FixedFeeType")))
                                    && ((totalPayoutData.get(i).get("Date")).equals(fixedProportionFeedata.get(j).get("Due Date")))
                                    && ((Math.abs(Double.parseDouble(totalPayoutData.get(i).get("Amount($)")))) == (Math.abs(Double.parseDouble(fixedProportionFeedata.get(j).get("Amount($)")))))
                                    && ((Math.abs(Double.parseDouble(totalPayoutData.get(i).get("GST($)")))) == (Math.abs(Double.parseDouble(fixedProportionFeedata.get(j).get("GST($)")))))
                                    && (((Math.abs(Double.parseDouble(totalPayoutData.get(i).get("Total($)")))) == (Math.abs(Double.parseDouble(fixedProportionFeedata.get(j).get("Total($)")))))
                                    || ((((Math.abs(Double.parseDouble(totalPayoutData.get(i).get("Total($)")))) - (Math.abs(Double.parseDouble(fixedProportionFeedata.get(j).get("Total($)"))))) <= 0.02)))) {
                                isFixedAndProportionFeeDataCorrect = true;
                                break;
//                            } else {
//                                throw new Exception("Fixed and Proportion Fee Data is not correct on payout report page");
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
                        }
                    }
                }
            }

        return isFixedAndProportionFeeDataCorrect;
    }

    public String fixedFeeDescriptionFromTotalPaymentTable(String description) {
        String[] descriptionParts = description.split("[/,:]");
        String payoutTableDescription;
        payoutTableDescription = descriptionParts[0] + " "+":" + " " + descriptionParts[2];
        return payoutTableDescription;
    }

    public String proportionFeeDescriptionFromTotalPaymentTable(String description)
    {
        String[] descriptionParts = description.split("[:]");
        String payoutTableDescription;
        payoutTableDescription = descriptionParts[0] + ":" + " " + descriptionParts[1];
        return payoutTableDescription;
    }


    public String[] adviserNameAndRedIdFromPanelHeading(String panelHeading) {
        panelHeading = panelHeading.replace("Advisergroup : : ", "");
        panelHeading = panelHeading.replace("(", " ");
        panelHeading = panelHeading.replace(")", "");
        String[] panelParts = panelHeading.split(" ");
        return panelParts;
    }

    public boolean verifyFixedFeeDataWithPayoutReportPageData(List<LinkedHashMap<String, String>> data) throws InterruptedException
    {
        boolean isFixedFeeDataCorrect=false;

        for(int i=0;i<data.size();i++)
        {

        }
        return isFixedFeeDataCorrect;
    }

}
