package com.vpetrou.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber.json", "junit:target/cucumber-junit-report.xml",
        "rerun:target/failed_scenarios/failed_once.txt"},
        features = {"src/test/resources/features/"},
        tags = "(not @Manual)",
        glue = {"com.vpetrou.stepdefs"},
        monochrome = true)
public class E2ECucumberRunner {

    @BeforeClass
    public static void setup() {
    }

    @AfterClass
    public static void teardown() {
    }
}
