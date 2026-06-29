package com.selfhealing.model;

import java.util.*;

public class ElementFingerprint {


    private String tag;

    private String text;


    private Map<String,String> importantAttributes =
            new HashMap<>();


    private String parentContext;


    private List<String> nearbyElements =
            new ArrayList<>();



    public String getTag() {
        return tag;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public Map<String,String> getImportantAttributes() {
        return importantAttributes;
    }


    public void setImportantAttributes(
            Map<String,String> importantAttributes
    ) {
        this.importantAttributes = importantAttributes;
    }


    public String getParentContext() {
        return parentContext;
    }


    public void setParentContext(String parentContext) {
        this.parentContext = parentContext;
    }


    public List<String> getNearbyElements() {
        return nearbyElements;
    }


    public void setNearbyElements(
            List<String> nearbyElements
    ) {
        this.nearbyElements = nearbyElements;
    }


    @Override
    public String toString() {

        return "ElementFingerprint{" +
                "tag='" + tag + '\'' +
                ", text='" + text + '\'' +
                ", attributes=" + importantAttributes +
                ", parent='" + parentContext + '\'' +
                ", nearby=" + nearbyElements +
                '}';
    }
}