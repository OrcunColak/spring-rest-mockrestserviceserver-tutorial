package com.colak.springresttutorial.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class RestClientQuoteService {

    private final RestClient.Builder restClientBuilder;

    public String getRandomQuote() {
        RestClient restClient = restClientBuilder.build();
        return restClient
                .get()
                .uri("http://localhost:8080/quote/random")
                .retrieve()
                .body(String.class);
    }
}

