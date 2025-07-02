package com.juaracoding.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LaporanSemuaPage {

    WebDriver driver;
    WebDriverWait wait;

    public LaporanSemuaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[contains(text(),'Laporan')]")
    WebElement navMenuLaporan;

    @FindBy(xpath = "//p[contains(text(),'Semua')]")
    WebElement laporanSemuaButton;

    @FindBy(css = "input[name='search']")
    WebElement inputNama;

    @FindBy(css = "button[aria-label='toggle password visibility'] svg.feather-calendar")
    WebElement dateButton;


    @FindBy(css = "input[placeholder='Early']")
    WebElement inputStartDate;

    @FindBy(css = "input[placeholder='Continuous']")
    WebElement inputEndDate;

    @FindBy(css = "button.MuiButton-containedSecondary")
    WebElement tombolFilter;


    @FindBy(css = "button[type='submit'].MuiButton-containedPrimary")
    WebElement tombolSearch;

    @FindBy(css = "body > div:nth-of-type(3) > div:nth-of-type(3) > div > form > div:first-of-type input")
    WebElement filterSearchDepartemen;

    @FindBy(xpath = "//button[contains(text(), 'Terapkan')]")
    WebElement tombolTerapkanFilter;

    public void goToLaporanSemua() {
        wait.until(ExpectedConditions.elementToBeClickable(navMenuLaporan)).click();
        wait.until(ExpectedConditions.elementToBeClickable(laporanSemuaButton)).click();
    }

    public void inputNama(String nama) {
        wait.until(ExpectedConditions.visibilityOf(inputNama)).clear();
        inputNama.sendKeys(nama);
    }

    public void dateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(dateButton)).click();
    }

    public void setStartDate(String tanggal) {
        wait.until(ExpectedConditions.visibilityOf(inputStartDate)).click();
        inputStartDate.sendKeys(Keys.CONTROL + "a");
        inputStartDate.sendKeys(Keys.DELETE);
        inputStartDate.sendKeys(tanggal);
    }

    public void setEndDate(String tanggal) {
        wait.until(ExpectedConditions.visibilityOf(inputEndDate)).click();
        inputStartDate.sendKeys(Keys.CONTROL + "a");
        inputStartDate.sendKeys(Keys.DELETE);
        inputEndDate.sendKeys(tanggal);
    }

    public void clickFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(tombolFilter)).click();
    }

    public void clickSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(tombolSearch)).click();
    }

    public void searchDepartemen(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(filterSearchDepartemen)).sendKeys(keyword);
    }

    public void klikTerapkanFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(tombolTerapkanFilter)).click();
    }
}
