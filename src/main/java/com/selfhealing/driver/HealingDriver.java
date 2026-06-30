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
     * using default configuration.
     */
    public static WebDriver create(
            WebDriver driver) {


        return create(
                driver,
                new HealingConfig(),
                new AIConfig()
        );

    }




    /**
     * Creates self-healing driver
     * using healing configuration.
     */
    public static WebDriver create(
            WebDriver driver,
            HealingConfig healingConfig) {


        return create(
                driver,
                healingConfig,
                new AIConfig()
        );

    }




    /**
     * Creates self-healing driver
     * using healing + AI configuration.
     *
     * Consumer controls AI settings.
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