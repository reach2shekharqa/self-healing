package com.selfhealing.driver;

import com.selfhealing.config.HealingConfig;
import com.selfhealing.factory.HealingFactory;
import org.openqa.selenium.WebDriver;

public final class HealingDriver {

    private HealingDriver() {
    }

    public static WebDriver create(WebDriver driver) {
        return create(driver, new HealingConfig());
    }

    public static WebDriver create(WebDriver driver,
            HealingConfig config) {

        return new HealingWebDriver(
                driver,
                HealingFactory.createEngine(config));
    }
}