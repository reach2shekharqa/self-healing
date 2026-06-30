package com.selfhealing.ai.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selfhealing.ai.model.HealingDecision;


public class AIResponseParser {


    private final ObjectMapper mapper = new ObjectMapper();



    public HealingDecision parse(String response) {


        try {


            /*
             * Extract AI message content
             * from Groq response
             */
            JsonNode root =
                    mapper.readTree(response);


            String content =
                    root.get("choices")
                        .get(0)
                        .get("message")
                        .get("content")
                        .asText();



            /*
             * Remove markdown wrapper
             */
            content = content
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();




            HealingDecision decision =
                    mapper.readValue(
                            content,
                            HealingDecision.class
                    );



            decision.setSuccess(true);



            return decision;



        } catch(Exception e) {


            HealingDecision failed =
                    new HealingDecision();


            failed.setSuccess(false);


            failed.setReason(
                    "Unable to parse AI response: "
                    + e.getMessage()
            );


            return failed;
        }
    }
}