package com.selfhealing.parser;


import com.selfhealing.model.ElementSnapshot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import java.util.*;


public class DOMElementExtractor {


    public List<ElementSnapshot> extract(
            String html
    ) {


        Document document =
                Jsoup.parse(html);


        List<ElementSnapshot> snapshots =
                new ArrayList<>();


        extractRecursive(
                document.body(),
                null,
                snapshots
        );


        buildSiblingRelations(
                snapshots
        );


        return snapshots;
    }



    private void extractRecursive(
            Element element,
            ElementSnapshot parent,
            List<ElementSnapshot> snapshots
    ) {


        ElementSnapshot snapshot =
                createSnapshot(element);



        snapshots.add(snapshot);



        if(parent != null) {

            parent.addChild(snapshot);
        }



        for(Element child : element.children()) {

            extractRecursive(
                    child,
                    snapshot,
                    snapshots
            );
        }

    }




    private ElementSnapshot createSnapshot(
            Element element
    ) {


        ElementSnapshot snapshot =
                new ElementSnapshot();



        snapshot.setTag(
                element.tagName()
        );



        snapshot.setText(
                cleanText(
                    element.text()
                )
        );



        // id

        if(element.hasAttr("id")) {

            snapshot.setId(
                    element.attr("id")
            );
        }



        // class

        if(element.hasAttr("class")) {

            snapshot.setClassName(
                    element.attr("class")
            );
        }



        // semantic attributes

        if(element.hasAttr("role")) {

            snapshot.setRole(
                    element.attr("role")
            );
        }



        if(element.hasAttr("aria-label")) {

            snapshot.setAriaLabel(
                    element.attr("aria-label")
            );
        }



        if(element.hasAttr("placeholder")) {

            snapshot.setPlaceholder(
                    element.attr("placeholder")
            );
        }



        if(element.hasAttr("name")) {

            snapshot.setName(
                    element.attr("name")
            );
        }



        // all attributes

        element.attributes()
                .forEach(attribute ->

                    snapshot.getAttributes()
                    .put(
                        attribute.getKey(),
                        attribute.getValue()
                    )
                );



        return snapshot;

    }




    private void buildSiblingRelations(
            List<ElementSnapshot> elements
    ) {


        for(ElementSnapshot element : elements) {


            ElementSnapshot parent =
                    element.getParent();



            if(parent == null) {
                continue;
            }



            for(ElementSnapshot child :
                    parent.getChildren()) {



                if(child != element) {

                    element.addSibling(child);

                }

            }

        }

    }




    private String cleanText(
            String text
    ) {


        if(text == null) {
            return null;
        }


        text =
            text.replaceAll(
                    "\\s+",
                    " "
            );


        return text.trim();

    }

}