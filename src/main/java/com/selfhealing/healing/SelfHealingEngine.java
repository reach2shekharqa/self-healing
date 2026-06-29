package com.selfhealing.healing;

import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.ElementSnapshot;
import com.selfhealing.model.HealingContext;
import com.selfhealing.parser.DOMParser;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class SelfHealingEngine {

    private final DOMParser parser = new DOMParser();

    public WebElement heal(HealingContext context) {

        DOMSnapshot snapshot = parser.parse(
                context.getCurrentUrl(),
                context.getPageSource());

        System.out.println();
        System.out.println("========== SELF HEALING ENGINE ==========");
        System.out.println("Failed Locator      : " + context.getFailedLocator());
        System.out.println("Current URL         : " + context.getCurrentUrl());
        System.out.println("DOM Size            : " + context.getPageSource().length() + " characters");
        System.out.println("Page Title          : " + snapshot.getTitle());
        System.out.println("Snapshot URL        : " + snapshot.getUrl());
        System.out.println("Extracted Elements  : " + snapshot.getElements().size());

        System.out.println();
        System.out.println("Extracted Elements:");

        for (ElementSnapshot element : snapshot.getElements()) {

            System.out.println("--------------------------------");
            System.out.println("Tag         : " + element.getTag());
            System.out.println("Id          : " + element.getId());
            System.out.println("Name        : " + element.getName());
            System.out.println("Type        : " + element.getType());
            System.out.println("Placeholder : " + element.getPlaceholder());
            System.out.println("Text        : " + element.getText());
        }
        throw new NoSuchElementException(
                "Healing not implemented yet",
                context.getException());
    }

}