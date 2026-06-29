package com.selfhealing.model;

import java.util.*;

public class ElementSnapshot {

    private String tag;
    private String id;
    private String className;
    private String text;

    private Map<String, String> attributes = new HashMap<>();

    // Semantic attributes
    private String role;
    private String ariaLabel;
    private String placeholder;
    private String name;

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