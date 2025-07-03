package com.juaracoding.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

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
}