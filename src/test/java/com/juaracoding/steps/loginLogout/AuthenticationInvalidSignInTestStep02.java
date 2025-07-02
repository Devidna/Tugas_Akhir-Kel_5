package com.juaracoding.steps.loginLogout;

import com.juaracoding.DriverSingleton;
import com.juaracoding.pages.SignInPage;
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
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
    }

    @When("Masukkan username {string} dan password {string} tidak valid 02")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        signInPage.setPassword(password);
    }

    @And("Klik tombol login untuk login tidak valid 02")
    public void testStep03() {
        signInPage.onClick();
    }

    @Then("Pengguna akan melihat pesan error validasi email")
    public void testStep04() {
        // Mengambil pesan validasi dari field email menggunakan JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript(
                "return document.querySelector('input[type=email]').validationMessage;"
        );

        System.out.println("Validation Message: " + validationMessage);

        // Verifikasi pesan mengandung teks error HTML5
        Assert.assertTrue(validationMessage.contains("Please include an '@' in the email address"));
    }
}
