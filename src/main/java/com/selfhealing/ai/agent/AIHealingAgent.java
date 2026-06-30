package com.selfhealing.ai.agent;

import com.selfhealing.ai.provider.LLMClient;
import com.selfhealing.decision.HealingDecisionContext;
import com.selfhealing.model.ElementFingerprint;


public class AIHealingAgent {


    private final LLMClient llmClient;



    public AIHealingAgent(
            LLMClient llmClient) {

        this.llmClient = llmClient;
    }





    public String heal(
            HealingDecisionContext context) {


        System.out.println(
                "AI Healing Agent started...");



        if (llmClient == null) {

            return "AI disabled";
        }



        String prompt =
                buildPrompt(context);



        return llmClient.generate(prompt);

    }





    private String buildPrompt(
            HealingDecisionContext context) {


        StringBuilder prompt =
                new StringBuilder();




        prompt.append(
                "You are a Selenium self healing AI.\n\n");



        prompt.append(
                "Analyze the failed Selenium locator "
                + "and suggest the best replacement locator.\n\n");



        prompt.append(
                "Failed Locator:\n")
                .append(
                context.getFailedLocator())
                .append("\n\n");



        prompt.append(
                "Page URL:\n")
                .append(
                context.getCurrentUrl())
                .append("\n\n");



        prompt.append(
                "Candidate Elements:\n");



        int count = 1;



        for(ElementFingerprint element :
                context.getCandidateElements()) {



            prompt.append(
                    "\nCandidate ")
                    .append(count++)
                    .append("\n");



            prompt.append(
                    "Tag: ")
                    .append(element.getTag())
                    .append("\n");



            prompt.append(
                    "Text: ")
                    .append(element.getText())
                    .append("\n");



            prompt.append(
                    "Attributes: ")
                    .append(
                    element.getImportantAttributes())
                    .append("\n");

        }




        prompt.append(
                """

                Return ONLY valid JSON.

                Format:
                {
                  "strategy":"id|name|css|xpath",
                  "value":"locator value",
                  "confidence":0.0
                }
                """);



        return prompt.toString();
    }

}