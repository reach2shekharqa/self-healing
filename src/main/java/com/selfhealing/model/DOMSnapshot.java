package com.selfhealing.model;

import java.util.*;

public class DOMSnapshot {


    private String url;

    private List<ElementSnapshot> elements = new ArrayList<>();


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public List<ElementSnapshot> getElements() {
        return elements;
    }


    public void setElements(List<ElementSnapshot> elements) {
        this.elements = elements;
    }


    public void addElement(ElementSnapshot element) {
        elements.add(element);
    }


    public List<ElementSnapshot> findByTag(String tag) {

        return elements.stream()
                .filter(e -> tag.equalsIgnoreCase(e.getTag()))
                .toList();
    }


    public List<ElementSnapshot> findByText(String text) {

        return elements.stream()
                .filter(e ->
                    e.getText() != null &&
                    e.getText().contains(text)
                )
                .toList();
    }

}