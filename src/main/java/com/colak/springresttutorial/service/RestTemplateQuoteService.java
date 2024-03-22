package com.colak.springresttutorial.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestTemplateQuoteService {

    private final RestTemplate restTemplate;
    public String getRandomQuote() {
        return restTemplate.getForObject("http://localhost:8080/quote/random",String.class);
    }
}

