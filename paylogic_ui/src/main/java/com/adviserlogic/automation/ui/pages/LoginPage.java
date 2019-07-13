package com.adviserlogic.automation.ui.pages;

import com.adviserlogic.automation.common.config.Properties;
import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private static final String relative = "/Login.aspx";

    @FindBy(css = "#txtUserName")
    private WebElement usernameTextbox;

    @FindBy(css = "#txtPassword")
    private WebElement passwordtextbox;

    @FindBy(css = "#btnSubmit")
    private WebElement submitButton;

    /**
     * Checks is login page is displayed
     * @return
     */
    public boolean isDisplayed(){
        boolean isDisplayed = false;
        if(usernameTextbox.isDisplayed() && passwordtextbox.isDisplayed() && submitButton.isDisplayed()){
            isDisplayed = true;
        }
        return isDisplayed;
    }

    /**
     *
     * @param driver
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigates to the login page
     */
    public void goToLoginPage() {
        driver.get(Properties.getBaseUrl() + relative);
        driver.manage().window().maximize();
    }

    /**
     *
     * login to application
     */
    public void login( boolean superAdmin) {
        if(superAdmin){
            usernameTextbox.clear();
            usernameTextbox.sendKeys(Properties.getSuperAdminUsername());
            passwordtextbox.clear();
            passwordtextbox.sendKeys(Properties.getSuperAdminPassword());
            submitButton.click();
        }
        else{
            goToLoginPage();
            usernameTextbox.sendKeys(Properties.getUsername());
            passwordtextbox.sendKeys(Properties.getPassword());
            submitButton.click();
        }
    }
}
