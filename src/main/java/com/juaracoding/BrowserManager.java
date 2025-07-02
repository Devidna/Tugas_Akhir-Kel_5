package com.juaracoding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserManager {
    public static WebDriver build() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
//      options.addArguments("--start-minimized"); // Start minimized (may not work on all Edge versions)
//      options.addArguments("--headless=new"); // Uncomment this line to run in headless mode

        return new FirefoxDriver(options);
    }
}
