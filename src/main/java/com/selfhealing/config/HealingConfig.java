package com.selfhealing.config;

public class HealingConfig {

    /**
     * Minimum similarity score required to accept a healed locator.
     */
    private double similarityThreshold;

    /**
     * Maximum number of candidate elements to evaluate during healing.
     */
    private int maxCandidates;

    /**
     * Enable or disable framework logging.
     */
    private boolean loggingEnabled;

    /**
     * Creates a configuration with sensible defaults.
     */
    public HealingConfig() {
        this.similarityThreshold = 0.80;
        this.maxCandidates = 5;
        this.loggingEnabled = true;
    }

    public double getSimilarityThreshold() {
        return similarityThreshold;
    }

    public void setSimilarityThreshold(double similarityThreshold) {
        this.similarityThreshold = similarityThreshold;
    }

    public int getMaxCandidates() {
        return maxCandidates;
    }

    public void setMaxCandidates(int maxCandidates) {
        this.maxCandidates = maxCandidates;
    }

    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    public void setLoggingEnabled(boolean loggingEnabled) {
        this.loggingEnabled = loggingEnabled;
    }
}