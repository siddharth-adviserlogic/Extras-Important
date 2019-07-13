package com.adviserlogic.automation.common.utils;

import java.util.List;

/**
 * Created by kumar.nipun on 5/17/2018
 */
public class ListUtil {
    /**
     * Gets the random element from the list (Generic)
     * @param list collection list
     * @param <T> collection type
     * @return random element of type T
     */
    public static <T> T getRandomListElement(List<T> list) {
        T element = null;
        if (!list.isEmpty()) {
            int index = Helper.getRandomNumber(list.size());
            element = list.get(index);
        }
        return element;
    }

}
