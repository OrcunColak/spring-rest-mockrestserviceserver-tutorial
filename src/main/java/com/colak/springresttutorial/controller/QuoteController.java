package com.colak.springresttutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private final List<String> quotes = Arrays.asList(
            "To be or not to be, that is the question.",
            "The only way to do great work is to love what you do.",
            "I have not failed. I've just found 10,000 ways that won't work."
    );

    // http://localhost:8080/quote/random
    @GetMapping(value = "/random")
    public ResponseEntity<String> getRandomQuote() {
        String randomQuote = quotes.get(ThreadLocalRandom.current().nextInt(quotes.size()));
        return ResponseEntity.ok(randomQuote);
    }
}

