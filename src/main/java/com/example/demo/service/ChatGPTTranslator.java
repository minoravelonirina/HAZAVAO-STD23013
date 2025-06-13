package com.example.demo.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;


@Service
public class ChatGPTTranslator {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    private final String key;

    public ChatGPTTranslator() {
        this.key = "test";
        if (key == null || key.isBlank()) {
            throw new RuntimeException("Clé API manquante : définis la propriété système API_KEY.");
        }
    }

    public String translate(String teny) throws Exception {
        String prompt = "Donner moi une courte phrase qui explique ce mot : " + teny;

        String jsonRequest = """
            {
              "model": "gpt-3.5-turbo",
              "messages": [
                {"role": "user", "content": "%s"}
              ]
            }
            """.formatted(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + key)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
