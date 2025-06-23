package com.juaracoding.hooks;

import com.juaracoding.DriverSingleton;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

public class Hook {
    WebDriver driver;

    @Before
    public void beforeScenario(Scenario scenario) {
        driver = DriverSingleton.createOrGetDriver();
        ScenarioContext.setScenarioName(scenario.getName());

        ExtentReportUtil.startScenario(scenario.getName());
        ExtentReportUtil.logInfo("Mulai scenario: " + scenario.getName());
        ExtentReportUtil.logWithScreenshot("- Before Scenario", driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.getStatus().name().equalsIgnoreCase("FAILED")) {
            ExtentReportUtil.logFail("Scenario Gagal: " + scenario.getName());
            ExtentReportUtil.logWithScreenshot("- Failed - " + scenario.getName(), driver);
        } else if (scenario.getStatus().name().equalsIgnoreCase("SKIPPED")) {
            ExtentReportUtil.logSkip("Scenario Dilewati (Skipped): " + scenario.getName());
            ExtentReportUtil.logWithScreenshot("- Skipped - " + scenario.getName(), driver);
        } else {
            ExtentReportUtil.logPass("Scenario Berhasil: " + scenario.getName());
            ExtentReportUtil.logWithScreenshot("- After Scenario", driver);
        }

        DriverSingleton.quitDriver();
        ScenarioContext.remove();
    }

    @AfterAll
    public static void afterAll() {
        ExtentReportUtil.flush();
    }
}
