package com.juaracoding.authentication;

import com.juaracoding.DriverSingleton;
import com.juaracoding.dashboardPages.ProfilPage;
import com.juaracoding.loginPages.SignInPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AuthenticationLogoutTestStep {
    WebDriver driver;
    SignInPage signInPage;
    ProfilPage profilepage;

    @Given("Buka halaman login")
    public void testStep01() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
        profilepage = new ProfilPage(driver);
    }

    @When("Masukkan username {string} dan password {string}  valid")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        ExtentReportUtil.logInfo("Masukkan username");
        signInPage.setPassword(password);
        ExtentReportUtil.logInfo("Masukkan password");
    }

    @And("Klik tombol login untuk login  valid")
    public void testStep03() {
        signInPage.onClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ExtentReportUtil.logInfo("Klik tombol login dilakukan");
    }


    @And("Klik user profile pada bagian navbar kanan atas halaman")
    public void testStep04() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actualUrl = driver.getCurrentUrl();
        ExtentReportUtil.logInfo("Current URL: " + actualUrl);
        Assert.assertEquals(actualUrl, "https://magang.dikahadir.com/dashboards/pending");

        profilepage.navbarButton();
    }

    @Then("Klik Logout di dalam navbar")
    public void testStep05() {

        profilepage.logoutButton();
        ExtentReportUtil.logInfo("Klik tombol logout dilakukan");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/authentication/login"));

        String actualUrl = driver.getCurrentUrl();
        ExtentReportUtil.logInfo("Current URL: " + actualUrl);
        Assert.assertEquals(actualUrl, "https://magang.dikahadir.com/authentication/login");
    }
}
