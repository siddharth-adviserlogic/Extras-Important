package com.adviserlogic.automation.common.config;

import com.adviserlogic.automation.common.utils.Helper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kumar.nipun on 5/17/2018
 */
public class Configuration {
    private static final Logger logger = Logger.getLogger(Configuration.class);
    private Map<String, String> properties = new HashMap<>();

    /**
     * Gets the Object of Configuration
     */
    public Configuration(){
        loadProperties("properties.yaml");
    }

    /**
     * Loads the properties file
     * @param resource resource file path
     */
    private void loadProperties(String resource) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
            properties = (Map<String, String>) new Yaml().load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            logger.debug("Failed to load properties file due to: " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Gets the property value
     * @param key property name
     * @return property value as String
     */
    public String get(String key){
        return Helper.isPropertySpecifiedAtOsLevel(key) ? System.getProperty(key) : properties.get(key);
    }
}
