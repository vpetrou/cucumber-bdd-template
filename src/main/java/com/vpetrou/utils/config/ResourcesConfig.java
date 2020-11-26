package com.vpetrou.utils.config;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

public class ResourcesConfig {

    public String getChromeDriver() {
        return getTestResourcesPath() + "/drivers/chromedriver.exe";
    }

    public String getInternetExplorerDriver() {
        return getTestResourcesPath() + "/drivers/IEDriverServer.exe";
    }

    public String getFirefoxDriver() {
        return getTestResourcesPath() + "/drivers/geckodriver.exe";
    }

    public String getEdgeDriver() {
        return getTestResourcesPath() + "/drivers/msedgedriver.exe";
    }

    public String getOutputDir() {
        return getTestResourcesPath() + "/test-data/outputDir";
    }

    public String getInputDir() {
        return getTestResourcesPath() + "/test-data/inputDir";
    }

    private static Logger logger = LoggerFactory.getLogger(ResourcesConfig.class);

    public String getEnvironmentProperties() {
        if (System.getProperty("env.properties") == null)
            return getResourcesPath() + "/env.properties";
        else
            return getAbsolutePath() + System.getProperty("env.properties");
    }

    private String getTestResourcesPath() {
        return getResourcesPath("test");
    }

    private String getResourcesPath() {
        return getResourcesPath("main");
    }

    private String getResourcesPath(String packageName) {
        String filePathString = getAbsolutePath() + "src/" + packageName + "/resources";
        File f = new File(filePathString);
        if (!f.exists())
            filePathString = getAbsolutePath();
        return filePathString;
    }

    public String getAbsolutePath() {
        String absPath = Paths.get(".")
                .toAbsolutePath().normalize().toString().replace("\\", "/") ;

        String modulePath = this.getClass().getClassLoader().getResource(".").getPath();
        modulePath = modulePath.replace("\\", "/") ;
        modulePath = modulePath.replace("/target/test-classes", "");
        modulePath = modulePath.replace("/target/classes", "");
        modulePath = modulePath.replace(absPath, "");
        modulePath = modulePath.replace("//", "/");

        return absPath + modulePath;
    }

}
