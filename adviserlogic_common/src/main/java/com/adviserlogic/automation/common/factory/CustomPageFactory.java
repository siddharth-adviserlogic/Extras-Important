package com.adviserlogic.automation.common.factory;

import com.adviserlogic.automation.common.pages.BasePage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by kumar.nipun on 5/21/2018
 */


public class CustomPageFactory {

    public static <T extends BasePage> T getPage(WebDriver driver, Class<T> pageClassToProxy) {
        T page = instantiatePageOrComponent(driver, pageClassToProxy);
        PageFactory.initElements(driver, page);
        return page;
    }

    public static <T> T getComponent(WebDriver driver, Class<T> pageClassToProxy) {
        return getComponent(driver, null, pageClassToProxy);
    }

    public static <T> T getComponent(WebDriver driver, WebElement webElement, Class<T> pageClassToProxy) {
        T component;
        if (webElement == null) {
            component = instantiatePageOrComponent(driver, pageClassToProxy);
            PageFactory.initElements(driver, component);
            return component;
        } else {
            component = instantiateComponent(driver, webElement, pageClassToProxy);
            PageFactory.initElements(new DefaultElementLocatorFactory(webElement), component);
            return component;
        }
    }

    private static <T> T instantiatePageOrComponent(SearchContext searchContext, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor[] constructors = pageClassToProxy.getConstructors();
                for(Constructor constructor : constructors ) {
                    return (1 == constructor.getParameterCount()) ?
                        (T)constructor.newInstance(searchContext) :
                        (T)constructor.newInstance();
                }
                return (T)pageClassToProxy.getConstructor().newInstance();
            } catch (NoSuchMethodException noSuchElementException) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException instantiationException) {
            throw new RuntimeException(instantiationException);
        }
    }

    private static <T> T instantiateComponent(SearchContext searchContext, WebElement webElement, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor[] constructors = pageClassToProxy.getConstructors();
                for(Constructor constructor : constructors ) {
                    T component;
                    if(1 == constructor.getParameterCount()) {
                        component = (T)constructor.newInstance(searchContext);
                    } else if (2 == constructor.getParameterCount()) {
                        component = (T)constructor.newInstance(searchContext, webElement);
                    } else {
                        component = (T)constructor.newInstance();
                    }
                    return component;
                }
                return (T)pageClassToProxy.getConstructor().newInstance();
            } catch (NoSuchMethodException noSuchElementException) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException instantiationException) {
            throw new RuntimeException(instantiationException);
        }
    }
}
