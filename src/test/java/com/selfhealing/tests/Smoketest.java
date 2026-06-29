package com.selfhealing.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.selfhealing.driver.HealingWebDriver;

public class Smoketest {

    @Test
    public void verifyProjectWorks() {

    
        WebDriver seleniumDriver = new ChromeDriver();

        HealingWebDriver driver = new HealingWebDriver(seleniumDriver);

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user_name"));

        driver.quit();

    }
    
}
