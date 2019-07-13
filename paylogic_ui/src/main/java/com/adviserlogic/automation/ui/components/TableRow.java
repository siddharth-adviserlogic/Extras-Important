package com.adviserlogic.automation.ui.components;

import com.adviserlogic.automation.common.pages.PageComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class TableRow extends PageComponent {

    @FindBy(tagName = "td")
    private List<WebElement> columns;

    public TableRow(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    public List<String> getRowData() {
        return columns.stream().map(m -> m.getText()).collect(Collectors.toList());
    }
}
