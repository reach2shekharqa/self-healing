package com.selfhealing.decision;

import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementFingerprint;
import com.selfhealing.model.ElementFingerprintBuilder;
import com.selfhealing.model.ElementSnapshot;
import com.selfhealing.model.HealingContext;

import java.util.ArrayList;
import java.util.List;


public class DecisionContextBuilder {


    private final ElementFingerprintBuilder fingerprintBuilder =
            new ElementFingerprintBuilder();


    private final ContextScorer contextScorer =
            new ContextScorer();




    public HealingDecisionContext build(
            HealingContext healingContext,
            DOMSnapshot snapshot) {



        String failedLocator =
                healingContext.getFailedLocator()
                        .toString();



        String currentUrl =
                healingContext.getUrl();



        String pageTitle = null;


        try {

            pageTitle =
                    healingContext.getDriver()
                            .getTitle();

        } catch (Exception ignored) {

        }



        String exceptionType = null;

        String exceptionMessage = null;



        if (healingContext.getException() != null) {


            exceptionType =
                    healingContext.getException()
                            .getClass()
                            .getSimpleName();



            exceptionMessage =
                    healingContext.getException()
                            .getMessage();

        }




        /*
         * Build fingerprints
         */
        List<ElementFingerprint> fingerprints =
                new ArrayList<>();



        for(ElementSnapshot element :
                snapshot.getElements()) {



            ElementFingerprint fingerprint =
                    fingerprintBuilder.build(element);



            if(fingerprint == null) {
                continue;
            }



            if(fingerprint.getTag() == null) {
                continue;
            }



            fingerprints.add(fingerprint);
        }




        /*
         * Reduce context size before AI
         */
        List<ElementFingerprint> rankedFingerprints =
                contextScorer.reduce(
                        fingerprints);




        /*
         * Create AI decision context
         */
        return new HealingDecisionContext(

                failedLocator,

                currentUrl,

                pageTitle,

                exceptionType,

                exceptionMessage,

                true,

                0.0,

                "1.0.0",

                rankedFingerprints
        );

    }

}