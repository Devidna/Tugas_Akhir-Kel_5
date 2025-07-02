package com.juaracoding.steps;

import com.juaracoding.DriverSingleton;
import com.juaracoding.helper.LoginHelper;
import com.juaracoding.pages.LaporanCutiPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LaporanCutiStep {
    LaporanCutiPage laporanCutiPage;

    public LaporanCutiStep() {
        laporanCutiPage = new LaporanCutiPage();
    }

    @And("Admin telah login dan buka halaman laporan cuti")
    public void adminBeradaDiHalamanLaporanCuti() {
        LoginHelper loginHelper = new LoginHelper(DriverSingleton.createOrGetDriver());
        loginHelper.performLogin("admin@hadir.com", "MagangSQA_JC@123");
        laporanCutiPage.goToLaporanCutiPage();
    }

    @When("Admin memasukkan nama {string} yang sesuai")
    public void adminMemasukkanNamaYangSesuai(String nama) {
        laporanCutiPage.inputNama(nama);
    }

    @And("Admin memilih tanggal start date {string} dan end date {string}")
    public void adminMemilihTanggalStartDateDanEndDate(String startDate, String endDate) {
        laporanCutiPage.selectDate(startDate, endDate);
    }

    @And("Admin memilih filter department {string}")
    public void adminMemilihFilterDepartment(String department) {
        laporanCutiPage.selectFilterDepartment(department);
    }

    @And("Admin klik tombol search")
    public void adminKlikTombolSearch() {
        laporanCutiPage.clickSearchButton();
    }

    @Then("Data laporan cuti berhasil ditampilkan")
    public void dataLaporanBerhasilDitampilkan() {
        Assert.assertTrue(laporanCutiPage.verifyLaporanCutiDataIsDisplayed(), "Data laporan cuti tidak berhasil ditampilkan");
    }

    @Then("Tidak berhasil menampilkan data laporan Cuti")
    public void tidakBerhasilMenampilkanDataLaporanCuti() {
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
