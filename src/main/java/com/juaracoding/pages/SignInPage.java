package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "email")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(css = "form button[type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1qamc72']")
    WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-message')]//p[contains(text(), 'Akun tidak ditemukan')]")
    WebElement accNotFound;

    @FindBy(css = "div.MuiAlert-message > p")
    WebElement wrongUnPw;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String value) {
        usernameField.sendKeys(value);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void onClick() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        onClick();
    }

    public String getaccNotfound() {
        wait.until(ExpectedConditions.visibilityOf(accNotFound));
        return accNotFound.getText();
    }

    public String wrongUsernameAndPassword() {
        wait.until(ExpectedConditions.visibilityOf(wrongUnPw));
        return wrongUnPw.getText();
    }
}
