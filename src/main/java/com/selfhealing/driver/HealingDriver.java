package com.selfhealing.driver;

import com.selfhealing.config.HealingConfig;
import com.selfhealing.factory.HealingFactory;
import org.openqa.selenium.WebDriver;

public final class HealingDriver {

    private HealingDriver() {
        // Utility class
    }

    /**
     * Creates a self-healing WebDriver using default configuration.
     *
     * @param driver Original Selenium WebDriver
     * @return Healing-enabled WebDriver
     */
    public static WebDriver create(WebDriver driver) {

        return create(driver, new HealingConfig());

    }

    /**
     * Creates a self-healing WebDriver using custom configuration.
     *
     * @param driver Original Selenium WebDriver
     * @param config Healing framework configuration
     * @return Healing-enabled WebDriver
     */
    public static WebDriver create(
            WebDriver driver,
            HealingConfig config) {

        return new HealingWebDriver(
                driver,
                HealingFactory.createEngine(config)
        );

    }

}