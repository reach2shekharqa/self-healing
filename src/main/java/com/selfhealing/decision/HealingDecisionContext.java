package com.selfhealing.decision;

import com.selfhealing.model.ElementFingerprint;

import java.util.List;


public class HealingDecisionContext {


    private final String failedLocator;

    private final String currentUrl;

    private final String pageTitle;

    private final String exceptionType;

    private final String exceptionMessage;

    private final boolean localHealingFailed;

    private final double bestSimilarityScore;

    private final String frameworkVersion;

    private final List<ElementFingerprint> candidateElements;



    public HealingDecisionContext(
            String failedLocator,
            String currentUrl,
            String pageTitle,
            String exceptionType,
            String exceptionMessage,
            boolean localHealingFailed,
            double bestSimilarityScore,
            String frameworkVersion,
            List<ElementFingerprint> candidateElements) {


        this.failedLocator = failedLocator;

        this.currentUrl = currentUrl;

        this.pageTitle = pageTitle;

        this.exceptionType = exceptionType;

        this.exceptionMessage = exceptionMessage;

        this.localHealingFailed = localHealingFailed;

        this.bestSimilarityScore = bestSimilarityScore;

        this.frameworkVersion = frameworkVersion;

        this.candidateElements = candidateElements;
    }





    public String getFailedLocator() {

        return failedLocator;
    }



    public String getCurrentUrl() {

        return currentUrl;
    }



    public String getPageTitle() {

        return pageTitle;
    }



    public String getExceptionType() {

        return exceptionType;
    }



    public String getExceptionMessage() {

        return exceptionMessage;
    }



    public boolean isLocalHealingFailed() {

        return localHealingFailed;
    }



    public double getBestSimilarityScore() {

        return bestSimilarityScore;
    }



    public String getFrameworkVersion() {

        return frameworkVersion;
    }



    public List<ElementFingerprint> getCandidateElements() {

        return candidateElements;
    }





    @Override
    public String toString() {

        return "HealingDecisionContext{" +

                "failedLocator='" + failedLocator + '\'' +

                ", currentUrl='" + currentUrl + '\'' +

                ", pageTitle='" + pageTitle + '\'' +

                ", exceptionType='" + exceptionType + '\'' +

                ", exceptionMessage='" + exceptionMessage + '\'' +

                ", localHealingFailed=" + localHealingFailed +

                ", bestSimilarityScore=" + bestSimilarityScore +

                ", frameworkVersion='" + frameworkVersion + '\'' +

                ", candidateElements=" + candidateElements +

                '}';
    }

}