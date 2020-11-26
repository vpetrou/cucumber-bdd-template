package com.vpetrou.utils.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Properties;

public class EnvDataConfig {

    ResourcesConfig resourcesConfig = new ResourcesConfig();

    public String getURL(String applicationName) {
        return getEnvProperties().getProperty(applicationName + ".URL");
    }

    public String getTimeout() { return getEnvProperties().getProperty("TIMEOUT"); }

    public String getInterval() { return getEnvProperties().getProperty("INTERVAL"); }

    public String getBrowser() { return getEnvProperties().getProperty("BROWSER"); }

    public String getRemoteURL() { return getEnvProperties().getProperty("DRIVER.REMOTE.URL"); }

    public Boolean getHeadlessMode() { return Boolean.parseBoolean(getEnvProperties().getProperty("HEADLESS.MODE")); }

    private Properties getEnvProperties() {
        return getProperties(loadProperties(resourcesConfig.getEnvironmentProperties()));
    }

    private static Properties loadProperties(String testDataFile) {
        Properties prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream(testDataFile);
            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            prop.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

    private static Properties getProperties(Properties params) {
        Properties result = new Properties();
        Enumeration<?> names = params.propertyNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            result.put(name, params.get(name));
        }
        return result;
    }

}
