package com.juaracoding.laporan.laporanCuti;

import com.juaracoding.DriverSingleton;
import com.juaracoding.authentication.LoginHelper;
import com.juaracoding.laporanPages.LaporanCutiPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LaporanCutiTest {
    LaporanCutiPage laporanCutiPage;

    public LaporanCutiTest() {
        laporanCutiPage = new LaporanCutiPage();
    }

    @Given("Admin telah login dan buka halaman laporan cuti")
    public void adminBeradaDiHalamanLaporanCuti() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        LoginHelper loginHelper = new LoginHelper(DriverSingleton.createOrGetDriver());
        loginHelper.performLogin("admin@hadir.com", "MagangSQA_JC@123");
        laporanCutiPage.goToLaporanCutiPage();
    }

    @When("Admin memasukkan nama {string} yang sesuai")
    public void adminMemasukkanNamaYangSesuai(String nama) {
        laporanCutiPage.inputNama(nama);
        ExtentReportUtil.logInfo("Memasukkan nama dilakukan");
    }

    @And("Admin memilih tanggal start date {string} dan end date {string}")
    public void adminMemilihTanggalStartDateDanEndDate(String startDate, String endDate) {
        laporanCutiPage.selectDate(startDate, endDate);
        ExtentReportUtil.logInfo("Memilih tanggal dilakukan");
    }

    @And("Admin memilih filter department {string}")
    public void adminMemilihFilterDepartment(String department) {
        laporanCutiPage.selectFilterDepartment(department);
        ExtentReportUtil.logInfo("Memilih filter dilakukan");
    }

    @And("Admin klik tombol search")
    public void adminKlikTombolSearch() {
        laporanCutiPage.clickSearchButton();
        ExtentReportUtil.logInfo("Klik tombol Search dilakukan");
    }

    @Then("Data laporan cuti berhasil ditampilkan")
    public void dataLaporanBerhasilDitampilkan() {
        ExtentReportUtil.logInfo("Data laporan cuti berhasil ditampilkan");
        Assert.assertTrue(laporanCutiPage.verifyLaporanCutiDataIsDisplayed(), "Data laporan cuti tidak berhasil ditampilkan");
    }

    @Then("Tidak berhasil menampilkan data laporan Cuti")
    public void tidakBerhasilMenampilkanDataLaporanCuti() {
        ExtentReportUtil.logInfo("Data laporan cuti tidak berhasil ditampilkan");
        Assert.assertFalse(laporanCutiPage.verifyLaporanCutiDataIsDisplayed(), "Data laporan cuti berhasil ditampilkan, padahal seharusnya tidak ada data");
    }

    @When("Admin mengosongkan input nama")
    public void adminMengosongkanInputNama() {
        laporanCutiPage.inputNama("");
    }

    @And("Admin mengosongkan tanggal start date dan end date")
    public void adminMengosongkanTanggalStartDateDanEndDate() {
        laporanCutiPage.selectDate("", "");
    }

    @And("Admin mengosongkan filter department")
    public void adminMengosongkanFilterDepartment() {
        laporanCutiPage.selectFilterDepartment("");
    }
}

