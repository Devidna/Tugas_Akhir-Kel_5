package com.juaracoding.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class utils {

    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForUrlContains(WebDriver driver, String urlPart, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.urlContains(urlPart));
    }

    public static WebElement waitUntilVisible(WebDriver driver, WebElement element, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilClickable(WebDriver driver, WebElement element, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilTableVisible(WebDriver driver, List<WebElement> rows, WebElement thead, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(d -> {
            try {
                return (rows != null && !rows.isEmpty()) || thead.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
    }

    public static void waitUntilRowCountEquals(WebDriver driver, List<WebElement> rows, int expectedCount, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(d -> rows.size() == expectedCount);
    }

    private static final Logger logger = Logger.getLogger(utils.class.getName());

    public static void doWithRetry(Supplier<Void> action, int maxRetries, long sleepDurationMillis) {
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                action.get();
                return; // Success
            } catch (StaleElementReferenceException | ElementClickInterceptedException | NoSuchElementException e) {
                logger.warning(String.format("StaleElementReferenceException on attempt %d", attempt + 1));
                if (attempt < maxRetries - 1) {
                    try {
                        Thread.sleep(sleepDurationMillis);
                    } catch (InterruptedException ignored) {}
                } else {
                    logger.severe(String.format("Failed after %d attempts", maxRetries));
                    throw e;
                }
            } catch (TimeoutException e) {
                logger.severe(String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage()));
                throw e;
            }
        }
    }

    public static List<WebElement> getAllElementsWithRetry(WebDriverWait wait, By pathType, int maxRetries, long sleepDurationMillis) {
        return retryWithStaleHandling(
                () -> wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pathType)),
                maxRetries, sleepDurationMillis, "elements", pathType.toString()
        );
    }

    private static <T> T retryWithStaleHandling(Supplier<T> supplier, int maxRetries, long sleepDurationMillis, String type, String xpath) {
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                return supplier.get();
            } catch (StaleElementReferenceException e) {
                logger.warning(String.format("StaleElementReferenceException on attempt %d for %s: %s", attempt + 1, type, xpath));
                if (attempt < maxRetries - 1) {
                    try {
                        Thread.sleep(sleepDurationMillis);
                    } catch (InterruptedException ignored) {
                    }
                } else {
                    logger.severe(String.format("Failed to locate %s after %d attempts: %s", type, maxRetries, xpath));
                    throw e;
                }
            } catch (NoSuchElementException | TimeoutException e) {
                logger.severe(String.format("%s for %s: %s", e.getClass().getSimpleName(), type, xpath));
                throw e;
            }
        }
        return null;
    }
}