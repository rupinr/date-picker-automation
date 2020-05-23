package config;

import capabality.Capability;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationHolder {

    private final static ConfigurationHolder configurationHolder = new ConfigurationHolder();
    private final Properties properties= new Properties();

    public Config config ;

    public DesiredCapabilities desiredCapabilities;

    private ConfigurationHolder(){
        try {
            properties.load((new FileInputStream("test.properties")));
            populateConfig();
            populateDesiredCapabilities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateConfig() {
        config = new Config(OS.valueOf(properties.getProperty("os").toUpperCase()),
                Version.valueOf(properties.getProperty("version")));
    }

    private void populateDesiredCapabilities() {
            switch (properties.getProperty("os")) {
                case "Android": {
                    desiredCapabilities = Capability.getAndroidCapabilities(
                            properties.getProperty("appPackage"),
                            properties.getProperty("appActivity"),
                            properties.getProperty("app"),
                            properties.getProperty("deviceName")
                    );
                    break;
                }
                case "iOS": {
                    desiredCapabilities = Capability.getIOSCapabilities(
                            properties.getProperty("app"),
                            properties.getProperty("automationName"),
                            properties.getProperty("deviceName")
                    );
                    break;
                }
            }

    }

    public static ConfigurationHolder INSTANCE = configurationHolder ;

}
