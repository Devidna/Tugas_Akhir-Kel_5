package com.juaracoding.steps.loginLogout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.juaracoding.DriverSingleton;
import com.juaracoding.pages.SignInPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;

public class AuthenticationSignInTestStep {
    WebDriver driver;
    SignInPage signInPage;

    @Given("Buka halaman login untuk pengujian login valid")
    public void testStep01() {
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
    }

    @When("Masukkan username {string} dan password {string} valid")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        signInPage.setPassword(password);
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
    }
}
