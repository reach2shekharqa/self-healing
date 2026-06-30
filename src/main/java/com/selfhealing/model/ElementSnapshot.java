package com.selfhealing.model;

import java.util.*;

public class ElementSnapshot {

    private String tag;

    private String id;

    private String className;

    private String text;

    /**
     * All HTML attributes.
     */
    private Map<String, String> attributes = new LinkedHashMap<>();

    // Semantic attributes
    private String role;

    private String ariaLabel;

    private String placeholder;

    private String name;

    private String type;

    private String value;

    private String title;

    private String href;

    private String src;

    private String alt;

    // Relationship information
    private ElementSnapshot parent;

    private List<ElementSnapshot> children = new ArrayList<>();

    private List<ElementSnapshot> siblings = new ArrayList<>();

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAriaLabel() {
        return ariaLabel;
    }

    public void setAriaLabel(String ariaLabel) {
        this.ariaLabel = ariaLabel;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public ElementSnapshot getParent() {
        return parent;
    }

    public void setParent(ElementSnapshot parent) {
        this.parent = parent;
    }

    public List<ElementSnapshot> getChildren() {
        return children;
    }

    public void setChildren(List<ElementSnapshot> children) {
        this.children = children;
    }

    public List<ElementSnapshot> getSiblings() {
        return siblings;
    }

    public void setSiblings(List<ElementSnapshot> siblings) {
        this.siblings = siblings;
    }

    public void addChild(ElementSnapshot child) {
        children.add(child);
        child.setParent(this);
    }

    public void addSibling(ElementSnapshot sibling) {
        siblings.add(sibling);
    }
}