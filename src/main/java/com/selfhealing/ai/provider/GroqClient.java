package com.selfhealing.ai.provider;

import com.selfhealing.ai.config.AIConfig;

public class GroqClient implements LLMClient {

    private final AIConfig config;

    public GroqClient(AIConfig config) {
        this.config = config;
    }

    @Override
    public String generate(String prompt) {

        System.out.println("\n========== Sending Prompt to Groq ==========");
        System.out.println(prompt);
        System.out.println("============================================\n");

        // TODO: Replace with actual Groq API call in Milestone 5.2
        return "Dummy response from Groq";
    }
}