package com.adviserlogic.automation.ui.components;

import com.adviserlogic.automation.ui.pages.payout_data.ProcessPayoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessPayoutTable extends ProcessPayoutPage {

    public ProcessPayoutTable(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#dvAdvg>div[class = 'panel panel-default panelNew']")
    private List<WebElement> adviserGroupPayoutTables;

    @FindBy(css = "[class *= 'onpaySubHeadder']")
    private List<WebElement> tableRows;

    By adviserGroupTableSubHeadings = By.cssSelector("thead>tr>th");

    /**
     * Extract the Process Payout Columns Data
     * @return
     */
    public List<WebElement> getTableColumns() {
        List<WebElement> tableHeaderColumns = new LinkedList<>();

        return tableHeaderColumns;
    }

    /**
     * Payout Rows Traverse
     * @param tableNumber
     * @return
     */
    public WebElement getAdviserTable(int tableNumber){
        String locatorToTable  = adviserGroupPayoutTables.toString().split("css selector:")[1].replace(
                "]", "") + ":nth-of-type(" + tableNumber + ")";
        return driver.findElement(By.cssSelector(locatorToTable));
    }

    /**
     * Returns the sub-headings for an adviser group table
     * @return
     */
    public List<String> getAdviserTableSubHeadings() {
        List<String> subHeadings = new LinkedList<>();
        List<WebElement> adviserTableSubHeadings = adviserGroupPayoutTables.get(1).findElements(adviserGroupTableSubHeadings);
        subHeadings = adviserTableSubHeadings.stream().map(m -> m.getText().toString()).collect(Collectors.toList());
        return subHeadings;
    }
}