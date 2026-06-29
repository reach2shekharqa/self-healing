package com.selfhealing.parser;

import com.selfhealing.model.ElementSnapshot;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DOMElementExtractor {

    private static final Set<String> INTERACTIVE_TAGS = Set.of(
            "input",
            "button",
            "select",
            "textarea",
            "option",
            "a",
            "label",
            "form"
    );

    public List<ElementSnapshot> extract(Document document) {

        List<ElementSnapshot> snapshots = new ArrayList<>();

        for (Element element : document.getAllElements()) {

            if (!isRelevant(element)) {
                continue;
            }

            snapshots.add(createSnapshot(element));
        }

        return snapshots;
    }

    private boolean isRelevant(Element element) {

        if (INTERACTIVE_TAGS.contains(element.tagName())) {
            return true;
        }

        return element.hasAttr("role")
                || element.hasAttr("onclick")
                || element.hasAttr("tabindex")
                || element.hasAttr("contenteditable");
    }

    private ElementSnapshot createSnapshot(Element element) {

        ElementSnapshot snapshot = new ElementSnapshot();

        snapshot.setTag(element.tagName());
        snapshot.setId(element.id());
        snapshot.setName(element.attr("name"));
        snapshot.setText(element.ownText());
        snapshot.setClassName(element.className());
        snapshot.setPlaceholder(element.attr("placeholder"));
        snapshot.setType(element.attr("type"));

        snapshot.setClasses(new ArrayList<>(element.classNames()));

        for (Attribute attribute : element.attributes()) {
            snapshot.getAttributes().put(
                    attribute.getKey(),
                    attribute.getValue()
            );
        }

        return snapshot;
    }
}