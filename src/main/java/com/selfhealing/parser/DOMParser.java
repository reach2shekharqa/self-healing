package com.selfhealing.parser;

import com.selfhealing.model.DOMSnapshot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DOMParser {

    private final DOMElementExtractor extractor = new DOMElementExtractor();

    public DOMSnapshot parse(String url, String html) {

        Document document = Jsoup.parse(html);

        DOMSnapshot snapshot = new DOMSnapshot();

        snapshot.setUrl(url);
        snapshot.setTitle(document.title());

        snapshot.setElements(extractor.extract(document));

        return snapshot;
    }
}