package com.selfhealing.driver;


import com.selfhealing.model.HealingContext;
import com.selfhealing.healing.SelfHealingEngine;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class HealingWebDriver {


    private final WebDriver driver;

    private final SelfHealingEngine healingEngine;



    public HealingWebDriver(
            WebDriver driver,
            SelfHealingEngine healingEngine
    ) {

        this.driver = driver;

        this.healingEngine = healingEngine;
    }




    public void get(String url) {

        driver.get(url);

    }




    public WebElement findElement(
            By locator
    ) {


        try {


            return driver.findElement(
                    locator
            );


        } catch (Exception ex) {



            System.out.println(
                    "Locator failed: "
                    + locator
            );



            HealingContext context =
                    new HealingContext(

                            driver,

                            locator,

                            driver.getCurrentUrl(),

                            driver.getPageSource(),

                            ex

                    );



            return healingEngine.heal(
                    context
            );

        }

    }




    public void quit() {

        driver.quit();

    }


}