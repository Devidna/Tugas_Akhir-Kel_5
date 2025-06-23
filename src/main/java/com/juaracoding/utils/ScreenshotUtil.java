package com.juaracoding.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String stepName) {
        String folder = "screenshots/";

        String scenarioName = ScenarioContext.getScenarioName();
        if (scenarioName == null || scenarioName.isEmpty()) {
            scenarioName = "UnknownScenario";
        }

        String fileName = scenarioName.replaceAll(" ", "_") + "_" + stepName.replaceAll(" ", "_") + "_" + ".png";
        String relativePath = folder + fileName;
        String fullPath = "target/" + relativePath;

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File dest = new File(fullPath);
            dest.getParentFile().mkdirs();
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relativePath;
    }


}
