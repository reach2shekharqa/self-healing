package com.selfhealing.driver;

import com.selfhealing.model.HealingContext;
import com.selfhealing.healing.SelfHealingEngine;

import java.util.List;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HealingWebDriver implements WebDriver {

    private final WebDriver driver;

    private final SelfHealingEngine healingEngine;

    public HealingWebDriver(
            WebDriver driver,
            SelfHealingEngine healingEngine) {

        this.driver = driver;

        this.healingEngine = healingEngine;
    }

    public void get(String url) {

        driver.get(url);

    }

    public WebElement findElement(
            By locator) {

        try {

            return driver.findElement(
                    locator);

        } catch (Exception ex) {

            System.out.println(
                    "Locator failed: "
                            + locator);

            HealingContext context = new HealingContext(

                    driver,

                    locator,

                    driver.getCurrentUrl(),

                    driver.getPageSource(),

                    ex

            );

            return healingEngine.heal(
                    context);

        }

    }

    public void quit() {

        driver.quit();

    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

    @Override
    public List<WebElement> findElements(By by) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findElements'");
    }

    @Override
    public @Nullable String getCurrentUrl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentUrl'");
    }

    @Override
    public @Nullable String getPageSource() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPageSource'");
    }

    @Override
    public @Nullable String getTitle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTitle'");
    }

    @Override
    public String getWindowHandle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWindowHandle'");
    }

    @Override
    public Set<String> getWindowHandles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWindowHandles'");
    }

    @Override
    public Options manage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'manage'");
    }

    @Override
    public Navigation navigate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'navigate'");
    }

    @Override
    public TargetLocator switchTo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'switchTo'");
    }

}