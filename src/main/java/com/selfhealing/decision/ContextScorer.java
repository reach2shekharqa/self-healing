package com.selfhealing.decision;

import com.selfhealing.model.ElementFingerprint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ContextScorer {


    /**
     * Reduce DOM fingerprints before sending to AI.
     */
    public List<ElementFingerprint> reduce(
            List<ElementFingerprint> fingerprints) {


        if (fingerprints == null ||
                fingerprints.isEmpty()) {

            return new ArrayList<>();
        }



        /*
         * Rank elements by relevance
         */
        fingerprints.sort(
                Comparator.comparingInt(
                        this::calculateScore)
                        .reversed()
        );



        /*
         * Keep only top candidates
         */
        int limit =
                Math.min(
                        fingerprints.size(),
                        3);



        return new ArrayList<>(
                fingerprints.subList(
                        0,
                        limit));
    }





    private int calculateScore(
            ElementFingerprint element) {


        int score = 0;



        String tag =
                element.getTag();



        if (tag != null) {


            switch (
                    tag.toLowerCase()) {


                case "button":
                    score += 100;
                    break;


                case "input":
                    score += 80;
                    break;


                case "a":
                    score += 70;
                    break;


                case "select":
                case "textarea":
                    score += 60;
                    break;


                case "label":
                    score += 20;
                    break;


                default:
                    score += 5;
            }
        }




        /*
         * Text helps AI understand intent
         */
        if (element.getText() != null &&
                !element.getText().isEmpty()) {

            score += 20;
        }





        /*
         * Important attributes
         */
        if (element.getImportantAttributes()
                != null) {


            if (element.getImportantAttributes()
                    .containsKey("id")) {

                score += 30;
            }


            if (element.getImportantAttributes()
                    .containsKey("name")) {

                score += 25;
            }


            if (element.getImportantAttributes()
                    .containsKey("type")) {

                score += 15;
            }


            if (element.getImportantAttributes()
                    .containsKey("placeholder")) {

                score += 15;
            }


            if (element.getImportantAttributes()
                    .containsKey("data-testid")) {

                score += 40;
            }
        }



        return score;
    }

}