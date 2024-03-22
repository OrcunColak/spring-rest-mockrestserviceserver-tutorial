package com.colak.springresttutorial.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

/**
 * Test a service that uses RestClient.Builder
 */
@RestClientTest(RestClientQuoteService.class)
class RestClientQuoteServiceTest {

    @Autowired
    private RestClientQuoteService restClientQuoteService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    void testGetRandomQuote() {
        // Prepare mock response
        String mockQuote = "This is a mock quote";

        // Define expected request
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/quote/random"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(mockQuote, MediaType.TEXT_PLAIN));

        // Invoke service method
        String actualQuote = restClientQuoteService.getRandomQuote();

        // Verify that the service method returned the expected quote
        Assertions.assertThat(actualQuote).isEqualTo(mockQuote);

        // Verify that the expected request was sent
        mockServer.verify();
    }
}
