package com.selfhealing.model;

public class ElementFingerprintBuilder {

    public ElementFingerprint build(ElementSnapshot element) {

        ElementFingerprint fingerprint = new ElementFingerprint();

        // Basic information
        fingerprint.setTag(element.getTag());
        fingerprint.setText(element.getText());

        // ---------- Important Attributes ----------

        addAttribute(fingerprint, "id", element.getId());

        addAttribute(fingerprint, "name", element.getName());

        addAttribute(fingerprint, "class", element.getClassName());

        addAttribute(fingerprint, "type", element.getType());

        addAttribute(fingerprint, "value", element.getValue());

        addAttribute(fingerprint, "placeholder", element.getPlaceholder());

        addAttribute(fingerprint, "role", element.getRole());

        addAttribute(fingerprint, "aria-label", element.getAriaLabel());

        addAttribute(fingerprint, "title", element.getTitle());

        addAttribute(fingerprint, "href", element.getHref());

        addAttribute(fingerprint, "src", element.getSrc());

        addAttribute(fingerprint, "alt", element.getAlt());

        // Parent context
        if (element.getParent() != null) {

            String parentContext =
                    element.getParent().getTag();

            if (element.getParent().getClassName() != null
                    && !element.getParent().getClassName().isBlank()) {

                parentContext += "." + element.getParent().getClassName();
            }

            fingerprint.setParentContext(parentContext);
        }

        // Nearby Elements
        for (ElementSnapshot sibling : element.getSiblings()) {

            String nearby =
                    sibling.getTag() + ":" + sibling.getText();

            fingerprint.getNearbyElements().add(nearby);
        }

        /*
         * Advanced properties.
         * These will be populated in a later milestone.
         */
        fingerprint.setDisplayed(false);
        fingerprint.setEnabled(false);
        fingerprint.setDepth(0);
        fingerprint.setXpath(null);
        fingerprint.setCssSelector(null);

        return fingerprint;
    }

    /**
     * Adds an attribute only when it has a non-empty value.
     */
    private void addAttribute(
            ElementFingerprint fingerprint,
            String key,
            String value) {

        if (value != null && !value.isBlank()) {

            fingerprint.getImportantAttributes()
                    .put(key, value);
        }
    }
}