package com.selfhealing.model;

import java.util.ArrayList;
import java.util.List;

public class DOMSnapshot {

    private String url;
    private String title;

    private List<ElementSnapshot> elements = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ElementSnapshot> getElements() {
        return elements;
    }

    public void setElements(List<ElementSnapshot> elements) {
        this.elements = elements;
    }
}