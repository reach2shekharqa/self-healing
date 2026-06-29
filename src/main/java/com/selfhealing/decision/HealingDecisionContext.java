package com.selfhealing.decision;

import com.selfhealing.model.ElementFingerprint;

import java.util.ArrayList;
import java.util.List;

public class HealingDecisionContext {

    private String failedLocator;

    private String currentUrl;

    private String pageTitle;

    private String exceptionType;

    private List<ElementFingerprint> candidateElements = new ArrayList<>();


    public String getFailedLocator() {
        return failedLocator;
    }

    public void setFailedLocator(String failedLocator) {
        this.failedLocator = failedLocator;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public List<ElementFingerprint> getCandidateElements() {
        return candidateElements;
    }

    public void setCandidateElements(List<ElementFingerprint> candidateElements) {
        this.candidateElements = candidateElements;
    }

    @Override
    public String toString() {
        return "HealingDecisionContext{" +
                "\nfailedLocator='" + failedLocator + '\'' +
                ",\ncurrentUrl='" + currentUrl + '\'' +
                ",\npageTitle='" + pageTitle + '\'' +
                ",\nexceptionType='" + exceptionType + '\'' +
                ",\ncandidateElements=" + candidateElements +
                "\n}";
    }
}