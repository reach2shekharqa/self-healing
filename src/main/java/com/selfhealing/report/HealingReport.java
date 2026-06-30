package com.selfhealing.report;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HealingReport {

    private String testName;
    private String failedLocator;
    private String healedLocator;
    private String locatorType;
    private double confidence;
    private String healingSource;
    private String exception;
    private String url;
    private String healingStatus;
    private String testResult;
    private String timestamp;

    public HealingReport() {

        this.timestamp = LocalDateTime.now()
                .format(
                        DateTimeFormatter.ofPattern(
                                "yyyy-MM-dd HH:mm:ss"));

        this.testName = detectTestName();
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getFailedLocator() {
        return failedLocator;
    }

    public void setFailedLocator(String failedLocator) {
        this.failedLocator = failedLocator;
    }

    public String getHealedLocator() {
        return healedLocator;
    }

    public void setHealedLocator(String healedLocator) {
        this.healedLocator = healedLocator;
    }

    public String getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getHealingSource() {
        return healingSource;
    }

    public void setHealingSource(String healingSource) {
        this.healingSource = healingSource;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHealingStatus() {
        return healingStatus;
    }

    public void setHealingStatus(String healingStatus) {
        this.healingStatus = healingStatus;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private String detectTestName() {

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        for (StackTraceElement element : stack) {

            String className = element.getClassName();

            if (className.contains("test")
                    || className.contains("Test")) {

                return className.substring(
                        className.lastIndexOf(".") + 1);
            }
        }

        return "UnknownTest";
    }

    @Override
    public String toString() {
        return "HealingReport{" +
                "testName='" + testName + '\'' +
                ", failedLocator='" + failedLocator + '\'' +
                ", healedLocator='" + healedLocator + '\'' +
                ", confidence=" + confidence +
                ", healingSource='" + healingSource + '\'' +
                ", healingStatus='" + healingStatus + '\'' +
                ", testResult='" + testResult + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}