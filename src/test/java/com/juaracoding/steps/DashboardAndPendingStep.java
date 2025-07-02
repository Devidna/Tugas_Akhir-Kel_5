package com.juaracoding.steps;

import com.juaracoding.pages.PendingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardAndPendingStep {
    PendingPage pendingPage;

    public DashboardAndPendingStep() {
        pendingPage = new PendingPage();
    }

    @Then("Menu dashboard ditampilkan")
    public void isDashboardPageDisplayed() {
        Assert.assertTrue(pendingPage.isDashboardPageDisplayed(),
                "Menu dashboard tidak ditampilkan");
    }

    @And("Admin berada di halaman pending")
    public void adminBeradaDiHalamanPending() {
        pendingPage.goToPendingPage();
    }

    @When("Admin memilih start date {string} dan {string} end date")
    public void setStartDanEndDate(String startDate, String endDate) {
        pendingPage.selectDate(startDate, endDate);
    }

    @And("Admin klik tombol filter untuk memfilter berdasarkan departemen {string}")
    public void adminKlikTombolFilterUntukMemfilterBerdasarkanDepartemen(String departemen) {
        pendingPage.setFilter(departemen);
    }

    @And("Admin klik tombol pending search")
    public void adminKlikTombolPendingSearch() {
        pendingPage.clickSearchButton();
    }

    @Then("Pending data ditampilkan")
    public void pendingDataDitampilkan() {
        Assert.assertTrue(pendingPage.verifyPendingDataIsDisplayed(),
                "Pending data tidak ditampilkan");
    }


    @Then("Pending data tidak ditampilkan")
    public void pendingDataTidakDitampilkan() {
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
