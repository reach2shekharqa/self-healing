package com.selfhealing.ai.model;


public class HealingDecision {


    private boolean success;

    private String strategy;

    private String value;

    private double confidence;

    private String reason;


    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getStrategy() {
        return strategy;
    }


    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
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
}