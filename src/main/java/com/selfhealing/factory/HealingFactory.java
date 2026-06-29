package com.selfhealing.factory;

import com.selfhealing.config.HealingConfig;

import com.selfhealing.healing.SelfHealingEngine;
import com.selfhealing.matcher.LocatorMatcher;
import com.selfhealing.model.ElementFingerprintBuilder;
import com.selfhealing.parser.DOMParser;

public final class HealingFactory {

    private HealingFactory() {
    }


    public static SelfHealingEngine createEngine() {

        return createEngine(new HealingConfig());
    }



    public static SelfHealingEngine createEngine(
            HealingConfig config) {


        DOMParser domParser =
                new DOMParser();


        LocatorMatcher locatorMatcher =
                new LocatorMatcher(config);


        ElementFingerprintBuilder fingerprintBuilder =
                new ElementFingerprintBuilder();



        return new SelfHealingEngine(
                domParser,
                locatorMatcher,
                fingerprintBuilder
        );
    }

}