package com.adviserlogic.automation.ui.pages.business_setup;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AdvisersPage extends BasePage {

    private static By searchbox = By.cssSelector("#tblAdvisers_filter>label>input");
    private static By tableColumns = By.cssSelector("#tblAdvisers_wrapper #tblAdvisers>tbody>tr>td");

    public AdvisersPage(WebDriver driver) {
        super(driver);
    }

    private String adviserGroupForGivenAdviser(String adviserName, int adviserGroup) throws InterruptedException {
        Thread.sleep(5000);
        String advisorGroup = "";
        driver.findElement(searchbox).click();
        driver.findElement(searchbox).clear();
        driver.findElement(searchbox).sendKeys(adviserName);
        if (adviserName.equals(driver.findElements(tableColumns).get(0).getText())) {
            if (adviserGroup == 1) {
                advisorGroup = driver.findElements(tableColumns).get(2).getText();
            } else if (adviserGroup == 2) {
                if (driver.findElements(tableColumns).size() > 7) {
                    advisorGroup = driver.findElements(tableColumns).get(9).getText();
                } else {
                    advisorGroup = driver.findElements(tableColumns).get(2).getText();
                }
            }
        }
        return advisorGroup;
    }

    public boolean areAllTransactionsRelevantToAdviserGroup(int adviserGroup, List<String> adviserList) throws InterruptedException {
        boolean flag = false;
        if(adviserGroup == 1){

            for(int i = 0; i < adviserList.size(); i++){
                if(this.adviserGroupForGivenAdviser(adviserList.get(i), 1).contains("AdviserGroup1")){
                    flag = true;
                }
                else{
                    System.out.println(adviserList.get(i) + "Is not mapped with AdviserGroup" + adviserGroup);
                }
            }
        }
        else if(adviserGroup == 2){
            for(int i = 0; i < adviserList.size(); i++){
                if(this.adviserGroupForGivenAdviser(adviserList.get(i), 2).contains("AdviserGroup2")){
                    flag = true;
                }
                else{
                    System.out.println(adviserList.get(i) + "Is not mapped with AdviserGroup" + adviserGroup);
                }
            }
        }

        return flag;
    }
}
