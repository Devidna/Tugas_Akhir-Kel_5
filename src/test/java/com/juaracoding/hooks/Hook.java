package com.juaracoding.hooks;

import com.juaracoding.DriverSingleton;
import com.juaracoding.utils.ExtentReportUtil;
import com.juaracoding.utils.ScenarioContext;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class Hook {
    WebDriver driver;

    // Static counter per tag prefix
    private static final Map<String, Integer> tagCounters = new HashMap<>();

    // Mapping tag ke prefix
    private static final Map<String, String> tagPrefixMap = new HashMap<>();
    static {
        tagPrefixMap.put("@laporanKehadiran", "LPRK-WEB-");
        tagPrefixMap.put("@dashboard", "DB-WEB-");
        tagPrefixMap.put("@authentication", "AUTH-WEB-");
        tagPrefixMap.put("@laporanSemua", "LPRS-WEB-");
        // Tambahkan tag lain jika ada feature baru
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        driver = DriverSingleton.createOrGetDriver();

        // generate kode skenario berdasarkan tag
        String scenarioName = scenario.getName();
        String prefix = "";
        String finalScenarioName = scenarioName;

        for (String tag : scenario.getSourceTagNames()) {
            if (tagPrefixMap.containsKey(tag)) {
                prefix = tagPrefixMap.get(tag);

                // Increment counter per tag
                int count = tagCounters.getOrDefault(tag, 0) + 1;
                tagCounters.put(tag, count);

                // Format nomor urut jadi 3 digit
                String formattedNumber = String.format("%03d", count);

                finalScenarioName = prefix + formattedNumber + " - " + scenarioName;
                break;
            }
        }

        // Save ke context dan extent report
        ScenarioContext.setScenarioName(finalScenarioName);
        ExtentReportUtil.startScenario(finalScenarioName);
        ExtentReportUtil.logInfo("Mulai scenario: " + finalScenarioName);
        ExtentReportUtil.logWithScreenshot("- Before Scenario", driver);
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            ExtentReportUtil.logFail("Scenario Gagal: " + scenario.getName());
            ExtentReportUtil.logWithScreenshot("Failed - " + scenario.getName(), driver);
        } else {
            ExtentReportUtil.logPass("Scenario Berhasil: " + scenario.getName());
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
