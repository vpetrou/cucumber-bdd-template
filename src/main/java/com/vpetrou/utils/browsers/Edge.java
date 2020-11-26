package com.vpetrou.utils.browsers;

import com.vpetrou.utils.config.EnvDataConfig;
import com.vpetrou.utils.config.ResourcesConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Edge {

    ResourcesConfig resourcesConfig = new ResourcesConfig();
    EnvDataConfig envDataConfig = new EnvDataConfig();

    public WebDriver start() {
        String remoteURL = envDataConfig.getRemoteURL();
        if (!remoteURL.isEmpty()) {
            try {
                RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteURL), getEdgeOptions());
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return new EdgeDriver(getEdgeOptions());
        }
    }


    private EdgeOptions getEdgeOptions() {
        System.setProperty("browser", "edge");
        System.setProperty("webdriver.edge.driver", resourcesConfig.getEdgeDriver());
        EdgeOptions options = new EdgeOptions();
        if (envDataConfig.getHeadlessMode()) {
            options.setCapability("headless", true);
        }
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.setCapability("browser.download.dir", resourcesConfig.getOutputDir());
        options.setCapability("requireWindowFocus", true);
        options.setCapability("enablePersistentHover", false);
        options.setCapability("ignoreProtectedModeSettings", true);
        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        options.setCapability("chrome.switches", Arrays.asList("--incognito"));
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability("requireWindowFocus", true);
        options.setCapability("enablePersistentHover", false);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        return options;
    }

}
