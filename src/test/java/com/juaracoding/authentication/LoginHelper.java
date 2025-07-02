package com.juaracoding.authentication;

import com.juaracoding.loginPages.SignInPage;
import com.juaracoding.utils.utils;
import org.openqa.selenium.WebDriver;

public class LoginHelper {

    WebDriver driver;
    SignInPage signInPage;

    public LoginHelper(WebDriver driver) {
        this.driver = driver;
        this.signInPage = new SignInPage(driver);
    }

    public void performLogin(String username, String password) {
        driver.get("https://magang.dikahadir.com/authentication/login");

        signInPage.setUsername(username);
        signInPage.setPassword(password);
        signInPage.onClick();

        utils.waitForUrlContains(driver, "/dashboards/pending", 10);
    }
}