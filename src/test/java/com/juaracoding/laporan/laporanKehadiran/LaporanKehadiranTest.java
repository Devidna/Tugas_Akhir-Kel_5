package com.juaracoding.laporan.laporanKehadiran;

import com.juaracoding.authentication.BaseLoginTest;
import com.juaracoding.DriverSingleton;
import com.juaracoding.laporanPages.LaporanKehadiranPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.utils;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LaporanKehadiranTest extends BaseLoginTest {
    WebDriver driver;
    LaporanKehadiranPage lKP;

    @Given("Admin login dan membuka laporan kehadiran")
    public void adminLogin() throws InterruptedException {
        driver = DriverSingleton.createOrGetDriver();
        lKP = new LaporanKehadiranPage(driver);

        baseLogin();
        utils.delay(3);
        lKP.bukaMenuLaporanKehadiran();

        ExtentReportUtil.logInfo("Berhasil login dan membuka halaman laporan kehadiran");
        ExtentReportUtil.logWithScreenshot("- Halaman awal laporan", driver);
    }

    @When("Input nama {string}, tanggal {string} hingga {string}, dan unit {string}")
    public void isiFormLaporan(String nama, String start, String end, String dept) throws InterruptedException {
        ExtentReportUtil.logInfo("Input form: nama=" + nama + ", tanggal=" + start + " - " + end + ", unit=" + dept);

        if (!nama.isEmpty()) {
            lKP.isiNama(nama);
            ExtentReportUtil.logWithScreenshot("- Masukkan Nama", driver);
        }
        if (!start.isEmpty() && !end.isEmpty()) {
            lKP.setTanggal(start, end);
            ExtentReportUtil.logWithScreenshot("- Masukkan Tanggal", driver);
        }
        lKP.klikFilter();
        if (!dept.isEmpty()) {
            lKP.pilihDepartemen(dept);
            ExtentReportUtil.logWithScreenshot("- Memilih Departemen", driver);
            lKP.KlikFilterTerapkan();
        } else {
            lKP.klikBatal();
        }
        lKP.klikSearch();
        ExtentReportUtil.logWithScreenshot("- Setelah klik search", driver);
    }

    @And("Klik tombol export data laporan")
    public void klikExportData() {
        lKP.klikExport();
        ExtentReportUtil.logInfo("Klik tombol Export dilakukan");
        ExtentReportUtil.logWithScreenshot("- Setelah klik Export", driver);
    }

    @And("Klik lokasi dari kolom Lokasi Masuk")
    public void klikLokasiMasuk() {
        lKP.klikLihatLokasi();
        ExtentReportUtil.logInfo("Klik Link Lokasi dilakukan");
        ExtentReportUtil.logWithScreenshot("- Setelah klik lihat lokasi", driver);
    }

    @Then("Data laporan kehadiran tampil")
    public void dataLaporanKehadiranTampil() {
        int rowCount = lKP.getRowCount();
        String rowText = lKP.getTableRowText(0);

        ExtentReportUtil.logInfo("Hasil baris pertama: " + rowText);
        ExtentReportUtil.logWithScreenshot("- Tabel data tampil", driver);

        Assert.assertTrue(rowCount > 0 && rowText != null && !rowText.isEmpty(),
                "Data seharusnya tampil, tetapi tidak ditemukan.");
        ExtentReportUtil.logPass("Data laporan kehadiran tampil");
    }

    @Then("Data laporan kehadiran tidak tampil")
    public void dataLaporanKehadiranTidakTampil() {
        int rowCount = lKP.getRowCount();
        ExtentReportUtil.logInfo("Jumlah baris: " + rowCount);
        ExtentReportUtil.logWithScreenshot("- Tabel data tidak tampil", driver);

        Assert.assertEquals(rowCount, 0,
                "Data seharusnya tidak tampil, tetapi ditemukan " + rowCount + " baris.");
        ExtentReportUtil.logPass("Data laporan kehadiran tidak tampil sesuai harapan");
    }


}
