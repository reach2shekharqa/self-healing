package com.selfhealing.ai.config;


public final class AIConfigBuilder {


    private AIConfigBuilder() {

    }



    public static AIConfig build() {


        /*
         * Load configuration from
         * consumer project resources.
         *
         * Example:
         * src/test/resources/healing.properties
         *
         */
        return AIConfigLoader.load();

    }

}