package com.selfhealing.matcher;

import com.selfhealing.config.HealingConfig;
import com.selfhealing.healing.CandidateGenerator;
import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementSnapshot;
import org.openqa.selenium.By;

import java.util.List;


public class LocatorMatcher {


    private final CandidateGenerator candidateGenerator;

    private final SimilarityCalculator calculator;

    private final HealingConfig config;



    public LocatorMatcher(
            HealingConfig config) {

        this.candidateGenerator =
                new CandidateGenerator();

        this.calculator =
                new SimilarityCalculator();

        this.config = config;
    }



    /*
     * Step 2:
     * Generate filtered candidates
     */
    public List<ElementSnapshot> findCandidates(
            By failedLocator,
            DOMSnapshot snapshot) {


        System.out.println(
                "Searching candidates for: "
                        + failedLocator
        );


        List<ElementSnapshot> candidates =
                candidateGenerator.generate(
                        snapshot,
                        failedLocator.toString()
                );


        System.out.println(
                "Total candidates found: "
                        + candidates.size()
        );


        for(ElementSnapshot candidate : candidates) {


            System.out.println(
                    "Candidate -> "
                            + candidate.getTag()
                            + " id="
                            + candidate.getAttributes().get("id")
            );

        }


        return candidates;
    }





    /*
     * Step 3:
     * Rank candidates and find best match
     */
    public ElementSnapshot findBestCandidate(
            List<ElementSnapshot> candidates,
            By failedLocator) {


        ElementSnapshot bestCandidate = null;


        double highestScore = 0;



        int limit = Math.min(
                candidates.size(),
                config.getMaxCandidates()
        );



        for(int i = 0; i < limit; i++) {


            ElementSnapshot element =
                    candidates.get(i);



            double score =
                    calculator.calculate(
                            failedLocator.toString(),
                            element
                    );



            System.out.println(
                    "Candidate score -> "
                            + element.getTag()
                            + " id="
                            + element.getAttributes().get("id")
                            + " score="
                            + score
            );



            if(score >= config.getSimilarityThreshold()
                    && score > highestScore) {


                highestScore = score;

                bestCandidate = element;

            }

        }



        System.out.println(
                "Best candidate score: "
                        + highestScore
        );


        return bestCandidate;
    }

}