package com.juaracoding.dashboard;

import com.juaracoding.authentication.LoginHelper;
import com.juaracoding.dashboardPages.DashboardPage;
import com.juaracoding.DriverSingleton;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import com.juaracoding.utils.utils;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardTest {
    WebDriver driver;
    DashboardPage DP;

    @Given("Admin login ke halaman dashboard Hadir")
    public void loginKeDashboard() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        DP = new DashboardPage(driver);

        LoginHelper loginHelper = new LoginHelper(driver);
        loginHelper.performLogin("admin@hadir.com", "MagangSQA_JC@123");

        utils.delay(3);
        DP.bukaMenuDashboard();
        utils.waitForUrlContains(driver, "/dashboards/dashboard", 10);

        ExtentReportUtil.logInfo("Admin berhasil login dan membuka dashboard");
    }

    @When("Dashboard selesai dimuat")
    public void dashboardDimuat() {
        boolean isDisplayed = DP.isDashboardTitleDisplayed();

        if (isDisplayed) {
            ExtentReportUtil.logPass("Judul dashboard tampil dengan benar");
        } else {
            ExtentReportUtil.logFailWithScreenshot("Judul dashboard tidak tampil", driver);
            Assert.fail("Judul dashboard tidak tampil");
        }
    }

    @Then("Semua elemen utama dashboard tampil dengan benar")
    public void semuaWidgetTampil() {
        boolean allDisplayed = DP.isAllDashboardWidgetsDisplayed();

        if (allDisplayed) {
            ExtentReportUtil.logPass("Semua elemen utama dashboard tampil dengan benar");
        } else {
            ExtentReportUtil.logFailWithScreenshot("Beberapa elemen dashboard tidak tampil", driver);
            Assert.fail("Beberapa elemen dashboard tidak tampil");
        }
    }
}
