package com.selfhealing.parser;

import com.selfhealing.model.DOMSnapshot;


public class DOMParser {


    private final DOMElementExtractor extractor;


    public DOMParser() {
        this.extractor = new DOMElementExtractor();
    }


    public DOMSnapshot parse(
            String url,
            String html
    ) {


        DOMSnapshot snapshot =
                new DOMSnapshot();


        snapshot.setUrl(url);


        snapshot.setElements(
                extractor.extract(html)
        );


        return snapshot;
    }

}