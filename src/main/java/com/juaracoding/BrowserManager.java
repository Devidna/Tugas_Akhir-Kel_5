//package com.juaracoding;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//public class BrowserManager {
//    public static WebDriver build() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//
//        return new ChromeDriver(options);
//    }
//}
//package com.juaracoding;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//
//public class BrowserManager {
//    public static WebDriver build() {
//        EdgeDriver options = new FirefoxOptions();
//        options.addArguments("--start-maximized");
//
//        return new FirefoxDriver(options);
//    }
//}

package com.juaracoding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserManager {
    public static WebDriver build() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");

        return new FirefoxDriver(options);
    }
}
