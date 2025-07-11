package com.juaracoding.laporan.laporanSemua;

import com.juaracoding.DriverSingleton;
import com.juaracoding.authentication.LoginHelper;
import com.juaracoding.laporanPages.LaporanSemuaPage;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Laporansemua01Test {

    WebDriver driver;
    LaporanSemuaPage laporanSemuaPage;

    @Given("Login")
    public void loginAcc(){
        System.out.println("[TEST] " + ScenarioContext.getScenarioName());
        driver = DriverSingleton.createOrGetDriver();
        LoginHelper loginHelper = new LoginHelper(driver);
        loginHelper.performLogin("admin@hadir.com", "MagangSQA_JC@123");
    }

    @When("Klik menu laporan semua")
    public void klikMenuLaporanSemua() {
        laporanSemuaPage = new LaporanSemuaPage(driver);
        laporanSemuaPage.goToLaporanSemua();
    }

    @And("Masukkan nama yang sesuai {string}")
    public void masukkanNamaYangSesuai(String nama) {
        ExtentReportUtil.logInfo("Memasukkan nama dilakukan");
        laporanSemuaPage.inputNama(nama);
    }

    @And("Pilih start date {string} dan end date {string} untuk memilih tanggal data laporan semua")
    public void pilihTanggal(String startDate, String endDate) {
        laporanSemuaPage.dateButton();
        laporanSemuaPage.setStartDate(startDate);
        laporanSemuaPage.setEndDate(endDate);;
        ExtentReportUtil.logInfo("Memilih tanggal dilakukan");
    }

    @And("Klik tombol filter untuk melakukan filter berdasarkan department")
    public void klikTombolFilter() {
        laporanSemuaPage.clickFilter();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        laporanSemuaPage.searchDepartemen("EDC Nasional");
        laporanSemuaPage.klikTerapkanFilter();
        ExtentReportUtil.logInfo("Memilih filter dilakukan");
    }

    @Then("Klik tombol search")
    public void klikTombolSearch() {
        laporanSemuaPage.clickSearch();
        ExtentReportUtil.logInfo("Klik tombol Search dilakukan");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody tr")));

        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        boolean dataDitemukan = false;

        for (WebElement row : rows) {
            String nama = row.findElement(By.cssSelector("td:nth-child(2) h6")).getText();
            ExtentReportUtil.logInfo("Row ditemukan dengan nama: " + nama);
            if (nama.equalsIgnoreCase("komar")) {
                dataDitemukan = true;
                break;
            }
        }

        Assert.assertTrue(dataDitemukan, "Data dengan nama 'komar' tidak ditemukan di tabel.");
    }

}
