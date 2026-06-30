package com.selfhealing.healing;

import com.selfhealing.ai.agent.AIHealingAgent;
import com.selfhealing.decision.DecisionContextBuilder;
import com.selfhealing.decision.HealingDecisionContext;
import com.selfhealing.matcher.LocatorMatcher;
import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementFingerprint;
import com.selfhealing.model.ElementFingerprintBuilder;
import com.selfhealing.model.ElementSnapshot;
import com.selfhealing.model.HealingContext;
import com.selfhealing.parser.DOMParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SelfHealingEngine {


    private final DOMParser domParser;

    private final LocatorMatcher locatorMatcher;

    private final ElementFingerprintBuilder fingerprintBuilder;

    private final DecisionContextBuilder decisionContextBuilder;

    private final AIHealingAgent aiHealingAgent;



    public SelfHealingEngine(
            DOMParser domParser,
            LocatorMatcher locatorMatcher,
            ElementFingerprintBuilder fingerprintBuilder,
            DecisionContextBuilder decisionContextBuilder,
            AIHealingAgent aiHealingAgent) {

        this.domParser = domParser;
        this.locatorMatcher = locatorMatcher;
        this.fingerprintBuilder = fingerprintBuilder;
        this.decisionContextBuilder = decisionContextBuilder;
        this.aiHealingAgent = aiHealingAgent;
    }



    public WebElement heal(
            HealingContext context) {


        System.out.println(
                "Starting self healing...");



        /*
         * Step 1:
         * Parse current DOM
         */

        DOMSnapshot snapshot =
                domParser.parse(
                        context.getUrl(),
                        context.getHtml());



        /*
         * Step 2:
         * Find candidate elements
         */

        List<ElementSnapshot> candidates =
                locatorMatcher.findCandidates(
                        context.getFailedLocator(),
                        snapshot);



        /*
         * Step 3:
         * AI fallback when no candidates found
         */

        if (candidates.isEmpty()) {


            HealingDecisionContext decisionContext =
                    decisionContextBuilder.build(
                            context,
                            snapshot);



            System.out.println(
                    "\n========== AI Decision Context ==========");

            System.out.println(
                    decisionContext);

            System.out.println(
                    "=========================================\n");



            String aiResponse =
                    aiHealingAgent.heal(
                            decisionContext);



            System.out.println(
                    "\n========== AI Response ==========");

            System.out.println(
                    aiResponse);

            System.out.println(
                    "=================================\n");



            throw new RuntimeException(
                    "AI healing response received but execution not implemented yet");
        }



        /*
         * Step 4:
         * Choose best candidate
         */

        ElementSnapshot bestCandidate =
                candidates.get(0);



        /*
         * Step 5:
         * Build fingerprint
         */

        ElementFingerprint fingerprint =
                fingerprintBuilder.build(
                        bestCandidate);



        System.out.println(
                "Healed Element Fingerprint:");

        System.out.println(
                fingerprint);



        /*
         * Step 6:
         * Generate healed locator
         */

        By healedLocator =
                generateLocator(
                        bestCandidate);



        System.out.println(
                "Trying healed locator: "
                        + healedLocator);



        /*
         * Step 7:
         * Retry Selenium action
         */

        return context.getDriver()
                .findElement(
                        healedLocator);
    }




    private By generateLocator(
            ElementSnapshot element) {


        /*
         * Priority:
         *
         * 1. id
         * 2. name
         * 3. class
         */


        if (element.getId() != null &&
                !element.getId().isEmpty()) {

            return By.id(
                    element.getId());
        }



        if (element.getName() != null &&
                !element.getName().isEmpty()) {

            return By.name(
                    element.getName());
        }



        if (element.getClassName() != null &&
                !element.getClassName().isEmpty()) {

            return By.className(
                    element.getClassName());
        }



        throw new RuntimeException(
                "Unable to generate locator");
    }

}