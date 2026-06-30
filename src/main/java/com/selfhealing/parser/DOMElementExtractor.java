package com.selfhealing.parser;

import com.selfhealing.model.ElementSnapshot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class DOMElementExtractor {

    public List<ElementSnapshot> extract(String html) {

        Document document = Jsoup.parse(html);

        List<ElementSnapshot> snapshots = new ArrayList<>();

        extractRecursive(
                document.body(),
                null,
                snapshots);

        buildSiblingRelations(snapshots);

        return snapshots;
    }

    private void extractRecursive(
            Element element,
            ElementSnapshot parent,
            List<ElementSnapshot> snapshots) {

        ElementSnapshot snapshot = createSnapshot(element);

        snapshots.add(snapshot);

        if (parent != null) {
            parent.addChild(snapshot);
        }

        for (Element child : element.children()) {

            extractRecursive(
                    child,
                    snapshot,
                    snapshots);
        }
    }

    private ElementSnapshot createSnapshot(Element element) {

        ElementSnapshot snapshot = new ElementSnapshot();

        // Basic information
        snapshot.setTag(element.tagName());
        snapshot.setText(cleanText(element.text()));

        // Standard attributes
        if (element.hasAttr("id")) {
            snapshot.setId(element.attr("id"));
        }

        if (element.hasAttr("class")) {
            snapshot.setClassName(element.attr("class"));
        }

        if (element.hasAttr("name")) {
            snapshot.setName(element.attr("name"));
        }

        if (element.hasAttr("type")) {
            snapshot.setType(element.attr("type"));
        }

        if (element.hasAttr("value")) {
            snapshot.setValue(element.attr("value"));
        }

        if (element.hasAttr("placeholder")) {
            snapshot.setPlaceholder(element.attr("placeholder"));
        }

        if (element.hasAttr("title")) {
            snapshot.setTitle(element.attr("title"));
        }

        if (element.hasAttr("href")) {
            snapshot.setHref(element.attr("href"));
        }

        if (element.hasAttr("src")) {
            snapshot.setSrc(element.attr("src"));
        }

        if (element.hasAttr("alt")) {
            snapshot.setAlt(element.attr("alt"));
        }

        // Accessibility attributes
        if (element.hasAttr("role")) {
            snapshot.setRole(element.attr("role"));
        }

        if (element.hasAttr("aria-label")) {
            snapshot.setAriaLabel(element.attr("aria-label"));
        }

        // Store ALL attributes
        element.attributes().forEach(attribute ->
                snapshot.getAttributes().put(
                        attribute.getKey(),
                        attribute.getValue()));

        return snapshot;
    }

    private void buildSiblingRelations(List<ElementSnapshot> elements) {

        for (ElementSnapshot element : elements) {

            ElementSnapshot parent = element.getParent();

            if (parent == null) {
                continue;
            }

            for (ElementSnapshot sibling : parent.getChildren()) {

                if (sibling != element) {
                    element.addSibling(sibling);
                }
            }
        }
    }

    private String cleanText(String text) {

        if (text == null) {
            return "";
        }

        return text.replaceAll("\\s+", " ").trim();
    }
}