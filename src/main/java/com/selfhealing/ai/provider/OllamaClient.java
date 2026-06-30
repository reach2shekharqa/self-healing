package com.selfhealing.ai.provider;

import com.selfhealing.ai.config.AIConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class OllamaClient implements LLMClient {


    private final AIConfig config;


    private final HttpClient client =
            HttpClient.newHttpClient();



    public OllamaClient(
            AIConfig config) {

        this.config = config;
    }




    @Override
    public String generate(
            String prompt) {


        System.out.println(
                "\n========== Sending Prompt to Ollama ==========");

        System.out.println(prompt);

        System.out.println(
                "==============================================\n");



        try {


            String requestBody =
                    """
                    {
                      "model": "%s",
                      "messages": [
                        {
                          "role": "user",
                          "content": "%s"
                        }
                      ],
                      "stream": false,
                      "options": {
                        "temperature": 0.1
                      }
                    }
                    """
                    .formatted(
                            config.getModel(),
                            escapeJson(prompt)
                    );




            HttpRequest request =
                    HttpRequest.newBuilder()

                            .uri(
                                    URI.create(
                                            config.getBaseUrl()
                                            + "/api/chat"
                                    )
                            )

                            .header(
                                    "Content-Type",
                                    "application/json"
                            )

                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(requestBody)
                            )

                            .build();




            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );



            System.out.println(
                    "\n========== Ollama Response ==========");

            System.out.println(
                    response.body());

            System.out.println(
                    "=====================================\n");



            return response.body();



        } catch(Exception e) {


            throw new RuntimeException(
                    "Ollama API call failed",
                    e
            );
        }

    }





    private String escapeJson(
            String value) {


        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");

    }

}