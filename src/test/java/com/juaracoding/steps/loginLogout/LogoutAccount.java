package com.juaracoding.steps.loginLogout;

import com.juaracoding.DriverSingleton;
import com.juaracoding.pages.Profilepage;
import com.juaracoding.pages.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LogoutAccount {
    WebDriver driver;
    SignInPage signInPage;
    Profilepage profilepage;

    @Given("Buka halaman login")
    public void testStep01() {
        driver = DriverSingleton.createOrGetDriver();
        driver.get("https://magang.dikahadir.com/authentication/login");
        signInPage = new SignInPage(driver);
        profilepage = new Profilepage(driver);
    }

    @When("Masukkan username {string} dan password {string}  valid")
    public void testStep02(String username, String password) {
        signInPage.setUsername(username);
        signInPage.setPassword(password);
        System.out.println("masukin password");
    }

    @And("Klik tombol login untuk login  valid")
    public void testStep03() {
        System.out.println("ini setelah klik login");
        signInPage.onClick();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @And("Klik user profile pada bagian navbar kanan atas halaman")
    public void testStep04() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + actualUrl);
        Assert.assertEquals(actualUrl, "https://magang.dikahadir.com/dashboards/pending");

        profilepage.navbarButton();
    }

    @Then("Klik Logout di dalam navbar")
    public void testStep05() {

        profilepage.logoutButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/authentication/login"));

        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + actualUrl);
        Assert.assertEquals(actualUrl, "https://magang.dikahadir.com/authentication/login");
    }
}
