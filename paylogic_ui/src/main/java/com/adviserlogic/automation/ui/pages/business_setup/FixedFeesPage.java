package com.adviserlogic.automation.ui.pages.business_setup;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FixedFeesPage extends BasePage {

    AdviserGroupsPage agp = new AdviserGroupsPage(driver);
    public FixedFeesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="#ulLeftPanel>li:nth-of-type(6)>ul>li:nth-of-type(2)")
    private WebElement clickOnAdviserGroupPage;

    @FindBy(css = ".content-header>#divAccordianHeading>div:nth-of-type(4)")
    private WebElement fixedFeesTab;

    @FindBy(css = "#tblAdviserGroup>thead>tr>th")
    private List<WebElement> adviserGroupHeader;

    @FindBy(css = "#tblAdviserGroup>tbody>tr")
    private List<WebElement> adviserGroups;

    @FindBy(css=".box-body.table-responsive>.table.table-bordered.dataTable>thead>tr>th")
    private List<WebElement> fixedFeeHeader;

    @FindBy(css= ".box-body.table-responsive>.table.table-bordered.dataTable>tbody>tr")
    private List<WebElement> fixedFeesValueRows;

    @FindBy(css = ".wrap_adviser_form>.row>div>div>label")
    private List<WebElement> ruleLabels;

    @FindBy(css=".wrap_adviser_form>.row")
    private List<WebElement> ruleValues;

    @FindBy(css= ".wrap.ng-scope.active")
    private WebElement updateDialog;

    @FindBy(css=".wrap.ng-scope.active>.modal-header>button")
    private WebElement dialogCloseButton;

    @FindBy(css = "#tblScheduleFixedFee>thead>tr>th")
    private List<WebElement> scheduleFeeHeader;

    @FindBy(css= "#tblScheduleFixedFee>tbody>tr")
    private List<WebElement> scheduledFeeRows;

   /* @FindBy(css="#tblScheduleFixedFee")
    private List<WebElement> scheduledData;*/

    @FindBy(css = ".wrap_adviser_form>div div label")
    private List<WebElement> feesLabel;

    @FindBy(css = ".wrap_adviser_form>.row")
    private List<WebElement> feesValueRows;

    public static final String scheduledData = "tblScheduleFixedFee";

public void clickOnAdvGroupPage()
{
    clickOnAdviserGroupPage.click();
}

public void goToFixedFeeTab()
{
    fixedFeesTab.click();
}

public List<HashMap<String,String>> goToActionsAndExtractFixedFeesRule(String adviserGroupName, String authorizedRepId,List<HashMap<String,String>> adviserGroupData) throws InterruptedException {
    List<HashMap<String,String>> fixedFeeRulesData=null;
    for (int i = 0; i < adviserGroupData.size(); i++) {
        fixedFeeRulesData = new LinkedList<>();
        if (adviserGroupData.get(i).get("Adviser Group Name").equals(adviserGroupName) &&
                adviserGroupData.get(i).get("Authorised Rep No").equals(authorizedRepId)) {
            driver.findElement(By.cssSelector("#tblAdviserGroup>tbody>tr:nth-of-type(" + (i + 1) + ")>td:nth-of-type(11)>a")).click();
            Thread.sleep(2000);
            goToFixedFeeTab();
            try{
            if(updateDialog.isDisplayed()){
                Thread.sleep(1000);
                dialogCloseButton.click();}}
            catch (NoSuchElementException e) {
                Thread.sleep(1000); }
            fixedFeeRulesData = fixedFeeDataForAdviser();
            break;
            }
    }
    return fixedFeeRulesData;
}

public void fixedFeesRuleScheduledDates(List<HashMap<String,String>> fixedFeesTableData) throws InterruptedException {
    List<List<HashMap<String,String>>> scheduledDatesData= new LinkedList<>();
    List<HashMap<String,String>> fixedfeeData = new LinkedList<>();
    for(int i=1;i<=fixedFeesTableData.size();i++)
    {
        List<HashMap<String,String>> scheduledDates =null;
        HashMap<String,String> values = null;
        getRowSelector(fixedFeesValueRows,i).click();
        Thread.sleep(2000);
        scheduledDates = scheduledFees();
        values= feeValuesData(feeRuleLabel(),feeRuleValue());
        scheduledDatesData.add(scheduledDates);
        fixedfeeData.add(values);
        goToFixedFeeTab();
    }
}

public Select sel(WebElement element)
{
    Select sel =new Select(element);
    return sel;
}

public HashMap<String,String> feeValuesData(List<String> feelabels,List<String> feeValues)
{
    HashMap<String,String> feeData= new HashMap<>();
    for(int i=0;i<feelabels.size();i++)
    {
        feeData.put(feelabels.get(i),feeValues.get(i));
    }
    return feeData;
}

public List<String> feeRuleLabel()
{
    return feesLabel.stream().map(m->m.getText()).filter(m-> !(m.isEmpty())).collect(Collectors.toList());
}

public List<String> feeRuleValue()
{
    List<String> ruleData = new LinkedList<>();
        for (int i = 1; i <feesValueRows.size(); i++) {
            List<WebElement> row=driver.findElements(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div>div"));
            for(int j=1;j<=row.size();j++)
            {
                try {
                    if(driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>select")).isDisplayed())
                    {
                        ruleData.add(sel(driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>select"))).getFirstSelectedOption().getText());
                    }
                }
              catch (NoSuchElementException e) {
                    try {
                        if (driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>input")).isDisplayed()) {
                            ruleData.add(driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>input")).getAttribute("value"));
                        }
                    }
                    catch (Exception ex)
                    {
                        try {
                            if (driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>textarea")).isDisplayed()) {
                                ruleData.add(driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>textarea")).getText());
                            }
                            else if (driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>div>input")).isSelected()) {
                                ruleData.add("TRUE");
                            }
                        }
                        catch (Exception excep) {
                                if(driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")")).getAttribute("style").contains("display: block")){
                                if (!(driver.findElement(By.cssSelector(".wrap_adviser_form>.row:nth-of-type(" + i + ")>div:nth-of-type(" + j + ")>div>div>input")).isSelected())) {
                                    ruleData.add("FALSE"); }
                                }
                        }
                    }
              }
            }
        }

    return ruleData;
}

public JavascriptExecutor javascriptExecutor()
{
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        return jse;
}

public List<String> scheduledFeeHeader()
{
   List<String> header = scheduleFeeHeader.stream().map(m->m.getText()).collect(Collectors.toList());
   return header;
}

public List<List<String>> scheduledFeeValues()
{
    List<List<String>> feeValues = new LinkedList<>();
    for(int i=0 ;i<scheduledFeeRows.size();i++)
    {
        List<WebElement> rowData= (List<WebElement>) javascriptExecutor().executeScript("return document.getElementById('"+ scheduledData +"').children[1].children[" + i + "].children");
        List<String> rowValues=new LinkedList<>();
        for(int j=0;j<rowData.size();j++)
        {
            rowValues.add((String)javascriptExecutor().executeScript("return document.getElementById('"+ scheduledData +"').children[1].children[" + i + "].children["+ j +"].innerHTML"));
        }
        feeValues.add(rowValues);
    }
    return feeValues;
}

public List<HashMap<String,String>> scheduledFees()
{
    List<String> header = scheduledFeeHeader();
    List<List<String>> values = scheduledFeeValues();
    List<HashMap<String,String>> fees = new LinkedList<>();
    for(int i=0;i<scheduledFeeValues().size();i++)
    {
        HashMap<String,String> feesValues = new HashMap<>();
        for (int j=0;j<header.size();j++)
        {
            feesValues.put(header.get(j),values.get(i).get(j));
        }
        fees.add(feesValues);
    }
    return fees;
}


public List<String> fixedFeesDataHeader()
{
   List<String> fixedFeeHeaderData = fixedFeeHeader.stream().map(m->m.getText()).collect(Collectors.toList());
   return fixedFeeHeaderData;
}


    /**
     * Get fixed fees Data values
     * @return
     */

public List<List<String>> fixedFeesDataValues()
{
    List<List<String>> fixedFeesRowValues = new LinkedList<>();
    for(int i=1; i<=fixedFeesValueRows.size();i++)
    {
        List<String> rowData=getRowCellValue(getRowSelector(fixedFeesValueRows,i)).stream().map(m->m.getText()).collect(Collectors.toList());
        fixedFeesRowValues.add(rowData);
    }

    return fixedFeesRowValues;
}

    /**
     * Get Row from any table
     * @param webElementList
     * @param rowNumber
     * @return
     */
    public WebElement getRowSelector(List<WebElement> webElementList,int rowNumber)
{
    String rowLocator = webElementList.get(rowNumber-1).toString().split("css selector:")[1]
            .replace("]","") + ":nth-of-type(" + rowNumber + ")";

    return driver.findElement(By.cssSelector(rowLocator));
}

    /**
     * Get Cell Values from any table
     * @param getRowsSelector
     * @return
     */
    public List<WebElement> getRowCellValue(WebElement getRowsSelector)
{
    String cellLocator=getRowsSelector.toString().split("css selector:")[1].replace("]","") + ">td";
    return  driver.findElements(By.cssSelector(cellLocator));
}

    /**
     *  Get the fixed fees table data from fixed fee page
     * @return
     */
    public List<HashMap<String,String>> fixedFeeDataForAdviser()
{
    List<HashMap<String,String>> fixedFeeTableData = new LinkedList<>();
    HashMap<String,String> values = null;
    for(int i =0;i<fixedFeesDataValues().size();i++)
    { values = new HashMap<>();
     for (int j=0;j<fixedFeesDataHeader().size();j++)
     { values.put(fixedFeesDataHeader().get(j),fixedFeesDataValues().get(i).get(j)); }
     fixedFeeTableData.add(values);
    }
    return fixedFeeTableData;
}


}



