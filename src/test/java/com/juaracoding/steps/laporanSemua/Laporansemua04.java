package com.juaracoding.steps.laporanSemua;

import com.juaracoding.DriverSingleton;
import com.juaracoding.pages.LaporanSemuaPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Laporansemua04 {

    WebDriver driver;
    LaporanSemuaPage laporanSemuaPage;

    @When("Klik menu laporan semua 04")
    public void klikMenuLaporanSemua() {
        driver = DriverSingleton.createOrGetDriver();
        laporanSemuaPage = new LaporanSemuaPage(driver);
        laporanSemuaPage.goToLaporanSemua();
    }

    @And("Masukkan nama yang sesuai {string} 04")
    public void masukkanNamaYangSesuai(String nama) {
        laporanSemuaPage.inputNama(nama);
    }

    @And("Pilih start date {string} dan end date {string} untuk memilih tanggal data laporan semua 04")
    public void pilihTanggal(String startDate, String endDate) {
        laporanSemuaPage.dateButton();
        laporanSemuaPage.setStartDate(startDate);
        laporanSemuaPage.setEndDate(endDate);
    }

    @And("Klik tombol dan kosongkan filter")
    public void klikTombolFilter() {
        laporanSemuaPage.clickFilter();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        laporanSemuaPage.searchDepartemen("");
        laporanSemuaPage.klikTerapkanFilter();
    }

    @Then("Klik tombol search 04")
    public void klikTombolSearch() {
        laporanSemuaPage.clickSearch();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody tr")));

        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        boolean dataDitemukan = false;

        for (WebElement row : rows) {
            String nama = row.findElement(By.cssSelector("td:nth-child(2) h6")).getText();
            System.out.println("Row ditemukan dengan nama: " + nama); // debug
            if (nama.equalsIgnoreCase("komar")) {
                dataDitemukan = true;
                break;
            }
        }

        Assert.assertTrue(dataDitemukan, "Data dengan nama 'komar' tidak ditemukan di tabel.");
    }
}
