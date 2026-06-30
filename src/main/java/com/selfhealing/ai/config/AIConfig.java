package com.selfhealing.ai.config;

public class AIConfig {

    private boolean enabled;

    private AIProvider provider;

    private String apiKey;

    private String model;

    private String baseUrl;

    public AIConfig() {

        this.enabled = false;

    }

    public boolean isEnabled() {

        return enabled;

    }

    public void setEnabled(boolean enabled) {

        this.enabled = enabled;

    }

    public AIProvider getProvider() {

        return provider;

    }

    public void setProvider(AIProvider provider) {

        this.provider = provider;

    }

    public String getApiKey() {

        return apiKey;

    }

    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;

    }

    public String getModel() {

        return model;

    }

    public void setModel(String model) {

        this.model = model;

    }

    public String getBaseUrl() {

        return baseUrl;

    }

    public void setBaseUrl(String baseUrl) {

        this.baseUrl = baseUrl;

    }

    @Override
    public String toString() {

        return "AIConfig{" +
                "enabled=" + enabled +
                ", provider=" + provider +
                ", model='" + model + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                '}';

    }

}
