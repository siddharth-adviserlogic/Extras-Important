package com.adviserlogic.automation.ui.components;

import com.adviserlogic.automation.common.pages.PageComponent;
import com.adviserlogic.automation.ui.pages.data_management.UploadHistoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;


public class UploadedFileTable extends PageComponent {

    static int processPayoutCount;
    static int unMappedCount;
    static int bouncedCount;
    static int duplicateCount;
    static int notProcessed;


    public UploadedFileTable(WebDriver driver) {
        super(driver);
    }

    /**
     * Get Upload Table Row Heading
     * @return
     */

    public List<String> getTableRowsHeading() {
        List<WebElement> tableRowsHeading = driver.findElements(By.cssSelector(
                ".table.table-bordered.table-striped.dataTable.table1.table-condensed.onpaytable>thead>tr>th"));

        List<String> headings = tableRowsHeading.stream().map(m -> m.getText().toString()).collect(Collectors.toList());
        return headings;
    }

    /**
     * Get Number of Upload table row
     * @return
     */
    public List<WebElement> getTableRows() {
        List<WebElement> tableRows = driver.findElements(By.cssSelector("#tblHistory>#lstUploadDataFile>tr"));
        return tableRows;
    }

    /**
     * Row Traverse locator
     * @param row
     * @return
     */
    public String getTableRowLocator(int row) {
        String oldRowLocator = "#tblHistory>#lstUploadDataFile>tr";
        return oldRowLocator + ":nth-of-type(" + row + ")";
    }


    /**
     * Extract upload table data
     * @param uploadedSummaryData
     * @return
     * @throws InterruptedException
     */
    public List<List<String>> getTableRowData(LinkedHashMap<String,String> uploadedSummaryData) throws InterruptedException {
        List<List<String>> tableRowData = new LinkedList<List<String>>();
        List<String> rowValues = null;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(5000);
        List<WebElement> tablerows = (List<WebElement>) js.executeScript("return document.getElementById('" + UploadHistoryPage.tableId + "').children;");

        UploadedFileTable.processPayoutCount = Integer.valueOf(uploadedSummaryData.get("Payable"));
        UploadedFileTable.unMappedCount = Integer.valueOf(uploadedSummaryData.get("Un Mapped"));
         if(Integer.valueOf(uploadedSummaryData.get("Payable")) >0 && Integer.valueOf(uploadedSummaryData.get("Un Mapped")) >0) {
            List<WebElement> rowStatusBar = UploadHistoryPage.tableRowStatus();
            System.out.println("Record Status: " + rowStatusBar.get(1).getText() + " Number of Records for Specified Status: " + rowStatusBar.get(2).getText());
            int NoOfRecords = Integer.valueOf(rowStatusBar.get(2).getText());

            for (int i = 1; i <= NoOfRecords; i++) {
                List<WebElement> row = (List<WebElement>) js.executeScript("return document.getElementById('" + UploadHistoryPage.tableId + "').children[" + i + "].children;");
                rowValues = new LinkedList<>();
                for (int j = 2; j < row.size(); j++) {
                    rowValues.add(js.executeScript("return document.getElementById('" + UploadHistoryPage.tableId + "').children[" + i + "].children[" + j + "].innerHTML;").toString());
                }
                tableRowData.add(rowValues);
            }
            js.executeScript("document.getElementById('gvFee2_DXGroupRowExp0').children[0].click();");
            js.executeScript("document.getElementById('gvFee2_DXGroupRowExp1').children[0].click();");

            for(int i= NoOfRecords+2;i<tablerows.size();i++)
            {
                List<WebElement> row = (List<WebElement>) js.executeScript("return document.getElementById('" + UploadHistoryPage.tableId + "').children[" + i + "].children;");
                rowValues = new LinkedList<>();
                for (int j = 2; j < row.size(); j++) {
                    rowValues.add(js.executeScript("return document.getElementById('" + UploadHistoryPage.tableId + "').children[" + i + "].children[" + j + "].innerHTML;").toString());
                }
                tableRowData.add(rowValues);
            }
            js.executeScript("document.getElementById('gvFee2_DXGroupRowExp1').children[0].click();");
        }

        else if(Integer.valueOf(uploadedSummaryData.get("Payable")) >0) {
            List<WebElement> rowStatusBar = UploadHistoryPage.tableRowStatus();
            System.out.println("Record Status: " + rowStatusBar.get(1).getText() + " Number of Records for Specified Status: " + rowStatusBar.get(2).getText());
            int NoOfRecords = Integer.valueOf(rowStatusBar.get(2).getText());

            for (int i = 1; i <= NoOfRecords; i++) {
                List<WebElement> row = (List<WebElement>) js.executeScript("return document.getElementById('" + UploadHistoryPage.tableId + "').children[" + i + "].children;");
                rowValues = new LinkedList<>();
                for (int j = 2; j < row.size(); j++) {
                    rowValues.add(js.executeScript("return document.getElementById('" + UploadHistoryPage.tableId + "').children[" + i + "].children[" + j + "].innerHTML;").toString());
                }
                tableRowData.add(rowValues);
            }
            js.executeScript("document.getElementById('gvFee2_DXGroupRowExp0').children[0].click();");
        }
        return tableRowData;
    }

    /**
     * Get Upload Table Data
     * @param uploadedSummaryData
     * @return
     * @throws InterruptedException
     */
    public List<LinkedHashMap<String, String>> getTableData(LinkedHashMap<String,String> uploadedSummaryData) throws InterruptedException {
        List<String> tableRowSubHeading = getTableRowsHeading();
        List<List<String>> tableRowData = getTableRowData(uploadedSummaryData);
        List<LinkedHashMap<String, String>> tableData = new LinkedList<LinkedHashMap<String, String>>();
        LinkedHashMap<String, String> valueMapping;

        for (int i = 0; i < tableRowData.size(); i++) {
            valueMapping = new LinkedHashMap<String, String>();
            for (int j = 2; j < tableRowSubHeading.size(); j++) {
                valueMapping.put(tableRowSubHeading.get(j), tableRowData.get(i).get(j-2));
            }
            tableData.add(valueMapping);
        }
        return tableData;
    }
}
