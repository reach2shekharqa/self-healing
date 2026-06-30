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

                System.out.println(
                                "Starting self healing...");

                DOMSnapshot snapshot = domParser.parse(
                                context.getUrl(),
                                context.getHtml());

                List<ElementSnapshot> candidates = locatorMatcher.findCandidates(
                                context.getFailedLocator(),
                                snapshot);

                /*
                 * Existing local healing
                 */
                if (!candidates.isEmpty()) {

                        ElementSnapshot bestCandidate = candidates.get(0);

                        ElementFingerprint fingerprint = fingerprintBuilder.build(
                                        bestCandidate);

                        System.out.println(
                                        "Healed Element Fingerprint:");

                        System.out.println(
                                        fingerprint);

                        By healedLocator = generateLocator(
                                        bestCandidate);

                        System.out.println(
                                        "Trying healed locator: "
                                                        + healedLocator);

                        return context.getDriver()
                                        .findElement(
                                                        healedLocator);
                }

                /*
                 * AI FALLBACK
                 */
                if (aiHealingAgent != null) {

                        HealingDecisionContext decisionContext = decisionContextBuilder.build(
                                        context,
                                        snapshot);

                        System.out.println(
                                        "\n========== AI Decision Context ==========");

                        System.out.println(
                                        decisionContext);

                        System.out.println(
                                        "=========================================\n");

                        /*
                         * AI returns raw response
                         */
                        String aiResponse = aiHealingAgent.heal(
                                        decisionContext);

                        System.out.println(
                                        "\n========== AI Response ==========");

                        System.out.println(
                                        aiResponse);

                        System.out.println(
                                        "=================================\n");

                        /*
                         * Raw response -> HealingDecision
                         */
                        HealingDecision decision = aiResponseParser.parse(
                                        aiResponse);

                        if (decision.isSuccess()
                                        &&
                                        decision.getValue() != null) {

                                /*
                                 * Confidence validation
                                 */
                                if (decision.getConfidence() < 0.85) {

                                        throw new RuntimeException(
                                                        "AI confidence too low: "
                                                                        + decision.getConfidence());
                                }

                                By aiLocator = buildLocator(
                                                decision.getStrategy(),
                                                decision.getValue());

                                System.out.println(
                                                "\n========== AI Healing Result ==========");

                                System.out.println(
                                                "Original Locator: "
                                                                + context.getFailedLocator());

                                System.out.println(
                                                "AI Suggested Locator: "
                                                                + aiLocator);

                                System.out.println(
                                                "AI Confidence: "
                                                                + decision.getConfidence());

                                System.out.println(
                                                "Healing Status: SUCCESS");

                                System.out.println(
                                                "=======================================\n");

                                return context.getDriver()
                                                .findElement(aiLocator);
                        }

                        throw new RuntimeException(
                                        "AI healing failed: "
                                                        + decision.getReason());
                }

                throw new RuntimeException(
                                "No healing strategy available");
        }

        private By generateLocator(
                        ElementSnapshot element) {

                if (element.getId() != null
                                &&
                                !element.getId().isEmpty()) {

                        return By.id(
                                        element.getId());
                }

                if (element.getName() != null
                                &&
                                !element.getName().isEmpty()) {

                        return By.name(
                                        element.getName());
                }

                if (element.getClassName() != null
                                &&
                                !element.getClassName().isEmpty()) {

                        return By.className(
                                        element.getClassName());
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