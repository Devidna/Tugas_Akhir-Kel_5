package com.juaracoding.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class Utils {
    private static final Logger logger = Logger.getLogger(Utils.class.getName());

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

    public static WebElement getElementWithRetry(WebDriverWait wait, By pathType, int maxRetries, long sleepDurationMillis) {
        return retryWithStaleHandling(
                () -> wait.until(ExpectedConditions.presenceOfElementLocated(pathType)),
                maxRetries, sleepDurationMillis, "element", pathType.toString()
        );
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