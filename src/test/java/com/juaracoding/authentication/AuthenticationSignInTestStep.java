package com.juaracoding.authentication;

import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.juaracoding.DriverSingleton;
import com.juaracoding.loginPages.SignInPage;

import io.cucumber.java.en.*;

import java.time.Duration;

public class AuthenticationSignInTestStep {
    WebDriver driver;
    SignInPage signInPage;

    @Given("Buka halaman login untuk pengujian login valid")
    public void testStep01() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
    }

    @When("Masukkan username {string} dan password {string} valid")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        ExtentReportUtil.logInfo("Masukkan username");
        ExtentReportUtil.logWithScreenshot("- Masukkan username", driver);
        signInPage.setPassword(password);
        ExtentReportUtil.logInfo("Masukkan password");
        ExtentReportUtil.logWithScreenshot("- Masukkan password", driver);
    }

    @And("Klik tombol login untuk login valid")
    public void testStep03() {
        signInPage.onClick();
    }

    @Then("Pengguna berhasil masuk ke halaman Dashboard")
    public void testStep04() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/dashboards/pending"));

        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + actualUrl);
        Assert.assertEquals(actualUrl, "https://magang.dikahadir.com/dashboards/pending");
        ExtentReportUtil.logInfo("Menampilkan Dashboard");
        ExtentReportUtil.logWithScreenshot("- Menampilkan Dashboard", driver);
    }
}
