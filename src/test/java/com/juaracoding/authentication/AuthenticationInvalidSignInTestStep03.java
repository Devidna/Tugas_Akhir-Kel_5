package com.juaracoding.authentication;

import com.juaracoding.DriverSingleton;
import com.juaracoding.loginPages.SignInPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AuthenticationInvalidSignInTestStep03 {
    WebDriver driver;
    SignInPage signInPage;

    @Given("Buka halaman login untuk pengujian login invalid 03")
    public void testStep01() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
    }

    @When("Masukkan username valid {string} dan password salah {string}")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        ExtentReportUtil.logInfo("Masukkan username");
        signInPage.setPassword(password);
        ExtentReportUtil.logInfo("Masukkan password tidak valid");
    }

    @And("Klik tombol login untuk login tidak valid 03")
    public void testStep03() {
        signInPage.onClick();
        ExtentReportUtil.logInfo("Klik tombol login dilakukan");
    }

    @Then("Pengguna akan melihat pesan error 03")
    public void testStep04() {
        String expected = "Email atau password salah";
        String actual = signInPage.wrongUsernameAndPassword();

        ExtentReportUtil.logInfo("Validation Message: " + expected);
        Assert.assertEquals(actual, expected);
    }
}
