package com.juaracoding.authentication;

import com.juaracoding.DriverSingleton;
import com.juaracoding.loginPages.SignInPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import com.juaracoding.utils.utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AuthenticationInvalidSignInTestStep02 {
    WebDriver driver;
    SignInPage signInPage;

    @Given("Buka halaman login untuk pengujian login invalid 02")
    public void testStep01() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
    }

    @When("Masukkan username {string} dan password {string} tidak valid 02")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        ExtentReportUtil.logInfo("Masukkan username");
        signInPage.setPassword(password);
        ExtentReportUtil.logInfo("Masukkan password tidak valid");
    }

    @And("Klik tombol login untuk login tidak valid 02")
    public void testStep03() {
        signInPage.onClick();
        utils.delay(5);
        ExtentReportUtil.logInfo("Klik tombol login dilakukan");
    }

    @Then("Pengguna akan melihat pesan error validasi email")
    public void testStep04() {
        // Mengambil pesan validasi dari field email menggunakan JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript(
                "return document.querySelector('input[type=email]').validationMessage;"
        );

        ExtentReportUtil.logInfo("Validation Message: " + validationMessage);

        // Verifikasi pesan mengandung teks error HTML5
        assert validationMessage != null;
        Assert.assertTrue(validationMessage.contains("Please include an '@' in the email address"));
    }
}
