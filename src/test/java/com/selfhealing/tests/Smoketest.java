package com.selfhealing.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selfhealing.config.HealingConfig;
import com.selfhealing.driver.HealingWebDriver;
import com.selfhealing.factory.HealingFactory;
import com.selfhealing.healing.SelfHealingEngine;

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

        HealingConfig config = new HealingConfig();

        SelfHealingEngine engine = HealingFactory.createEngine(config);

        driver = new HealingWebDriver(
                seleniumDriver,
                engine);

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    public void verifyProjectWorks() {

        driver.get(
                "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        driver.findElement(
                By.xpath("//input[@name='username']")).sendKeys("Admin");

        driver.findElement(
                By.xpath("//input[@name='passw']")).sendKeys("admin123");

        driver.findElement(
                By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"))
                .click();

    }

}