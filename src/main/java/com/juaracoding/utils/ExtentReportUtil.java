package com.juaracoding.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportUtil {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    static {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportPath = "target/extent-report_" + timeStamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static void startScenario(String scenarioName) {
        ExtentTest extentTest = extent.createTest(scenarioName);
        test.set(extentTest);
    }

    public static void logInfo(String message) {
        test.get().log(Status.INFO, message);
    }

    public static void logPass(String message) {
        test.get().log(Status.PASS, message);
    }

    public static void logFail(String message) {
        test.get().log(Status.FAIL, message);
    }

    public static void logSkip(String message) {
        test.get().log(Status.SKIP, message);
    }

    public static void logWithScreenshot(String title, WebDriver driver) {
        String path = ScreenshotUtil.takeScreenshot(driver, title);
        try {
            test.get().addScreenCaptureFromPath(path, title);
        } catch (Exception e) {
            test.get().log(Status.WARNING, "Gagal menambahkan screenshot: " + e.getMessage());
        }
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
