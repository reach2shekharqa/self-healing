package com.selfhealing.driver;

import com.selfhealing.healing.SelfHealingEngine;
import com.selfhealing.model.HealingContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HealingWebDriver {

    private final WebDriver driver;
    private final SelfHealingEngine healingEngine;

    public HealingWebDriver(WebDriver driver) {
        this.driver = driver;
        this.healingEngine = new SelfHealingEngine();
    }

    public void get(String url) {
        driver.get(url);
    }

    public WebElement findElement(By locator) {

        try {
            return driver.findElement(locator);

        } catch (Exception ex) {

            HealingContext context = new HealingContext(
                    locator,
                    driver.getCurrentUrl(),
                    driver.getPageSource(),
                    ex
            );

            return healingEngine.heal(context);
        }
    }

    public void quit() {
        driver.quit();
    }
}