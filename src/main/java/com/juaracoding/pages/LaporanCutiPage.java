package com.juaracoding.pages;

import com.juaracoding.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.juaracoding.utils.Utils.doWithRetry;
import static com.juaracoding.utils.Utils.getAllElementsWithRetry;

// page_url = https://magang.dikahadir.com/laporan/cuti
public class LaporanCutiPage extends BasePage {

    @FindBy(xpath = "//p[contains(@class, 'css-1ub5lza') and text()='Laporan']")
    WebElement laporanListNavigation;
    @FindBy(xpath = "//p[contains(@class, 'css-aqx7sf') and text()='Cuti']")
    WebElement laporanCutiPageNavigation;
    @FindBy(xpath = "//p[contains(@class, 'css-1kei35f')]")
    WebElement laporanCutiPageTitle;
    @FindBy(css = "input[placeholder='Cari berdasarkan nama']")
    WebElement inputNama;
    @FindBy(css = "input[placeholder='Early']")
    WebElement inputStartDate;
    @FindBy(xpath = "//input[@placeholder='Start Date']/following-sibling::div/button[@aria-label='toggle password visibility']")
    WebElement startDateButton;
    @FindBy(css = "input[placeholder='Continuous']")
    WebElement inputEndDate;
    @FindBy(xpath = "//button[contains(@class, 'css-4075ia') and text()='save']")
    WebElement saveDateButton;
    @FindBy(xpath = "//button[contains(@class, 'css-1k0lhp1')]")
    WebElement filterButton;
    @FindBy(css = "input[placeholder='Cari Departemen']")
    WebElement inputFilterDepartemen;
    @FindBy(xpath = "//button[contains(@class, 'css-4075ia') and text()='Terapkan']")
    WebElement filterSubmitButton;
    @FindBy(css = "tbody tr")
    List<WebElement> cutiDataRows;
    @FindBy(xpath = "//button[contains(@class, 'css-td9qyd') and text()='Search']")
    WebElement searchButton;

    public LaporanCutiPage() {
        driver = DriverSingleton.createOrGetDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver, this);
    }

    public void goToLaporanCutiPage() {
        wait.until(ExpectedConditions.elementToBeClickable(laporanListNavigation)).click();
        wait.until(ExpectedConditions.elementToBeClickable(laporanCutiPageNavigation)).click();
        wait.until(ExpectedConditions.visibilityOf(laporanCutiPageTitle));
    }

    public void inputNama(String johnDoe) {
        wait.until(ExpectedConditions.elementToBeClickable(inputNama));
        inputNama.sendKeys(Keys.CONTROL + "a");
        inputNama.sendKeys(Keys.DELETE);
        inputNama.sendKeys(johnDoe);
    }

    public void selectDate(String startDate, String endDate) {
        wait.until(ExpectedConditions.elementToBeClickable(startDateButton)).click();
        selectStartDate(startDate);
        selectEndDate(endDate);
        wait.until(ExpectedConditions.elementToBeClickable(saveDateButton)).click();
    }

    public void selectStartDate(String startDate) {
        wait.until(ExpectedConditions.elementToBeClickable(inputStartDate));
        inputStartDate.sendKeys(Keys.CONTROL + "a");
        inputStartDate.sendKeys(Keys.DELETE);
        inputStartDate.sendKeys(startDate);
    }

    public void selectEndDate(String endDate) {
        wait.until(ExpectedConditions.elementToBeClickable(inputEndDate));
        inputEndDate.sendKeys(Keys.CONTROL + "a");
        inputEndDate.sendKeys(Keys.DELETE);
        inputEndDate.sendKeys(endDate);
    }

    public void selectFilterDepartment(String departemen) {
        wait.until(ExpectedConditions.elementToBeClickable(filterButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", filterButton);
        filterButton.click();
        inputFilterDepartemen.sendKeys(departemen);

        if (departemen.isEmpty()) {
            inputFilterDepartemen.sendKeys(Keys.ENTER);
        } else {
            try {
                List<WebElement> options = getAllElementsWithRetry(
                        wait,
                        By.xpath("//ul[@id='job_departement-listbox']/li"),
                        2, 1000
                );

                doWithRetry(() -> {
                    for (WebElement option : options) {
                        if (option.getText().isEmpty()) {
                            continue;
                        }
                        if (option.getText().equalsIgnoreCase(departemen)) {
                            option.click();
                            break;
                        }
                    }
                    return null;
                }, 3, 2000);
            } catch (TimeoutException e) {
            }
        }

        fluentWait.until(ExpectedConditions.elementToBeClickable(filterSubmitButton)).click();
    }

    public void clickSearchButton() {
        searchButton.click();
        waitForTopProgressBarToDisappear();
    }

    public Boolean verifyLaporanCutiDataIsDisplayed() {
        boolean isDataDisplayed = false;

        for (WebElement row : cutiDataRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            StringBuilder tmpText = new StringBuilder();
            int tempCouter = 0;

            for (WebElement cell : cells) {
                tempCouter++;
                if (tempCouter <= 1) {
                    continue; // Skip the first cell if it is not needed
                }

                String cellText = cell.findElement(By.cssSelector("h6")).getText();

                if (cellText.isEmpty()) {
                    continue; // Skip empty cells
                } else {
                    if (tempCouter == 2) {
                        tmpText.append("Nama Karyawan:").append(cellText).append(", ");
                    } else if (tempCouter == 3) {
                        tmpText.append("Tgl Pengajuan: ").append(cellText).append(", ");
                    } else if (tempCouter == 4) {
                        tmpText.append("Tgl Cuti: ").append(cellText).append(", ");
                    } else if (tempCouter == 5) {
                        tmpText.append("Upliner: ").append(cellText).append(", ");
                    } else if (tempCouter == 8) {
                        tmpText.append("Tipe Cuti: ").append(cellText);
                    }

                    if (tempCouter == 8) {
                        isDataDisplayed = true; // At least one cell has data
                        System.out.println("Data ditemukan: " + tmpText);
                    }
                }
                if (tempCouter == 8) {
                    break; // Exit loop after processing 6 cells
                }
            }
            if (isDataDisplayed) break;
        }

        return isDataDisplayed;
    }
}
