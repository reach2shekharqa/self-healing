package com.selfhealing.healing;

import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CandidateGenerator {


    public List<ElementSnapshot> generate(
            DOMSnapshot snapshot,
            String failedLocator) {


        List<ElementSnapshot> candidates =
                new ArrayList<>();


        LocatorInfo locator =
                parseLocator(failedLocator);



        System.out.println("Parsed Locator");
        System.out.println("Strategy : " + locator.strategy);
        System.out.println("Attribute: " + locator.attribute);
        System.out.println("Value    : " + locator.value);



        for (ElementSnapshot element :
                snapshot.getElements()) {


            if (element.getTag() == null) {
                continue;
            }


            if (isCandidate(element, locator)) {

                candidates.add(element);
            }
        }


        return candidates;
    }




    /**
     * Generic candidate matching.
     */
    private boolean isCandidate(
            ElementSnapshot element,
            LocatorInfo locator) {


        if (element.getAttributes() == null) {
            return false;
        }



        /*
         * Match attribute based locator
         */
        if (locator.attribute != null) {


            String candidate =
                    element.getAttributes()
                            .get(locator.attribute);



            if (candidate != null &&
                    isSimilar(
                            candidate,
                            locator.value)) {


                /*
                 * Accept only useful elements.
                 */
                return isInteractiveElement(element)
                        || hasUsefulAttribute(element);
            }
        }




        /*
         * Text based locator
         */
        if ("text".equals(locator.attribute)) {


            String text =
                    element.getText();



            if (text != null &&
                    isSimilar(
                            text,
                            locator.value)) {


                return isInteractiveElement(element);
            }
        }


        return false;
    }





    /**
     * Checks whether element can normally
     * receive Selenium actions.
     */
    private boolean isInteractiveElement(
            ElementSnapshot element) {


        String tag =
                element.getTag()
                        .toLowerCase(Locale.ROOT);



        return tag.equals("input")
                || tag.equals("button")
                || tag.equals("a")
                || tag.equals("select")
                || tag.equals("textarea");
    }




    /**
     * Allows non standard elements only
     * when they contain useful locator data.
     */
    private boolean hasUsefulAttribute(
            ElementSnapshot element) {


        return element.getAttributes()
                .containsKey("id")

                || element.getAttributes()
                .containsKey("name")

                || element.getAttributes()
                .containsKey("type")

                || element.getAttributes()
                .containsKey("placeholder")

                || element.getAttributes()
                .containsKey("data-testid");
    }





    /**
     * Parses Selenium locator string.
     */
    private LocatorInfo parseLocator(
            String locator) {


        LocatorInfo info =
                new LocatorInfo();



        locator = locator.trim();



        if (locator.startsWith("By.id:")) {

            info.strategy = "id";
            info.attribute = "id";
            info.value =
                    locator.substring(6).trim();

            return info;
        }



        if (locator.startsWith("By.name:")) {

            info.strategy = "name";
            info.attribute = "name";
            info.value =
                    locator.substring(8).trim();

            return info;
        }



        if (locator.startsWith("By.className:")) {

            info.strategy = "class";
            info.attribute = "class";
            info.value =
                    locator.substring(13).trim();

            return info;
        }



        if (locator.startsWith("By.tagName:")) {

            info.strategy = "tag";
            info.attribute = "tag";
            info.value =
                    locator.substring(11).trim();

            return info;
        }



        if (locator.startsWith("By.cssSelector:")) {

            info.strategy = "css";


            String css =
                    locator.substring(15)
                            .trim();


            parseCss(css, info);

            return info;
        }



        if (locator.startsWith("By.xpath:")) {

            info.strategy = "xpath";


            String xpath =
                    locator.substring(9)
                            .trim();



            parseXPath(xpath, info);


            return info;
        }



        info.strategy = "unknown";
        info.value = locator;


        return info;
    }






    /**
     * XPath parser.
     */
    private void parseXPath(
            String xpath,
            LocatorInfo info) {


        Matcher matcher =
                Pattern.compile(
                        "@([a-zA-Z0-9_-]+)\\s*=\\s*['\"]([^'\"]+)['\"]")
                        .matcher(xpath);



        if (matcher.find()) {

            info.attribute =
                    matcher.group(1);

            info.value =
                    matcher.group(2);

            return;
        }



        matcher =
                Pattern.compile(
                        "text\\(\\)\\s*=\\s*['\"]([^'\"]+)['\"]")
                        .matcher(xpath);



        if (matcher.find()) {

            info.attribute = "text";

            info.value =
                    matcher.group(1);
        }
    }







    /**
     * CSS parser.
     */
    private void parseCss(
            String css,
            LocatorInfo info) {


        Matcher matcher =
                Pattern.compile(
                        "\\[([a-zA-Z0-9_-]+)=['\"]?([^'\"\\]]+)['\"]?\\]")
                        .matcher(css);



        if (matcher.find()) {

            info.attribute =
                    matcher.group(1);

            info.value =
                    matcher.group(2);
        }
    }







    /**
     * Similarity calculation.
     */
    private boolean isSimilar(
            String actual,
            String expected) {


        if (actual == null ||
                expected == null) {

            return false;
        }



        actual =
                actual.toLowerCase(Locale.ROOT);


        expected =
                expected.toLowerCase(Locale.ROOT);



        if (actual.equals(expected)) {
            return true;
        }



        if (actual.contains(expected)) {
            return true;
        }



        if (expected.contains(actual)) {
            return true;
        }



        if (actual.startsWith(expected)) {
            return true;
        }



        if (expected.startsWith(actual)) {
            return true;
        }



        return levenshtein(
                actual,
                expected) <= 2;
    }







    /**
     * Levenshtein distance.
     */
    private int levenshtein(
            String a,
            String b) {


        int[][] dp =
                new int[a.length() + 1]
                        [b.length() + 1];



        for (int i = 0;
             i <= a.length();
             i++) {

            dp[i][0] = i;
        }



        for (int j = 0;
             j <= b.length();
             j++) {

            dp[0][j] = j;
        }



        for (int i = 1;
             i <= a.length();
             i++) {


            for (int j = 1;
                 j <= b.length();
                 j++) {


                int cost =
                        a.charAt(i - 1)
                                == b.charAt(j - 1)
                                ? 0
                                : 1;



                dp[i][j] =
                        Math.min(
                                Math.min(
                                        dp[i - 1][j] + 1,
                                        dp[i][j - 1] + 1),
                                dp[i - 1][j - 1] + cost);
            }
        }


        return dp[a.length()][b.length()];
    }







    /**
     * Internal locator holder.
     */
    private static class LocatorInfo {

        private String strategy;

        private String attribute;

        private String value;
    }

}