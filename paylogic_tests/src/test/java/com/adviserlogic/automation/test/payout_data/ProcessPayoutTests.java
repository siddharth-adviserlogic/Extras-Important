package com.adviserlogic.automation.test.payout_data;

import com.adviserlogic.automation.common.config.BaseRunner;
import com.adviserlogic.automation.common.factory.CustomPageFactory;
import com.adviserlogic.automation.ui.pages.DashboardPage;
import com.adviserlogic.automation.ui.pages.LoginPage;
import com.adviserlogic.automation.ui.pages.data_management.UploadHistoryPage;
import com.adviserlogic.automation.ui.pages.payout_data.ProcessPayoutPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

public class ProcessPayoutTests extends BaseRunner {

    private DashboardPage dashboardPage;
    private LoginPage loginPage;
    private UploadHistoryPage uploadHistoryPage;
    private ProcessPayoutPage processPayoutPage;

    @BeforeMethod
    public void setupPages() {
        dashboardPage = CustomPageFactory.getPage(driver, DashboardPage.class);
        loginPage = CustomPageFactory.getPage(driver, LoginPage.class);
        uploadHistoryPage = CustomPageFactory.getPage(driver, UploadHistoryPage.class);
        processPayoutPage = CustomPageFactory.getPage(driver, ProcessPayoutPage.class);
    }

    @Test
    public void test1() {
        System.out.println("Hello!!");
    }

    @Test
    public void test() throws Exception {
        loginPage.login(false);
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase(dashboardPage.dashboardPageUrl), "Home Page is not displayed");
        dashboardPage.goToUploadHistory();
//        uploadHistoryPage.selectProductProvider();
        uploadHistoryPage.selectUploadHistoryMonth(LocalDate.now().getMonthValue());
       // uploadHistoryPage.selectUploadedFile();
        /*List<LinkedHashMap<String, String>> tableContent = uploadHistoryPage.readTableData();
        for (int i = 0; i <= tableContent.size() - 1; i++) {
            System.out.println(tableContent.get(i));
        }*/
//        processPayoutPage.goToProcessPayout();
//        Thread.sleep(5000);
//        driver.findElement(By.cssSelector("div.form-group>#txtFDate")).click();
//        processPayoutPage.clickOnFromDate();
//        processPayoutPage.clickOnSearchPayout();

    }
}
