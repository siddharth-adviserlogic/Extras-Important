package com.adviserlogic.automation.common.listeners;

import com.adviserlogic.automation.common.config.BaseRunner;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.util.Arrays;

/**
 * Created by kumar.nipun on 5/18/2018
 */
public class Reporting implements ISuiteListener, ITestListener {

    public static final Logger logger = Logger.getLogger(Reporting.class);

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    @Override
    public void onStart(ISuite iSuite) {
        String message = "Starting execution of suite: " + iSuite.getName();
        logger.debug(message);
        System.out.println(message);
        htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "/target/report.html"));
        htmlReporter.loadXMLConfig(new File(this.getClass().getClassLoader().getResource("extent-config.xml").getPath().replace("%20", " ")));
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setProtocol(Protocol.HTTPS);
        htmlReporter.config().setDocumentTitle("Adviser-Logic Automation Report - " + iSuite.getName());
        htmlReporter.config().setReportName(iSuite.getName());
        htmlReporter.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    }

    @Override
    public void onFinish(ISuite iSuite) {
        String message = "Finishing execution of suite: " + iSuite.getName();
        logger.debug(message);
        System.out.println(message);
        extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String desc = iTestResult.getMethod().getDescription();
        if (desc == null || desc.isEmpty())
            desc = iTestResult.getName();
        String message = "Starting execution of Scenario: " + desc;
        logger.debug(message);
        System.out.println(message);
        extentTest = extentReports.createTest(MarkupHelper.createLabel(iTestResult.getTestContext().getName()
            + " :", ExtentColor.TEAL).getMarkup() + "  " + desc);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String desc = iTestResult.getMethod().getDescription();
        if (desc == null || desc.isEmpty())
            desc = iTestResult.getName();
        String message = "Success of Scenario: " + desc;
        logger.debug(message);
        System.out.println(message);
        extentTest.log(Status.PASS, "Pass");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String desc = iTestResult.getMethod().getDescription();
        if (desc == null || desc.isEmpty())
            desc = iTestResult.getName();
        String message = "Failure of Scenario: " + desc;
        logger.debug(message);
        System.out.println(message);
        extentTest.log(Status.FAIL, "Failed : " + iTestResult.getThrowable());
        try {
            extentTest.addScreenCaptureFromPath(takeScreenShot());
        } catch (Exception e) {
            logger.debug("Error capturing screenshot to extent reports: " + Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String desc = iTestResult.getMethod().getDescription();
        if (desc == null || desc.isEmpty())
            desc = iTestResult.getName();
        String message = "Skipped Scenario: " + desc;
        logger.debug(message);
        System.out.println(message);
        extentTest.log(Status.SKIP, "Skipped!!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        String message = "Starting execution of test context/feature: " + iTestContext.getName();
        logger.debug(message);
        System.out.println(message);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        String message = "Finishing execution of test context/feature: " + iTestContext.getName();
        logger.debug(message);
        System.out.println(message);
    }

    private String takeScreenShot() {
        String path = null;
        try {
            File screen = ((TakesScreenshot) BaseRunner.getDriver()).getScreenshotAs(OutputType.FILE);
            path = System.getProperty("user.dir") + "/target/screens/" + System.currentTimeMillis() + ".png";
            File file = new File(path);
            FileUtils.copyFile(screen, file);
            return file.getCanonicalPath();
        } catch (Exception e) {
            logger.debug("Unable to take screenshot due to: " + e.getMessage());
            return path;
        }
    }
}
