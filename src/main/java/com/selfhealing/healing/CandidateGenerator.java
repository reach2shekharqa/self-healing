package com.selfhealing.healing;

import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CandidateGenerator {

    public List<ElementSnapshot> generate(
        DOMSnapshot snapshot,
        String failedLocator) {


    List<ElementSnapshot> candidates =
            new ArrayList<>();


    String searchValue =
            extractLocatorValue(failedLocator);



    for(ElementSnapshot element :
            snapshot.getElements()) {



        String id =
                element.getAttributes()
                        .get("id");


        String text =
                element.getText();



        /*
         * 1. ID similarity
         */
        if(id != null &&
                isSimilar(id, searchValue)) {

            candidates.add(element);
            continue;
        }



        /*
         * 2. Text similarity
         */
        if(text != null &&
                !text.isEmpty()
                &&
                isSimilar(text, searchValue)) {


            candidates.add(element);

        }


    }


    return candidates;
}

    private String extractLocatorValue(String locator) {

        // By.id: react-burger-menu-btn1

        if (locator.contains(":")) {

            return locator
                    .substring(
                            locator.indexOf(":") + 1)
                    .trim();
        }

        return locator;

    }

    private boolean isSimilar(
            String value,
            String target) {

        value = value.toLowerCase();
        target = target.toLowerCase();

        return value.contains(target.substring(0,
                Math.min(5, target.length())))
                ||
                target.contains(value.substring(0,
                        Math.min(5, value.length())));

    }

}