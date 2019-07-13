package com.adviserlogic.automation.test;

import com.adviserlogic.automation.common.config.BaseRunner;
import com.adviserlogic.automation.common.factory.CustomPageFactory;
import com.adviserlogic.automation.ui.pages.DashboardPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestRunner extends BaseRunner {

    private DashboardPage homePage;

    @BeforeMethod
    public void setupPages() {
        homePage = CustomPageFactory.getPage(driver, DashboardPage.class);
    }

    @Test
    public void test1() {
        System.out.println("Hello!!");
    }
}
