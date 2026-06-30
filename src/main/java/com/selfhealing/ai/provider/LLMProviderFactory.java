package com.selfhealing.ai.provider;

import com.selfhealing.ai.config.AIConfig;
import com.selfhealing.ai.config.AIProvider;

public final class LLMProviderFactory {

    private LLMProviderFactory() {
    }

    public static LLMClient create(AIConfig config) {

        AIProvider provider = config.getProvider();

        switch (provider) {

            case GROQ:
                return new GroqClient(config);

            case NVIDIA:
               // return new NvidiaClient(config);

            default:
                throw new IllegalArgumentException(
                        "Unsupported AI Provider: " + provider);
        }
    }
}