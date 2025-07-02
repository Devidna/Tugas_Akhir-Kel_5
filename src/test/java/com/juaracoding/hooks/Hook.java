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

        ScenarioContext.generateScenarioName(scenario);
        String scenarioName = ScenarioContext.getScenarioName();

        ExtentReportUtil.startScenario(scenarioName);
        ExtentReportUtil.logInfo("Mulai scenario: " + scenarioName);
        ExtentReportUtil.logWithScreenshot("Before Scenario", driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        String scenarioName = ScenarioContext.getScenarioName();

        if (scenario.isFailed()) {
            ExtentReportUtil.logFail("Scenario Gagal: " + scenarioName);
            ExtentReportUtil.logWithScreenshot("Failed - " + scenarioName, driver);
        } else {
            ExtentReportUtil.logPass("Scenario Berhasil: " + scenarioName);
            ExtentReportUtil.logWithScreenshot("After Scenario", driver);
        }

        DriverSingleton.quitDriver();
        ScenarioContext.remove();
    }

    @AfterAll
    public static void afterAll() {
        ExtentReportUtil.flush();
    }
}
