package com.selfhealing.healing;


import com.selfhealing.matcher.LocatorMatcher;
import com.selfhealing.model.*;
import com.selfhealing.parser.DOMParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SelfHealingEngine {


    private final DOMParser domParser;

    private final LocatorMatcher locatorMatcher;

    private final ElementFingerprintBuilder fingerprintBuilder;



    public SelfHealingEngine(
            DOMParser domParser,
            LocatorMatcher locatorMatcher,
            ElementFingerprintBuilder fingerprintBuilder
    ) {

        this.domParser = domParser;

        this.locatorMatcher = locatorMatcher;

        this.fingerprintBuilder = fingerprintBuilder;
    }




    public WebElement heal(
            HealingContext context
    ) {



        System.out.println(
                "Starting self healing..."
        );



        /*
         * Step 1:
         * Parse current DOM
         */

        DOMSnapshot snapshot =
                domParser.parse(
                        context.getUrl(),
                        context.getHtml()
                );



        /*
         * Step 2:
         * Find candidate elements
         */

        List<ElementSnapshot> candidates =
                locatorMatcher.findCandidates(
                        context.getFailedLocator(),
                        snapshot
                );



        if(candidates.isEmpty()) {

            throw new RuntimeException(
                    "No healing candidates found"
            );
        }



        /*
         * Step 3:
         * Choose best candidate
         */

        ElementSnapshot bestCandidate =
                candidates.get(0);



        /*
         * Step 4:
         * Create AI-ready fingerprint
         */

        ElementFingerprint fingerprint =
                fingerprintBuilder.build(
                        bestCandidate
                );


        System.out.println(
                "Healed Element Fingerprint:"
        );


        System.out.println(
                fingerprint
        );



        /*
         * Step 5:
         * Generate healed locator
         */

        By healedLocator =
                generateLocator(
                        bestCandidate
                );



        System.out.println(
                "Trying healed locator: "
                + healedLocator
        );



        /*
         * Step 6:
         * Retry Selenium action
         */

        return context.getDriver()
                .findElement(
                        healedLocator
                );

    }






    private By generateLocator(
            ElementSnapshot element
    ) {


        /*
         * Priority:
         *
         * 1. id
         * 2. name
         * 3. css class
         *
         */


        if(element.getId()!=null &&
                !element.getId().isEmpty()) {


            return By.id(
                    element.getId()
            );
        }




        if(element.getName()!=null &&
                !element.getName().isEmpty()) {


            return By.name(
                    element.getName()
            );
        }





        if(element.getClassName()!=null &&
                !element.getClassName().isEmpty()) {


            return By.className(
                    element.getClassName()
            );
        }



        throw new RuntimeException(
                "Unable to generate locator"
        );

    }

}