package com.juaracoding.laporanPages;

import com.juaracoding.utils.utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
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

    @FindBy(xpath = "//li[contains(@class,'MuiAutocomplete-option')]")
    private List<WebElement> dropdownSuggest;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> tableRows;

    public LaporanKehadiranPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void bukaMenuLaporanKehadiran() {
        utils.waitUntilClickable(driver, menuLaporan, 10).click();
        utils.waitUntilClickable(driver, subMenuKehadiran, 10).click();
    }

    public void isiNama(String nama) {
        utils.waitUntilVisible(driver, inputNama, 10).clear();
        inputNama.sendKeys(nama);
    }

    public void setTanggal(String startDate, String endDate) {
        utils.waitUntilClickable(driver, btnCalender, 10).click();

        WebElement startDateElement = utils.waitUntilClickable(driver, setStartDate, 10);
        startDateElement.click();
        startDateElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        startDateElement.sendKeys(Keys.DELETE);
        startDateElement.sendKeys(startDate);

        WebElement endDateElement = utils.waitUntilClickable(driver, setEndDate, 10);
        endDateElement.click();
        endDateElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        endDateElement.sendKeys(Keys.DELETE);
        endDateElement.sendKeys(endDate);
    }

    public void klikSave() {
        utils.waitUntilClickable(driver, btnSave, 10).click();
    }

    public void pilihDepartemen(String departemen) {
        WebElement unitField = utils.waitUntilClickable(driver, selectUnit, 10);
        unitField.clear();
        unitField.click();
        unitField.sendKeys(departemen);

        if (isSuggestionVisible()) {
            unitField.sendKeys(Keys.ARROW_DOWN);
            unitField.sendKeys(Keys.ENTER);
        }
    }

    private boolean isSuggestionVisible() {
        try {
            utils.waitUntilVisible(driver, dropdownSuggest.get(0), 5);
            return dropdownSuggest != null && !dropdownSuggest.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void klikFilter() {
        utils.waitUntilClickable(driver, btnFilter, 10).click();
    }

    public void KlikFilterTerapkan() {
        utils.waitUntilClickable(driver, btnFilterTerapkan, 10).click();
    }

    public void klikSearch() {
        utils.waitUntilClickable(driver, btnSearch, 10).click();
        utils.waitUntilVisible(driver, driver.findElement(By.xpath("//tbody")), 10);
    }

    public void klikReset() {
        utils.waitUntilClickable(driver, btnReset, 10).click();
    }

    public boolean isFormKosong() {
        try {
            return isNullOrEmpty(inputNama.getAttribute("value")) &&
                    isNullOrEmpty(setStartDate2.getAttribute("value")) &&
                    isNullOrEmpty(setEndDate2.getAttribute("value"));
        } catch (Exception e) {
            System.out.println("Gagal cek form kosong setelah reset: " + e.getMessage());
            return false;
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public void klikExport() {
        utils.waitUntilClickable(driver, btnExport, 10).click();
        utils.waitUntilClickable(driver, btnExportData, 10).click();
    }

    public boolean isExportErrorToastDisplayed() {
        try {
            return utils.waitUntilVisible(driver, toastErrorExport, 5).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void klikLihatLokasi() {
        try {
            if (utils.waitUntilVisible(driver, linkLihatLokasi, 5).isDisplayed()) {
                linkLihatLokasi.click();
            }
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Link lihat lokasi tidak ditemukan.");
        }
    }

    public void pilihShowPage(String jumlah) {
        utils.waitUntilClickable(driver, showPageDropdown, 10).click();
        WebElement opsiJumlah = utils.waitUntilClickable(driver,
                driver.findElement(By.xpath("//li[normalize-space(text())='" + jumlah + "']")), 10);
        opsiJumlah.click();

        int expectedRow = Integer.parseInt(jumlah);
        utils.waitUntilRowCountEquals(driver, tableRows, expectedRow, 10);
    }

    public String getTableRowText(int index) {
        try {
            utils.waitUntilVisible(driver, tableRows.get(index), 10);
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
