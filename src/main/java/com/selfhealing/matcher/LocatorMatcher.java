package com.selfhealing.matcher;

import com.selfhealing.healing.CandidateGenerator;
import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementSnapshot;
import org.openqa.selenium.By;

import java.util.List;


public class LocatorMatcher {


    private final CandidateGenerator candidateGenerator;


    public LocatorMatcher() {

        this.candidateGenerator =
                new CandidateGenerator();

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


        SimilarityCalculator calculator =
                new SimilarityCalculator();


        ElementSnapshot bestCandidate = null;


        double highestScore = 0;



        for(ElementSnapshot element : candidates) {


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



            if(score > highestScore) {

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