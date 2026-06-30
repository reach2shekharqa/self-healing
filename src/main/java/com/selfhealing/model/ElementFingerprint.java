package com.selfhealing.model;

import java.util.*;


public class ElementFingerprint {


    /**
     * HTML tag (input, button, div, etc.)
     */
    private String tag;


    /**
     * Visible text.
     */
    private String text;


    /**
     * Important HTML attributes.
     */
    private Map<String, String> importantAttributes =
            new LinkedHashMap<>();


    /**
     * Parent element description.
     */
    private String parentContext;


    /**
     * Nearby labels/siblings.
     */
    private List<String> nearbyElements =
            new ArrayList<>();


    /**
     * XPath generated during DOM parsing.
     */
    private String xpath;


    /**
     * CSS selector generated during DOM parsing.
     */
    private String cssSelector;


    /**
     * Whether element is displayed.
     */
    private boolean displayed;


    /**
     * Whether element is enabled.
     */
    private boolean enabled;


    /**
     * DOM depth.
     */
    private int depth;




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



    public Map<String, String> getImportantAttributes() {

        return importantAttributes;
    }



    public void setImportantAttributes(
            Map<String, String> importantAttributes) {

        this.importantAttributes =
                importantAttributes;
    }



    public String getParentContext() {

        return parentContext;
    }



    public void setParentContext(
            String parentContext) {

        this.parentContext =
                parentContext;
    }



    public List<String> getNearbyElements() {

        return nearbyElements;
    }



    public void setNearbyElements(
            List<String> nearbyElements) {

        this.nearbyElements =
                nearbyElements;
    }



    public String getXpath() {

        return xpath;
    }



    public void setXpath(String xpath) {

        this.xpath = xpath;
    }



    public String getCssSelector() {

        return cssSelector;
    }



    public void setCssSelector(
            String cssSelector) {

        this.cssSelector =
                cssSelector;
    }



    public boolean isDisplayed() {

        return displayed;
    }



    public void setDisplayed(
            boolean displayed) {

        this.displayed =
                displayed;
    }



    public boolean isEnabled() {

        return enabled;
    }



    public void setEnabled(
            boolean enabled) {

        this.enabled =
                enabled;
    }



    public int getDepth() {

        return depth;
    }



    public void setDepth(int depth) {

        this.depth = depth;
    }




    @Override
    public String toString() {

        return "ElementFingerprint{" +
                "tag='" + tag + '\'' +
                ", text='" + text + '\'' +
                ", attributes=" + importantAttributes +
                ", parent='" + parentContext + '\'' +
                ", nearby=" + nearbyElements +
                ", xpath='" + xpath + '\'' +
                ", cssSelector='" + cssSelector + '\'' +
                ", displayed=" + displayed +
                ", enabled=" + enabled +
                ", depth=" + depth +
                '}';
    }

}