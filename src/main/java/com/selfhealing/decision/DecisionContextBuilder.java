package com.selfhealing.decision;

import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementFingerprint;
import com.selfhealing.model.ElementFingerprintBuilder;
import com.selfhealing.model.ElementSnapshot;
import com.selfhealing.model.HealingContext;

import java.util.ArrayList;
import java.util.List;

public class DecisionContextBuilder {

        private final ElementFingerprintBuilder fingerprintBuilder = new ElementFingerprintBuilder();

        private final ContextScorer contextScorer = new ContextScorer();

        public HealingDecisionContext build(
                        HealingContext healingContext,
                        DOMSnapshot snapshot) {

                HealingDecisionContext decisionContext = new HealingDecisionContext();

                // Failed Locator
                decisionContext.setFailedLocator(
                                healingContext.getFailedLocator().toString());

                // Current URL
                decisionContext.setCurrentUrl(
                                healingContext.getUrl());

                // Exception
                if (healingContext.getException() != null) {
                        decisionContext.setExceptionType(
                                        healingContext.getException()
                                                        .getClass()
                                                        .getSimpleName());
                }

                // Page Title
                try {
                        decisionContext.setPageTitle(
                                        healingContext.getDriver().getTitle());
                } catch (Exception ignored) {
                }

                /*
                 * Build fingerprints for all DOM elements
                 */
                List<ElementFingerprint> fingerprints = new ArrayList<>();

                for (ElementSnapshot element : snapshot.getElements()) {

                        ElementFingerprint fingerprint = fingerprintBuilder.build(element);

                        if (fingerprint == null) {
                                continue;
                        }

                        if (fingerprint.getTag() == null) {
                                continue;
                        }

                        fingerprints.add(fingerprint);
                }

                /*
                 * Reduce context before sending to AI
                 */
                List<ElementFingerprint> rankedFingerprints = contextScorer.reduce(fingerprints);

                decisionContext.setCandidateElements(
                                rankedFingerprints);

                return decisionContext;
        }
}