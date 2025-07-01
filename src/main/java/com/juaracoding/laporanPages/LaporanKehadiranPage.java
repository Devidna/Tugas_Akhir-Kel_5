package com.juaracoding.laporanPages;

import com.juaracoding.utils.utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LaporanKehadiranPage {
    private WebDriver driver;

    @FindBy(xpath = "//p[normalize-space()='Laporan']")
    private WebElement menuLaporan;

    @FindBy(xpath = "//p[normalize-space()='Kehadiran']")
    private WebElement subMenuKehadiran;

    @FindBy(xpath = "//*[@id=\"search\"]")
    private WebElement inputNama;

    @FindBy(xpath = "//button[normalize-space()='save']")
    private WebElement btnSave;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-7 MuiGrid-grid-lg-8 css-kw2xn2']//div[1]//div[1]//div[1]//button[1]//*[name()='svg']")
    private WebElement btnCalender;

    @FindBy(xpath = "//input[@placeholder='Early']")
    private WebElement setStartDate;

    @FindBy(xpath = "// input[@placeholder='Continuous']")
    private WebElement setEndDate;

    @FindBy(xpath = "//input[@placeholder='Start Date']")
    private WebElement setStartDate2;

    @FindBy(xpath = "//input[@placeholder='End Date']")
    private WebElement setEndDate2;

    @FindBy(xpath = "//input[@placeholder='Cari Departemen']")
    private WebElement selectUnit;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div/button[1]")
    private WebElement btnFilter;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'MuiButton-containedPrimary') and contains(text(), 'Terapkan')]")
    private WebElement btnFilterTerapkan;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSearch;

    @FindBy(xpath = "//button[@type='button' and contains(text(), 'Reset')]")
    private WebElement btnReset;

    @FindBy(xpath = "//h6[contains(@class, 'MuiTypography-root')]/a[contains(@href, 'maps')]")
    private WebElement linkLihatLokasi;

    @FindBy(xpath = "//button[normalize-space()='Export']")
    private WebElement btnExport;

    @FindBy(xpath = "//*[@id=\"generate-report\"]/div[2]/button[1]")
    private WebElement btnExportData;

    @FindBy(xpath = "//*[contains(text(),'Maaf terjadi kesalahan pada server')]")
    private WebElement toastErrorExport;

    @FindBy(xpath = "//div[@role='combobox']")
    private WebElement showPageDropdown;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> tableRows;

    public LaporanKehadiranPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void bukaMenuLaporanKehadiran() throws InterruptedException {
        menuLaporan.click();
        utils.delay(2);
        subMenuKehadiran.click();
        utils.delay(2);
    }

    public void isiNama(String nama) {
        inputNama.clear();
        inputNama.sendKeys(nama);
        utils.delay(2);
    }

    public void setTanggal(String startDate, String endDate) {
        btnCalender.click();
        utils.delay(2);
        setStartDate.click();
        setStartDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        setStartDate.sendKeys(Keys.DELETE);
        setStartDate.sendKeys(startDate);
        utils.delay(2);
        setEndDate.click();
        setEndDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        setEndDate.sendKeys(Keys.DELETE);
        setEndDate.sendKeys(endDate);
        utils.delay(2);
    }

    public void klikSave() {
        btnSave.click();
        utils.delay(2);
    }

    public void pilihDepartemen(String departemen) {
        selectUnit.clear();
        selectUnit.click();
        selectUnit.sendKeys(departemen);
        utils.delay(2);
        selectUnit.sendKeys(Keys.ARROW_DOWN);
        utils.delay(2);
        selectUnit.sendKeys(Keys.ENTER);
        utils.delay(2);
    }

    public void klikFilter() {
        btnFilter.click();
        utils.delay(2);
    }

    public void KlikFilterTerapkan() {
        btnFilterTerapkan.click();
        utils.delay(2);
    }

    public void klikSearch() throws InterruptedException {
        btnSearch.click();
        utils.delay(2);
    }

    public void klikReset() {
        btnReset.click();
        utils.delay(2);
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public boolean isFormKosong() {
        try {
            btnFilter.click();
            utils.delay(2);
            return isNullOrEmpty(inputNama.getAttribute("value")) &&
                    isNullOrEmpty(setStartDate2.getAttribute("value")) &&
                    isNullOrEmpty(setEndDate2.getAttribute("value")) &&
                    isNullOrEmpty(selectUnit.getAttribute("value"));
        } catch (Exception e) {
            System.out.println("Gagal cek form kosong setelah reset: " + e.getMessage());
            return false;
        }
    }

    public void klikLihatLokasi() {
        try {
            if (linkLihatLokasi.isDisplayed()) {
                linkLihatLokasi.click();
                utils.delay(2);
            }
        } catch (Exception e) {
            System.out.println("Link lihat lokasi tidak ditemukan.");
        }
    }

    public void klikExport() {
        btnExport.click();
        utils.delay(2);
        btnExportData.click();
        utils.delay(2);
    }

    public boolean isExportErrorToastDisplayed() {
        try {
            return toastErrorExport.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void pilihShowPage(String jumlah) {
        showPageDropdown.click();
        WebElement opsiJumlah = driver.findElement(By.xpath("//li[normalize-space(text())='" + jumlah + "']"));
        opsiJumlah.click();
        utils.delay(2);
    }

    public String getTableRowText(int index) {
        try {
            return tableRows.get(index).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getAllTableRowTexts() {
        List<String> texts = new ArrayList<>();
        for (WebElement row : tableRows) {
            texts.add(row.getText());
        }
        return texts;
    }

    public int getRowCount() {
        return tableRows.size();
    }

}