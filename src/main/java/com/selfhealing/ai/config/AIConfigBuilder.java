package com.selfhealing.ai.config;


public class AIConfigBuilder {


    public static AIConfig build() {

        AIConfig config = new AIConfig();

        /*
         * Milestone 5.1
         * Default AI provider
         */
        config.setProvider(
                AIProvider.GROQ
        );


        return config;
    }

}