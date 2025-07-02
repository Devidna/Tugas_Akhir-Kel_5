package com.juaracoding.laporan.laporanKehadiran;

import com.juaracoding.DriverSingleton;
import com.juaracoding.authentication.LoginHelper;
import com.juaracoding.laporanPages.LaporanKehadiranPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import com.juaracoding.utils.utils;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LaporanKehadiranTest {
    WebDriver driver;
    LaporanKehadiranPage lKP;

    @Given("Admin login dan membuka laporan kehadiran")
    public void adminLogin() {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        lKP = new LaporanKehadiranPage(driver);

        LoginHelper loginHelper = new LoginHelper(driver);
        loginHelper.performLogin("admin@hadir.com", "MagangSQA_JC@123");

        utils.delay(3);
        lKP.bukaMenuLaporanKehadiran();
        utils.waitForUrlContains(driver, "/laporan/activity", 5);

        ExtentReportUtil.logInfo("Berhasil login dan membuka halaman laporan kehadiran");
    }

    @When("Input nama {string}, tanggal {string} hingga {string}, dan unit {string}")
    public void isiFormLaporan(String nama, String start, String end, String dept) {
        ExtentReportUtil.logInfo("Input form: nama=" + nama + ", tanggal=" + start + " - " + end + ", unit=" + dept);

        if (!nama.isEmpty()) {
            lKP.isiNama(nama);
        }
        if (!start.isEmpty() && !end.isEmpty()) {
            lKP.setTanggal(start, end);
            lKP.klikSave();
        }
        if (!dept.isEmpty()) {
            lKP.klikFilter();
            lKP.pilihDepartemen(dept);
            lKP.KlikFilterTerapkan();
        }
        lKP.klikSearch();
    }

    @And("Klik tombol reset filter laporan")
    public void klikTombolReset() {
        lKP.klikReset();
        ExtentReportUtil.logInfo("Klik tombol Reset Filter dilakukan");
    }

    @And("Klik tombol export data laporan")
    public void klikExportData() {
        lKP.klikExport();
        ExtentReportUtil.logInfo("Klik tombol Export dilakukan");
    }

    @And("Klik pagination dan pilih {string} rows")
    public void klikPagination(String jumlah) {
        lKP.pilihShowPage(jumlah);
        ExtentReportUtil.logInfo("Pilih jumlah rows: " + jumlah);
    }

    @And("Klik lokasi dari kolom Lokasi Masuk")
    public void klikLokasiMasuk() {
        lKP.klikLihatLokasi();
        ExtentReportUtil.logInfo("Klik Link Lokasi dilakukan");
    }

    @Then("Form filter laporan kembali kosong")
    public void formKosong() {
        boolean isKosong = lKP.isFormKosong();
        ExtentReportUtil.logInfo("Validasi form kosong setelah reset: " + isKosong);

        if (isKosong) {
            ExtentReportUtil.logPass("Form berhasil dikosongkan setelah reset");
        } else {
            ExtentReportUtil.logFailWithScreenshot("Form tidak kosong setelah reset", driver);
            Assert.fail("Form tidak kosong setelah reset");
        }
    }

    @Then("Cek hasil export data laporan")
    public void cekHasilExport() {
        if (lKP.isExportErrorToastDisplayed()) {
            ExtentReportUtil.logFailWithScreenshot("Export gagal: Muncul error toast", driver);
            Assert.fail("Export gagal: Muncul error toast");
        } else {
            ExtentReportUtil.logPass("Export berhasil tanpa error toast");
        }
    }

    @Then("Jumlah baris data yang tampil {string}")
    public void jumlahBarisData(String expectedRowCount) {
        int rowCount = lKP.getRowCount();
        ExtentReportUtil.logInfo("Jumlah baris: " + rowCount);

        if (String.valueOf(rowCount).equals(expectedRowCount)) {
            ExtentReportUtil.logPass("Jumlah baris sesuai dengan yang dipilih");
        } else {
            ExtentReportUtil.logFailWithScreenshot("Jumlah baris tidak sesuai. Expected: " + expectedRowCount + ", Actual: " + rowCount, driver);
            Assert.fail("Jumlah baris tidak sesuai. Expected: " + expectedRowCount + ", Actual: " + rowCount);
        }
    }

    @Then("Data laporan kehadiran tampil")
    public void dataLaporanKehadiranTampil() {
        int rowCount = lKP.getRowCount();
        String rowText = lKP.getTableRowText(0);

        ExtentReportUtil.logInfo("Hasil baris pertama: " + rowText);

        if (rowCount > 0 && rowText != null && !rowText.isEmpty()) {
            ExtentReportUtil.logPass("Data laporan kehadiran tampil");
        } else {
            ExtentReportUtil.logFailWithScreenshot("Data seharusnya tampil, tetapi tidak ditemukan.", driver);
            Assert.fail("Data seharusnya tampil, tetapi tidak ditemukan.");
        }
    }

    @Then("Data laporan kehadiran tidak tampil")
    public void dataLaporanKehadiranTidakTampil() {
        int rowCount = lKP.getRowCount();
        ExtentReportUtil.logInfo("Jumlah baris: " + rowCount);

        if (rowCount == 0) {
            ExtentReportUtil.logPass("Data laporan kehadiran tidak tampil sesuai harapan");
        } else {
            ExtentReportUtil.logFailWithScreenshot("Data seharusnya tidak tampil, tetapi ditemukan " + rowCount + " baris.", driver);
            Assert.fail("Data seharusnya tidak tampil, tetapi ditemukan " + rowCount + " baris.");
        }
    }

}
