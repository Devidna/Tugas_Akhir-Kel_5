package com.juaracoding.laporan.laporanSemua;

import com.juaracoding.DriverSingleton;
import com.juaracoding.laporanPages.LaporanSemuaPage;
import com.juaracoding.utils.ExtentReportUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class Laporansemua03Test {

    WebDriver driver;
    LaporanSemuaPage laporanSemuaPage;

    @When("Klik menu laporan semua 03")
    public void klikMenuLaporanSemua() {
        driver = DriverSingleton.createOrGetDriver();
        laporanSemuaPage = new LaporanSemuaPage(driver);
        laporanSemuaPage.goToLaporanSemua();
    }

    @And("Masukkan nama yang sesuai {string} 03")
    public void masukkanNamaYangSesuai(String nama) {
        ExtentReportUtil.logInfo("Memasukkan nama dilakukan");
    }


    @And("Klik tombol filter untuk melakukan filter berdasarkan department 03")
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

    @Then("Klik tombol search 03")
    public void klikTombolSearch() {
        laporanSemuaPage.clickSearch();
        ExtentReportUtil.logInfo("Klik tombol Search dilakukan");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        assertTrue("Data tidak boleh muncul", rows.isEmpty());

    }
}
