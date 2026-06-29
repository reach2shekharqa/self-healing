package com.selfhealing.model;

import java.util.ArrayList;
import java.util.List;

public class DOMSnapshot {

    private String url;

    private final List<DOMElementSnapshot> elements = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DOMElementSnapshot> getElements() {
        return elements;
    }

    public void addElement(DOMElementSnapshot element) {
        elements.add(element);
    }
    
}
