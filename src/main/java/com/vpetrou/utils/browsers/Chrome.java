package com.vpetrou.utils.browsers;

import com.vpetrou.utils.config.EnvDataConfig;
import com.vpetrou.utils.config.ResourcesConfig;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Chrome {

    ResourcesConfig resourcesConfig = new ResourcesConfig();
    EnvDataConfig envDataConfig = new EnvDataConfig();

    public WebDriver start() {
        String remoteURL = envDataConfig.getRemoteURL();
        if (!remoteURL.isEmpty()) {
            try {
                RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteURL), getChromeOptions());
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return new ChromeDriver(getChromeOptions());
        }
    }

    private ChromeOptions getChromeOptions() {
        System.setProperty("browser", "chrome");
        System.setProperty("webdriver.chrome.driver", resourcesConfig.getChromeDriver());
//        WebDriverManager.chromedriver().setup();

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", resourcesConfig.getOutputDir().replace("/", "\\"));
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.directory_upgrade", "true");
        chromePrefs.put("download.prompt_for_download", "false");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("lang=en");
        if (envDataConfig.getHeadlessMode()) {
            options.addArguments("headless");
        }
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--ignore-certificate-errors"); //Suppressed Certificate errors
        options.setCapability(ChromeOptions.CAPABILITY, options);
        options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        options.setCapability("chrome.switches", Arrays.asList("incognito"));
        return options;
    }
}
