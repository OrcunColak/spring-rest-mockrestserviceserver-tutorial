package com.colak.springresttutorial.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

/**
 * Test a service that uses RestTemplate
 */
@SpringBootTest
class RestTemplateQuoteServiceTest {

    @Autowired
    private RestTemplateQuoteService restTemplateQuoteService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testGetRandomQuote() {
        // Prepare mock response
        String mockQuote = "This is a mock quote";

        // Define expected request
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8080/quote/random"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(mockQuote, MediaType.TEXT_PLAIN));

        // Invoke service method
        String actualQuote = restTemplateQuoteService.getRandomQuote();

        // Verify that the service method returned the expected quote
        Assertions.assertThat(actualQuote).isEqualTo(mockQuote);

        // Verify that the expected request was sent
        mockServer.verify();
    }
}
