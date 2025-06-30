package com.juaracoding.laporan.laporanKehadiran;

import com.juaracoding.authentication.BaseLoginTest;
import com.juaracoding.DriverSingleton;
import com.juaracoding.laporanPages.LaporanKehadiranPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import com.juaracoding.utils.utils;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LaporanKehadiranTest extends BaseLoginTest {
    WebDriver driver;
    LaporanKehadiranPage lKP;

    @Given("Admin login dan membuka laporan kehadiran")
    public void adminLogin() throws InterruptedException {
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        lKP = new LaporanKehadiranPage(driver);

        baseLogin();
        utils.delay(3);
        lKP.bukaMenuLaporanKehadiran();
        utils.waitForUrlContains(driver, "/laporan/activity", 5);

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
            lKP.klikSave();
        }
        if (!dept.isEmpty()) {
            lKP.klikFilter();
            lKP.pilihDepartemen(dept);
            ExtentReportUtil.logWithScreenshot("- Memilih Departemen", driver);
            lKP.KlikFilterTerapkan();
        }
        lKP.klikSearch();
        ExtentReportUtil.logWithScreenshot("- Setelah klik search", driver);
    }

    @And("Klik tombol reset filter laporan")
    public void klikTombolReset() {
        lKP.klikReset();
        ExtentReportUtil.logInfo("Klik tombol Reset Filter dilakukan");
        ExtentReportUtil.logWithScreenshot("- Setelah klik Reset", driver);
    }

    @And("Klik tombol export data laporan")
    public void klikExportData() {
        lKP.klikExport();
        ExtentReportUtil.logInfo("Klik tombol Export dilakukan");
        ExtentReportUtil.logWithScreenshot("- Setelah klik Export", driver);

        if (lKP.isExportErrorToastDisplayed()) {
            ExtentReportUtil.logFail("Export gagal: Muncul error toast");
            ExtentReportUtil.logWithScreenshot("- Error Export", driver);
        } else {
            ExtentReportUtil.logInfo("Export berhasil tanpa error toast");
        }
    }

    @And("Klik pagination dan pilih {string} rows")
    public void klikPagination(String jumlah) {
        lKP.pilihShowPage(jumlah);
        ExtentReportUtil.logInfo("Pilih jumlah rows: " + jumlah);
        ExtentReportUtil.logWithScreenshot("- Setelah pilih rows", driver);
    }

    @And("Klik lokasi dari kolom Lokasi Masuk")
    public void klikLokasiMasuk() {
        lKP.klikLihatLokasi();
        ExtentReportUtil.logInfo("Klik Link Lokasi dilakukan");
        ExtentReportUtil.logWithScreenshot("- Setelah klik lihat lokasi", driver);
    }

    @Then("Form filter laporan kembali kosong")
    public void formKosong() {
        boolean isKosong = lKP.isFormKosong();

        ExtentReportUtil.logInfo("Validasi form kosong setelah reset: " + isKosong);

        if (isKosong) {
            ExtentReportUtil.logPass("Form berhasil dikosongkan setelah reset");
        } else {
            ExtentReportUtil.logFail("Form tidak kosong setelah reset");
            ExtentReportUtil.logWithScreenshot("- Error Reset", driver);
            Assert.fail("Form tidak kosong setelah reset");
        }
    }

    @Then("Jumlah baris data yang tampil {string}")
    public void jumlahBarisData(String expectedRowCount) {
        int rowCount = lKP.getRowCount();
        ExtentReportUtil.logInfo("Jumlah baris: " + rowCount);
        ExtentReportUtil.logWithScreenshot("- Tampil jumlah baris", driver);

        if (String.valueOf(rowCount).equals(expectedRowCount)) {
            ExtentReportUtil.logPass("Jumlah baris sesuai dengan yang dipilih");
        } else {
            ExtentReportUtil.logFail("Jumlah baris tidak sesuai. Expected: " + expectedRowCount + ", Actual: " + rowCount);
            ExtentReportUtil.logWithScreenshot("- Error Jumlah Baris", driver);
            Assert.fail("Jumlah baris tidak sesuai. Expected: " + expectedRowCount + ", Actual: " + rowCount);
        }
    }

    @Then("Data laporan kehadiran tampil")
    public void dataLaporanKehadiranTampil() {
        int rowCount = lKP.getRowCount();
        String rowText = lKP.getTableRowText(0);

        ExtentReportUtil.logInfo("Hasil baris pertama: " + rowText);
        ExtentReportUtil.logWithScreenshot("- Tabel data tampil", driver);

        if (rowCount > 0 && rowText != null && !rowText.isEmpty()) {
            ExtentReportUtil.logPass("Data laporan kehadiran tampil");
        } else {
            ExtentReportUtil.logFail("Data seharusnya tampil, tetapi tidak ditemukan.");
            ExtentReportUtil.logWithScreenshot("- Error Data Tidak Tampil", driver);
            Assert.fail("Data seharusnya tampil, tetapi tidak ditemukan.");
        }
    }

    @Then("Data laporan kehadiran tidak tampil")
    public void dataLaporanKehadiranTidakTampil() {
        int rowCount = lKP.getRowCount();
        ExtentReportUtil.logInfo("Jumlah baris: " + rowCount);
        ExtentReportUtil.logWithScreenshot("- Tabel data tidak tampil", driver);

        if (rowCount == 0) {
            ExtentReportUtil.logPass("Data laporan kehadiran tidak tampil sesuai harapan");
        } else {
            ExtentReportUtil.logFail("Data seharusnya tidak tampil, tetapi ditemukan " + rowCount + " baris.");
            ExtentReportUtil.logWithScreenshot("- Error Data Muncul", driver);
            Assert.fail("Data seharusnya tidak tampil, tetapi ditemukan " + rowCount + " baris.");
        }
    }

}
