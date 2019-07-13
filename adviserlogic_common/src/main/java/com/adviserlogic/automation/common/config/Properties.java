package com.adviserlogic.automation.common.config;

import com.adviserlogic.automation.common.utils.Helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kumar.nipun on 5/17/2018
 */
public class Properties {

    private static ThreadLocal<Configuration> configuration = new ThreadLocal<>();
    private static ThreadLocal<Map<String, String>> variables = new ThreadLocal<>();
    private static List<String> productProviders = new LinkedList<>();

    static {
        variables.set(new HashMap<>());
        configuration.set(new Configuration());
    }

    public static void setVariable(String name, String value) {
        if (variables.get() == null)
            variables.set(new HashMap<>());
        variables.get().put(name, value);
    }

    public static String getVariable(String name) {
        return variables.get().get(name);
    }

    private static Configuration getConfig() {
        if (configuration.get() == null)
            configuration.set(new Configuration());
        return configuration.get();
    }

    public static String getBrowserName() {
        return getConfig().get("browser");
    }

    private static String getDriverPath() {
        return getConfig().get("driver.path");
    }

    public static String getChromeDriverPath() {
        String driverName = Helper.isWindows() ? "chromedriver.exe" : "chromedriver";
        return getDriverPath() + driverName;
    }

    public static String getFirefoxDriverPath() {
        String driverName = Helper.isWindows() ? "geckodriver.exe" : "geckodriver";
        return getDriverPath() + driverName;
    }

    public static String getBaseUrl(){
        return getConfig().get("baseUrl");
    }

    /**
     * Gets username for login
     * @return
     */
    public static String getUsername(){
        return getConfig().get("username");
    }

    /**
     * Gets password for login
     * @return
     */
    public static String getPassword(){
        return getConfig().get("password");
    }

    /**
     * Gets the username for superAdmin
     * @return
     */
    public static String getSuperAdminUsername(){
        return getConfig().get("superAdminUsername");
    }

    /**
     * Gets the password for superAdmin
     * @return
     */
    public static String getSuperAdminPassword(){
        return getConfig().get("superAdminPassword");
    }

    /**
     * Gets the Product Provider from properties
     * @return
     */
    public static String getProductProvider(boolean aia, boolean asteron){
        String productProvider = "";
        if(aia){
            productProvider = getConfig().get("productProvider1");
        }
        else if(asteron){
            productProvider = getConfig().get("productProvider2");
        }
        return productProvider;
    }


    /**
     * Gets the schema name for a specific product provider
     * @param productProviderName
     * @return
     */
    public static String getSchemaName(String productProviderName){
        String schemaName;
        if(productProviderName.equalsIgnoreCase("aia")){
            schemaName = getConfig().get("aiaSchemaName");
        }
        else{
            schemaName = getConfig().get("asteronSchemaName");
        }
        return schemaName;
    }

    /**
     * Gets the file path for file upload
     * @return
     */
    public static String getFilePathForBouncedFlow(){
        return getConfig().get("uploadFilePathForBouncedFlow");
    }

    public static String getFilePathForUnMappedAndProcessPayoutFlow(){
        return getConfig().get("uploadFilePathForUnMappedAndProcessPayoutFlow");
    }

}
