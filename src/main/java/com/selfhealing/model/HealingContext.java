package com.selfhealing.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HealingContext {


    private final WebDriver driver;

    private final By failedLocator;

    private final String url;

    private final String html;

    private final Exception exception;



    public HealingContext(
            WebDriver driver,
            By failedLocator,
            String url,
            String html,
            Exception exception
    ) {

        this.driver = driver;
        this.failedLocator = failedLocator;
        this.url = url;
        this.html = html;
        this.exception = exception;

    }



    public WebDriver getDriver() {

        return driver;
    }



    public By getFailedLocator() {

        return failedLocator;
    }



    public String getUrl() {

        return url;
    }



    public String getHtml() {

        return html;
    }



    public Exception getException() {

        return exception;
    }
}