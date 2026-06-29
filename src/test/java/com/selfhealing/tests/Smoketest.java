package com.selfhealing.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selfhealing.driver.HealingWebDriver;
import com.selfhealing.healing.SelfHealingEngine;
import com.selfhealing.matcher.LocatorMatcher;
import com.selfhealing.model.ElementFingerprintBuilder;
import com.selfhealing.parser.DOMParser;

public class Smoketest {

    private HealingWebDriver driver;

    @BeforeMethod
    public void setup() {

        WebDriver seleniumDriver = new ChromeDriver();

        seleniumDriver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));

        seleniumDriver.manage()
                .window()
                .maximize();

        DOMParser parser = new DOMParser();

        LocatorMatcher matcher = new LocatorMatcher();

        ElementFingerprintBuilder builder = new ElementFingerprintBuilder();

        SelfHealingEngine engine = new SelfHealingEngine(
                parser,
                matcher,
                builder);

        driver = new HealingWebDriver(
                seleniumDriver,
                engine);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        driver.quit();

    }

    @Test
    public void verifyProjectWorks() {

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password565")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed());

        // driver.quit();

    }

}
