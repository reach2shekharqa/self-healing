package com.selfhealing.factory;

import com.selfhealing.ai.agent.AIHealingAgent;
import com.selfhealing.ai.config.AIConfig;
import com.selfhealing.ai.config.AIConfigBuilder;
import com.selfhealing.ai.provider.LLMClient;
import com.selfhealing.ai.provider.LLMProviderFactory;
import com.selfhealing.config.HealingConfig;
import com.selfhealing.decision.DecisionContextBuilder;
import com.selfhealing.healing.SelfHealingEngine;
import com.selfhealing.matcher.LocatorMatcher;
import com.selfhealing.model.ElementFingerprintBuilder;
import com.selfhealing.parser.DOMParser;


public final class HealingFactory {


    private HealingFactory() {
    }


    public static SelfHealingEngine createEngine(
            HealingConfig healingConfig) {


        /*
         * AI Configuration
         */
        AIConfig aiConfig =
                AIConfigBuilder.build();



        /*
         * LLM Provider
         */
        LLMClient llmClient =
                LLMProviderFactory.create(
                        aiConfig);



        /*
         * AI Healing Agent
         */
        AIHealingAgent aiHealingAgent =
                new AIHealingAgent(
                        llmClient);



        /*
         * Locator Matcher
         */
        LocatorMatcher locatorMatcher =
                new LocatorMatcher(
                        healingConfig);



        return new SelfHealingEngine(

                new DOMParser(),

                locatorMatcher,

                new ElementFingerprintBuilder(),

                new DecisionContextBuilder(),

                aiHealingAgent
        );
    }

}