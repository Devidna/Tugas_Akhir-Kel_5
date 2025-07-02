package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    FluentWait <WebDriver> fluentWait;

    @FindBy(css = "div[id='nprogress']")
    WebElement topProgressBar;

    public void waitForTopProgressBarToDisappear() {
        // Wait for bar to disappear
        wait.until(ExpectedConditions.invisibilityOf(topProgressBar));
    }
}
