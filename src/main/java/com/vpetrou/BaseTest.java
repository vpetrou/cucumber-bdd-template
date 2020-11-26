package com.vpetrou;

import com.vpetrou.pages.Page;
import com.vpetrou.utils.browsers.Chrome;
import com.vpetrou.utils.browsers.Edge;
import com.vpetrou.utils.browsers.Firefox;
import com.vpetrou.utils.browsers.InternetExplorer;
import com.vpetrou.utils.common.Elements;
import com.vpetrou.utils.config.EnvDataConfig;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Elements element;
    public static Page page;

    public EnvDataConfig envDataConfig = new EnvDataConfig();

    public static int index;

    private final Duration timeout = Duration.ofSeconds(Long.parseLong(envDataConfig.getTimeout()));

    public void openBrowser() {

        switch (envDataConfig.getBrowser()) {
            case "chrome": {
                Chrome chrome = new Chrome();
                driver = chrome.start();
                break;
            }
            case "firefox": {
                Firefox firefox = new Firefox();
                driver = firefox.start();
                break;
            }
            case "edge": {
                Edge edge = new Edge();
                driver = edge.start();
                break;
            }
            case "ie": {
                InternetExplorer internetExplorer = new InternetExplorer();
                driver = internetExplorer.start();
                break;
            }
        }
        assert driver != null;
        wait = new WebDriverWait(driver, timeout);
        element = new Elements();

        page = new Page();
    }

    public void closeBrowser(Scenario scenario) {
        if (scenario.isFailed())
            try {
                File screenshot =
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    scenario.attach(Files.readAllBytes(screenshot.toPath()), "image/png", "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        driver.quit();
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> expectation =
                driver -> {
                    assert driver != null;
                    return ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState").toString().equals("complete");
                };
        try {
            Thread.sleep(500);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(expectation);
            Thread.sleep(500);
            waitForLoadingImage();
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }

    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForElement(WebElement element) {
        if (element != null) {
            wait.until(ExpectedConditions.visibilityOf(element));
        } else {
            System.out.println("ERROR Element is NOT present.");
        }
    }

    // Wait until Loading Image disappears
    public void waitForLoadingImage() {
        if (element.isElementPresent(
                By.xpath("//div[contains(@style,'visibility: visible')]" +
                        "//img[contains(@src,'loader')]"))) {
            long t = System.currentTimeMillis();
            long end = t + (Integer.parseInt(envDataConfig.getTimeout()) * 1000);
            while (System.currentTimeMillis() < end) {
                if (element.isElementPresent(
                        By.xpath("//div[contains(@style,'visibility: hidden')]" +
                                "//img[contains(@src,'loader')]"))) {
                    break;
                }
            }
        }
    }


}
