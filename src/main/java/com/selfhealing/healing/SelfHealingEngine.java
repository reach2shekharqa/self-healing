package com.selfhealing.healing;

import org.openqa.selenium.WebElement;

import com.selfhealing.model.DOMSnapshot;
import com.selfhealing.model.HealingContext;
import com.selfhealing.parser.DOMParser;

public class SelfHealingEngine {

    public WebElement heal(HealingContext context) {

        System.out.println("\n========== SELF HEALING ENGINE ==========");
        System.out.println("Failed Locator : " + context.getFailedLocator());
        System.out.println("Current URL    : " + context.getCurrentUrl());
        System.out.println("DOM Size       : " + context.getPageSource().length() + " characters");

        DOMParser parser = new DOMParser();

        DOMSnapshot snapshot = parser.parse(
                context.getCurrentUrl(),
                context.getPageSource());

        System.out.println("Snapshot URL   : " + snapshot.getUrl());

        System.out.println("=========================================\n");

        return null;
    }
}
