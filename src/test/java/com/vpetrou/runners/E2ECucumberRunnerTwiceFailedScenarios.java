package com.vpetrou.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber_failed_twice.json",
        "junit:target/cucumber-junit-report_failed_twice.xml"},
        features = {"@target/failed_scenarios/failed_twice.txt"},
        tags = "(not @Manual)",
        glue = {"com.vpetrou.stepdefs"},
        monochrome = true)
public class E2ECucumberRunnerTwiceFailedScenarios {

    @BeforeClass
    public static void setup() {
    }

    @AfterClass
    public static void teardown() {
    }
}
