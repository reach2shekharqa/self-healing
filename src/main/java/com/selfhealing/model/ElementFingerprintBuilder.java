package com.selfhealing.model;


public class ElementFingerprintBuilder {


    public ElementFingerprint build(
            ElementSnapshot element
    ) {

        ElementFingerprint fingerprint =
                new ElementFingerprint();


        fingerprint.setTag(
                element.getTag()
        );


        fingerprint.setText(
                element.getText()
        );


        if(element.getId() != null) {

            fingerprint.getImportantAttributes()
                    .put(
                      "id",
                      element.getId()
                    );
        }


        if(element.getClassName() != null) {

            fingerprint.getImportantAttributes()
                    .put(
                      "class",
                      element.getClassName()
                    );
        }


        if(element.getRole() != null) {

            fingerprint.getImportantAttributes()
                    .put(
                      "role",
                      element.getRole()
                    );
        }


        if(element.getAriaLabel() != null) {

            fingerprint.getImportantAttributes()
                    .put(
                      "aria-label",
                      element.getAriaLabel()
                    );
        }


        if(element.getPlaceholder() != null) {

            fingerprint.getImportantAttributes()
                    .put(
                      "placeholder",
                      element.getPlaceholder()
                    );
        }


        // Parent context
        if(element.getParent()!=null) {

            fingerprint.setParentContext(
                element.getParent().getTag()
                + "."
                + element.getParent().getClassName()
            );
        }


        // Nearby elements
        element.getSiblings()
                .forEach(
                    sibling ->
                        fingerprint.getNearbyElements()
                        .add(
                          sibling.getTag()
                          + ":"
                          + sibling.getText()
                        )
                );


        return fingerprint;
    }
}