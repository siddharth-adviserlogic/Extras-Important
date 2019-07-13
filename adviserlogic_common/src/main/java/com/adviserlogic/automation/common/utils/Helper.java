package com.adviserlogic.automation.common.utils;

import java.util.Random;

/**
 * Created by kumar.nipun on 5/17/2018
 */
public class Helper {

    /**
     * Gets the random number
     * @param numberBound upper bound
     * @return Random integer between 0 (inclusive) and numberBound (exclusive)
     */
    public static int getRandomNumber(int numberBound) {
        return new Random().nextInt(numberBound);
    }

    public static boolean isPropertySpecifiedAtOsLevel(String key) {
        String value = System.getProperty(key);
        return (value != null && !value.isEmpty());
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }
}
