package com.selfhealing.driver;

import com.selfhealing.healing.SelfHealingEngine;
import com.selfhealing.model.HealingContext;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class HealingWebDriver implements WebDriver {

    private final WebDriver driver;
    private final SelfHealingEngine healingEngine;

    public HealingWebDriver(
            WebDriver driver,
            SelfHealingEngine healingEngine) {

        this.driver = driver;
        this.healingEngine = healingEngine;
    }

    @Override
    public void get(String url) {
        driver.get(url);
    }

    @Override
    public WebElement findElement(By locator) {

        try {

            return driver.findElement(locator);

        } catch (Exception ex) {

            System.out.println("Locator failed: " + locator);
            System.out.println("Starting self healing...");

            HealingContext context = new HealingContext(
                    driver,
                    locator,
                    driver.getCurrentUrl(),
                    driver.getPageSource(),
                    ex
            );

            return healingEngine.heal(context);
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public @Nullable String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public @Nullable String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public @Nullable String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }
}