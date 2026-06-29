package com.selfhealing.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.selfhealing.model.DOMSnapshot;

public class DOMParser {

    public DOMSnapshot parse(String url, String html) {

        Document document = Jsoup.parse(html);

        DOMSnapshot snapshot = new DOMSnapshot();
        snapshot.setUrl(url);

        System.out.println("Page Title : " + document.title());

        return snapshot;
    }
}