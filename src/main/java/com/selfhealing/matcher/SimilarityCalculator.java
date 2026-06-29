package com.selfhealing.matcher;

import com.selfhealing.model.ElementSnapshot;


public class SimilarityCalculator {


    /**
     * Existing String to String similarity
     */
    public double calculate(
            String first,
            String second
    ) {


        if(first == null || second == null) {
            return 0.0;
        }


        first = first.toLowerCase();
        second = second.toLowerCase();


        int distance =
                levenshteinDistance(
                        first,
                        second
                );


        int maxLength =
                Math.max(
                        first.length(),
                        second.length()
                );


        if(maxLength == 0) {
            return 1.0;
        }


        return 1.0 -
                ((double) distance / maxLength);

    }





    /**
     * New method for ElementSnapshot comparison
     *
     * LocatorMatcher will call this method
     */
    public double calculate(
            String locator,
            ElementSnapshot element
    ) {


        StringBuilder candidate =
                new StringBuilder();



        if(element.getId() != null) {

            candidate.append(
                    element.getId()
            );

        }



        if(element.getClassName() != null) {

            candidate.append(" ");

            candidate.append(
                    element.getClassName()
            );

        }



        if(element.getText() != null) {

            candidate.append(" ");

            candidate.append(
                    element.getText()
            );

        }



        if(element.getName() != null) {

            candidate.append(" ");

            candidate.append(
                    element.getName()
            );

        }



        if(element.getAriaLabel() != null) {

            candidate.append(" ");

            candidate.append(
                    element.getAriaLabel()
            );

        }



        return calculate(
                locator,
                candidate.toString()
        );

    }





    private int levenshteinDistance(
            String a,
            String b
    ) {


        int[][] dp =
                new int[a.length()+1]
                [b.length()+1];



        for(int i = 0; i <= a.length(); i++) {

            dp[i][0] = i;

        }



        for(int j = 0; j <= b.length(); j++) {

            dp[0][j] = j;

        }



        for(int i = 1; i <= a.length(); i++) {


            for(int j = 1; j <= b.length(); j++) {


                int cost =
                        a.charAt(i-1) ==
                        b.charAt(j-1)
                        ? 0
                        : 1;



                dp[i][j] =
                        Math.min(

                            Math.min(
                                dp[i-1][j] + 1,
                                dp[i][j-1] + 1
                            ),

                            dp[i-1][j-1] + cost
                        );

            }

        }


        return dp[a.length()][b.length()];

    }

}