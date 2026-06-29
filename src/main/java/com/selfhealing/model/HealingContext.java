package com.selfhealing.model;

import org.openqa.selenium.By;

public class HealingContext {

    private final By failedLocator;
    private final String currentUrl;
    private final String pageSource;
    private final Exception exception;

    public HealingContext(By failedLocator,
            String currentUrl,
            String pageSource,
            Exception exception) {

        this.failedLocator = failedLocator;
        this.currentUrl = currentUrl;
        this.pageSource = pageSource;
        this.exception = exception;
    }

    public By getFailedLocator() {
        return failedLocator;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public String getPageSource() {
        return pageSource;
    }

    public Exception getException() {
        return exception;
    }

}
