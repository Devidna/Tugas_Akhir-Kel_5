package com.juaracoding.dashboard;

import com.juaracoding.authentication.BaseLoginTest;
import com.juaracoding.dashboardPages.DashboardPage;
import com.juaracoding.DriverSingleton;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardTest  extends BaseLoginTest {
    WebDriver driver;
    DashboardPage DP;

    @Given("Admin login ke halaman dashboard Hadir")
    public void loginKeDashboard() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        DP = new DashboardPage(driver);

        baseLogin();
        DP.bukaMenuDashboard();

        ExtentReportUtil.logInfo("Admin berhasil login dan membuka dashboard");
        ExtentReportUtil.logWithScreenshot("- Halaman Dashboard", driver);
    }

    @When("Dashboard selesai dimuat")
    public void dashboardDimuat() {
        boolean isDisplayed = DP.isDashboardTitleDisplayed();
        Assert.assertTrue(isDisplayed, "Judul Dashboard tidak tampil");

        ExtentReportUtil.logInfo("Judul dashboard tampil dengan benar");
        ExtentReportUtil.logWithScreenshot("- Judul Dashboard", driver);
    }

    @Then("Semua elemen utama dashboard tampil dengan benar")
    public void semuaWidgetTampil() {
        boolean allDisplayed = DP.isAllDashboardWidgetsDisplayed();
        Assert.assertTrue(allDisplayed, "Beberapa elemen dashboard tidak tampil");

        ExtentReportUtil.logPass("Semua elemen utama dashboard tampil dengan benar");
        ExtentReportUtil.logWithScreenshot("- elemen utama Dashboard", driver);
    }
}
