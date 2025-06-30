package com.juaracoding.authentication;

import com.juaracoding.DriverSingleton;
import com.juaracoding.loginPages.SignInPage;
import com.juaracoding.utils.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseLoginTest {
    WebDriver driver;
    SignInPage signInPage;

    public void baseLogin() {
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");

        signInPage = new SignInPage(driver);

        signInPage.login("admin@hadir.com", "MagangSQA_JC@123");
    }
}
