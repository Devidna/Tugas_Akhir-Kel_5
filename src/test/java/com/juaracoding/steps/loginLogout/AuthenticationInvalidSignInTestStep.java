package com.juaracoding.steps.loginLogout;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.juaracoding.DriverSingleton;
import com.juaracoding.pages.SignInPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AuthenticationInvalidSignInTestStep {
    WebDriver driver;
    SignInPage signInPage;

    @Given("Buka halaman login untuk pengujian login invalid")
    public void testStep01() {
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
    }

    @When("Masukkan username {string} dan password {string} tidak valid")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        signInPage.setPassword(password);
    }

    @And("Klik tombol login untuk login tidak valid")
    public void testStep03() {
        signInPage.onClick();
    }

    @Then("Pengguna akan melihat pesan error")
    public void testStep04() {
        String expected = "Akun tidak ditemukan";
        String actual = signInPage.getaccNotfound();

        Assert.assertEquals(actual, expected);
    }
}
