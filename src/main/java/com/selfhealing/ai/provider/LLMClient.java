package com.selfhealing.ai.provider;

public interface LLMClient {

    /**
     * Sends a prompt to the configured LLM and
     * returns the raw text response.
     *
     * @param prompt AI prompt
     * @return Raw LLM response
     */
    String generate(String prompt);

}