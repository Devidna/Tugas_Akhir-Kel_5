package com.juaracoding.dashboard;

import com.juaracoding.DriverSingleton;
import com.juaracoding.authentication.LoginHelper;
import com.juaracoding.dashboardPages.PendingPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardAndPending {
    PendingPage pendingPage;

    public DashboardAndPending() {
        pendingPage = new PendingPage();
    }

    @Given("Admin telah login dan buka halaman pending")
    public void adminBeradaDiHalamanPending() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        LoginHelper loginHelper = new LoginHelper(DriverSingleton.createOrGetDriver());
        loginHelper.performLogin("admin@hadir.com", "MagangSQA_JC@123");
        pendingPage.goToPendingPage();
    }

    @Then("Menu dashboard ditampilkan")
    public void isDashboardPageDisplayed() {
        ExtentReportUtil.logInfo("Menu dashboard berhasil ditampilkan");
        Assert.assertTrue(pendingPage.isDashboardPageDisplayed(),
                "Menu dashboard tidak ditampilkan");
    }

    @When("Admin memilih start date {string} dan {string} end date")
    public void setStartDanEndDate(String startDate, String endDate) {
        pendingPage.selectDate(startDate, endDate);
        ExtentReportUtil.logInfo("Memilih tanggal dilakukan");
    }

    @And("Admin klik tombol filter untuk memfilter berdasarkan departemen {string}")
    public void adminKlikTombolFilterUntukMemfilterBerdasarkanDepartemen(String departemen) {
        pendingPage.setFilter(departemen);
        ExtentReportUtil.logInfo("Memilih filter dilakukan");
    }

    @And("Admin klik tombol pending search")
    public void adminKlikTombolPendingSearch() {
        pendingPage.clickSearchButton();
        ExtentReportUtil.logInfo("Klik tombol Search dilakukan");
    }

    @Then("Pending data ditampilkan")
    public void pendingDataDitampilkan() {
        ExtentReportUtil.logInfo("Data pending berhasil ditampilkan");
        Assert.assertTrue(pendingPage.verifyPendingDataIsDisplayed(),
                "Pending data tidak ditampilkan");
    }


    @Then("Pending data tidak ditampilkan")
    public void pendingDataTidakDitampilkan() {
        ExtentReportUtil.logInfo("Data pending tidak berhasil ditampilkan");
        Assert.assertFalse(pendingPage.verifyPendingDataIsDisplayed(),
                "Pending data seharusnya tidak ditampilkan");
    }

    @When("Admin membiarkan start date dan end date kosong")
    public void adminLeavesStartDateAndEndDateEmpty() {
        pendingPage.selectDate("", "");
    }

    @And("Admin membiarkan filter departemen kosong")
    public void adminLeavesDepartmentFilterEmpty() {
        pendingPage.setFilter("");
    }
}

