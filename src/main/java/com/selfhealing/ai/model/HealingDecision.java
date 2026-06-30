package com.selfhealing.ai.model;

public class HealingDecision {

    /**
     * Whether AI successfully found a locator.
     */
    private boolean success;

    /**
     * Suggested Selenium locator.
     */
    private String locator;

    /**
     * Confidence score.
     */
    private double confidence;

    /**
     * Reason for the decision.
     */
    private String reason;

    public HealingDecision() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {

        return "HealingDecision{" +
                "success=" + success +
                ", locator='" + locator + '\'' +
                ", confidence=" + confidence +
                ", reason='" + reason + '\'' +
                '}';
    }

}