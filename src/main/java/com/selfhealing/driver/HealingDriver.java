package com.selfhealing.driver;

import com.selfhealing.ai.config.AIConfig;
import com.selfhealing.config.HealingConfig;
import com.selfhealing.factory.HealingFactory;

import org.openqa.selenium.WebDriver;


public final class HealingDriver {


    private HealingDriver() {

    }



    /**
     * Creates self-healing driver
     * without AI.
     */
    public static WebDriver create(
            WebDriver driver) {


        return create(
                driver,
                new HealingConfig(),
                null
        );
    }




    /**
     * Creates self-healing driver
     * with custom healing configuration
     * without AI.
     */
    public static WebDriver create(
            WebDriver driver,
            HealingConfig healingConfig) {


        return create(
                driver,
                healingConfig,
                null
        );
    }




    /**
     * Creates self-healing driver
     * with optional AI configuration.
     */
    public static WebDriver create(
            WebDriver driver,
            HealingConfig healingConfig,
            AIConfig aiConfig) {


        return new HealingWebDriver(

                driver,

                HealingFactory.createEngine(
                        healingConfig,
                        aiConfig
                )
        );
    }
}