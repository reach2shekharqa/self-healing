package com.selfhealing.ai.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class AIConfigLoader {


    private static final String CONFIG_FILE =
            "healing.properties";



    private AIConfigLoader() {

    }




    public static AIConfig load() {


        Properties properties =
                new Properties();



        try(InputStream input =
                AIConfigLoader.class
                        .getClassLoader()
                        .getResourceAsStream(CONFIG_FILE)) {


            if(input != null) {

                properties.load(input);

            }

        }
        catch(IOException e) {

            throw new RuntimeException(
                    "Unable to load AI configuration",
                    e
            );

        }




        AIConfig config =
                new AIConfig();




        /*
         * AI enabled
         */
        config.setEnabled(
                Boolean.parseBoolean(
                        properties.getProperty(
                                "healing.ai.enabled",
                                "false"
                        )
                )
        );



        /*
         * Provider
         */
        String provider =
                properties.getProperty(
                        "healing.ai.provider",
                        "GROQ"
                );



        config.setProvider(
                AIProvider.valueOf(
                        provider.toUpperCase()
                )
        );



        /*
         * API Key
         */
        config.setApiKey(
                resolveValue(
                        properties.getProperty(
                                "healing.ai.apiKey"
                        )
                )
        );



        /*
         * Model
         */
        config.setModel(
                properties.getProperty(
                        "healing.ai.model"
                )
        );



        /*
         * Base URL
         */
        config.setBaseUrl(
                properties.getProperty(
                        "healing.ai.baseUrl"
                )
        );



        return config;
    }





    /**
     * Supports:
     *
     * healing.ai.apiKey=${GROQ_API_KEY}
     *
     */
    private static String resolveValue(
            String value) {


        if(value == null) {

            return null;
        }



        if(value.startsWith("${")
                && value.endsWith("}")) {


            String envName =
                    value.substring(
                            2,
                            value.length() - 1
                    );


            return System.getenv(
                    envName
            );
        }


        return value;
    }

}