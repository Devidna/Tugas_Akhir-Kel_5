package com.juaracoding.helper;

import com.juaracoding.pages.SignInPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginHelper {

    private WebDriver driver;
    private SignInPage signInPage;

    public LoginHelper(WebDriver driver) {
        this.driver = driver;
        this.signInPage = new SignInPage(driver);
    }

    public void performLogin(String username, String password) {
        driver.get("https://magang.dikahadir.com/authentication/login");

        signInPage.setUsername(username);
        signInPage.setPassword(password);
        signInPage.onClick();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/dashboards/pending"));
    }
}