package com.juaracoding.dashboardPages;

import com.juaracoding.utils.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    private WebDriver driver;

    @FindBy(xpath = "//p[normalize-space()='Dashboard Menu']")
    private WebElement menuDashboard;

    @FindBy(xpath = "//p[normalize-space()='Dashboard']")
    private WebElement subMenuDashboard;

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1kei35f']")
    private WebElement titleDashboard;

    @FindBy(xpath = "//h3[normalize-space()]")
    private List<WebElement> labelStats;

    @FindBy(xpath = "//p[normalize-space()]")
    private List<WebElement> valueStats;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void bukaMenuDashboard() {
        utils.waitUntilClickable(driver, subMenuDashboard, 10).click();
    }

    public boolean isDashboardTitleDisplayed() {
        return titleDashboard.isDisplayed();
    }

    public boolean isAllDashboardWidgetsDisplayed() {
        boolean allVisible = true;

        for (WebElement label : labelStats) {
            if (!label.isDisplayed()) {
                System.out.println("Label tidak tampil: " + label.getText());
                allVisible = false;
            }
        }

        for (WebElement value : valueStats) {
            if (!value.isDisplayed()) {
                System.out.println("Value tidak tampil: " + value.getText());
                allVisible = false;
            }
        }

        return allVisible;
    }
}
