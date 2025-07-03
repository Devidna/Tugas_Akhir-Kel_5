package com.juaracoding.dashboardPages;

import com.juaracoding.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.juaracoding.utils.utils.doWithRetry;
import static com.juaracoding.utils.utils.getAllElementsWithRetry;

// page_url = "https://magang.dikahadir.com/dashboards/pending"
public class PendingPage {

    @FindBy(xpath = "//p[contains(@class, 'css-1kei35f') and text()='Dashboard']")
    WebElement dashboardPageTitle;
    @FindBy(xpath = "//p[contains(@class, 'css-aqx7sf')]")
    WebElement dashboardPageButtion;
    @FindBy(xpath = "//div[contains(@class, 'css-16mw8l2')]")
    WebElement pendingButton;
    @FindBy(xpath = "//p[contains(@class, 'css-1kei35f') and text()='Pending']")
    WebElement pendingPageTitle;
    @FindBy(xpath = "//button[contains(@class, 'css-zgm2yr') and text()='Reset']")
    WebElement pendingResetButton;
    @FindBy(xpath = "//button[contains(@class, 'css-4075ia') and text()='save']")
    WebElement saveDateButton;
    @FindBy(xpath = "//button[contains(@class, 'css-1a0u513')]")
    WebElement filterButton;
    @FindBy(css = "input[placeholder='Cari Departemen']")
    WebElement inputFilterDepartemen;
    @FindBy(xpath = "//button[contains(@class, 'css-4075ia') and text()='Terapkan']")
    WebElement filterSubmitButton;
    @FindBy(xpath = "//input[@placeholder='Start Date']/following-sibling::div/button[@aria-label='toggle password visibility']")
    WebElement startDateButton;
    @FindBy(css = "input[placeholder='Early']")
    WebElement inputStartDate;
    @FindBy(css = "input[placeholder='Continuous']")
    WebElement inputEndDate;
    @FindBy(xpath = "//button[contains(@class, 'css-b07ihu') and text()='Search']")
    WebElement searchButton;
    @FindBy(css = "tbody tr")
    List<WebElement> pendingDataRows;
    @FindBy(css = "div[id='nprogress']")
    WebElement topProgressBar;

    WebDriver driver;
    WebDriverWait wait;
    FluentWait <WebDriver> fluentWait;

    public void waitForTopProgressBarToDisappear() {
        // Wait for bar to disappear
        wait.until(ExpectedConditions.invisibilityOf(topProgressBar));
    }

    public PendingPage() {
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

    public Boolean isDashboardPageDisplayed() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardPageButtion)).click();
        wait.until(ExpectedConditions.visibilityOf(dashboardPageTitle));
        return true;
    }

    public void goToPendingPage() {
        wait.until(ExpectedConditions.elementToBeClickable(pendingButton)).click();
        wait.until(ExpectedConditions.visibilityOf(pendingPageTitle));
        fluentWait.until(ExpectedConditions.elementToBeClickable(pendingResetButton)).click();
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

    public void setFilter(String departemen) {
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
                        3, 3000
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
                }, 3, 1000);
            } catch (TimeoutException e) {
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(filterSubmitButton)).click();
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        waitForTopProgressBarToDisappear();
    }

    public Boolean verifyPendingDataIsDisplayed() {
        boolean isDataDisplayed = false;
        int tempCouter = 0;

        for (WebElement row : pendingDataRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            StringBuilder tmpText = new StringBuilder();

            for (WebElement cell : cells) {
                tempCouter++;
                String cellText = cell.findElement(By.tagName("h6")).getText();

                if (cellText.isEmpty()) {
                    continue; // Skip empty cells
                } else {
                    if (tempCouter % 2 == 1) {
                        tmpText.append(cellText).append(", ");
                    } else {
                        tmpText.append("Total: ").append(cellText);
                    }

                    if (tempCouter % 2 == 0) {
                        isDataDisplayed = true; // At least one cell has data
                    }
                }
            }
            if (isDataDisplayed) break; // Exit loop if at least one data row is found
        }
        return isDataDisplayed;
    }
}
