package com.selfhealing.config;

import com.selfhealing.ai.config.AIConfig;

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
     * AI configuration.
     */
    private AIConfig aiConfig;

    /**
     * Creates a configuration with sensible defaults.
     */
    public HealingConfig() {

        this.similarityThreshold = 0.80;

        this.maxCandidates = 5;

        this.loggingEnabled = true;

        this.aiConfig = new AIConfig();
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

    public AIConfig getAiConfig() {
        return aiConfig;
    }

    public void setAiConfig(AIConfig aiConfig) {
        this.aiConfig = aiConfig;
    }

    public boolean isAIEnabled() {
        return aiConfig != null && aiConfig.isEnabled();
    }

    @Override
    public String toString() {
        return "HealingConfig{" +
                "similarityThreshold=" + similarityThreshold +
                ", maxCandidates=" + maxCandidates +
                ", loggingEnabled=" + loggingEnabled +
                ", aiConfig=" + aiConfig +
                '}';
    }
}