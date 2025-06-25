package com.juaracoding.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {
        "src/test/resources/features"
}, plugin = { "pretty:target/pretty.txt",
        "html:target/cucumber-reports/index.html",
        "json:target/cucumber-reports/index.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
}, glue = {
        "com.juaracoding.hooks",
        "com.juaracoding.steps.laporanSemua",
        "com.juaracoding.steps.loginLogout",

})
public class Runner extends AbstractTestNGCucumberTests {

}


