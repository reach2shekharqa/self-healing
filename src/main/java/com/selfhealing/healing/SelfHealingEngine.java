package com.selfhealing.healing;

import com.selfhealing.ai.agent.AIHealingAgent;
import com.selfhealing.ai.model.HealingDecision;
import com.selfhealing.ai.parser.AIResponseParser;
import com.selfhealing.decision.DecisionContextBuilder;
import com.selfhealing.decision.HealingDecisionContext;
import com.selfhealing.matcher.LocatorMatcher;
import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementFingerprint;
import com.selfhealing.model.ElementFingerprintBuilder;
import com.selfhealing.model.ElementSnapshot;
import com.selfhealing.model.HealingContext;
import com.selfhealing.parser.DOMParser;
import com.selfhealing.report.HealingReport;
import com.selfhealing.report.HealingReportManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SelfHealingEngine {

    private final DOMParser domParser;
    private final LocatorMatcher locatorMatcher;
    private final ElementFingerprintBuilder fingerprintBuilder;
    private final DecisionContextBuilder decisionContextBuilder;
    private final AIHealingAgent aiHealingAgent;
    private final AIResponseParser aiResponseParser;


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
        this.aiResponseParser = new AIResponseParser();
    }


    public WebElement heal(
            HealingContext context) {


        System.out.println("Starting self healing...");


        DOMSnapshot snapshot = domParser.parse(
                context.getUrl(),
                context.getHtml());


        List<ElementSnapshot> candidates =
                locatorMatcher.findCandidates(
                        context.getFailedLocator(),
                        snapshot);



        /*
         * Local healing
         */
        if (!candidates.isEmpty()) {


            ElementSnapshot bestCandidate =
                    candidates.get(0);


            ElementFingerprint fingerprint =
                    fingerprintBuilder.build(bestCandidate);


            System.out.println(
                    "Healed Element Fingerprint:");

            System.out.println(fingerprint);



            By healedLocator =
                    generateLocator(bestCandidate);


            System.out.println(
                    "Trying healed locator: "
                            + healedLocator);



            WebElement element =
                    context.getDriver()
                            .findElement(healedLocator);



            addReport(
                    context,
                    healedLocator.toString(),
                    0.90,
                    "DOM_SIMILARITY"
            );


            return element;
        }



        /*
         * AI FALLBACK
         */
        if (aiHealingAgent != null) {


            HealingDecisionContext decisionContext =
                    decisionContextBuilder.build(
                            context,
                            snapshot);



            String aiResponse =
                    aiHealingAgent.heal(
                            decisionContext);



            HealingDecision decision =
                    aiResponseParser.parse(
                            aiResponse);



            if (decision.isSuccess()
                    &&
                    decision.getValue() != null) {



                if (decision.getConfidence() < 0.85) {

                    throw new RuntimeException(
                            "AI confidence too low: "
                                    + decision.getConfidence());
                }



                By aiLocator =
                        buildLocator(
                                decision.getStrategy(),
                                decision.getValue());



                System.out.println(
                        "AI Suggested Locator: "
                                + aiLocator);



                WebElement element =
                        context.getDriver()
                                .findElement(aiLocator);



                addReport(
                        context,
                        aiLocator.toString(),
                        decision.getConfidence(),
                        "AI"
                );



                return element;
            }



            throw new RuntimeException(
                    "AI healing failed: "
                            + decision.getReason());
        }



        throw new RuntimeException(
                "No healing strategy available");
    }



    private void addReport(
            HealingContext context,
            String healedLocator,
            double confidence,
            String source) {


        HealingReport report =
                new HealingReport();


        report.setFailedLocator(
                context.getFailedLocator()
                        .toString());


        report.setHealedLocator(
                healedLocator);


        report.setConfidence(
                confidence);


        report.setHealingSource(
                source);


        report.setUrl(
                context.getUrl());


        report.setHealingStatus(
                "SUCCESS");


        report.setTestResult(
                "PASSED");



        HealingReportManager.addReport(report);



        System.out.println(
                "Healing report captured");
    }



    private By generateLocator(
            ElementSnapshot element) {


        if (element.getId() != null
                &&
                !element.getId().isEmpty()) {

            return By.id(element.getId());
        }


        if (element.getName() != null
                &&
                !element.getName().isEmpty()) {

            return By.name(element.getName());
        }


        if (element.getClassName() != null
                &&
                !element.getClassName().isEmpty()) {

            return By.className(element.getClassName());
        }


        throw new RuntimeException(
                "Unable to generate locator");
    }



    private By buildLocator(
            String strategy,
            String value) {


        switch (strategy.toLowerCase()) {


            case "id":
                return By.id(value);


            case "name":
                return By.name(value);


            case "css":
                return By.cssSelector(value);


            case "xpath":
                return By.xpath(value);


            default:

                throw new RuntimeException(
                        "Unsupported AI strategy: "
                                + strategy);
        }
    }
}