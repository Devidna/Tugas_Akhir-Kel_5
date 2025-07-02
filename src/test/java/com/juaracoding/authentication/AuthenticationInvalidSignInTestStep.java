package com.juaracoding.authentication;

import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.juaracoding.DriverSingleton;
import com.juaracoding.loginPages.SignInPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AuthenticationInvalidSignInTestStep {
    WebDriver driver;
    SignInPage signInPage;

    @Given("Buka halaman login untuk pengujian login invalid")
    public void testStep01() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
    }

    @When("Masukkan username {string} dan password {string} tidak valid")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        ExtentReportUtil.logInfo("Masukkan username");
        signInPage.setPassword(password);
        ExtentReportUtil.logInfo("Masukkan password");
    }

    @And("Klik tombol login untuk login tidak valid")
    public void testStep03() {
        signInPage.onClick();
    }

    @Then("Pengguna akan melihat pesan error")
    public void testStep04() {
        String expected = "Akun tidak ditemukan";
        String actual = signInPage.getaccNotfound();

        ExtentReportUtil.logInfo("Akun tidak ditemukan");
        Assert.assertEquals(actual, expected);
    }
}
