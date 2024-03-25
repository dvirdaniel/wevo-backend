package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@SpringBootTest
public class FeedReaderServiceTest {

    @MockBean
    private SuggestionsService suggestionsService;

    @InjectMocks
    private FeedReaderService feedReaderService;

    @Mock
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetFeed() {
        String value = "@dvirdaniel";

        // Mock the behavior of the SuggestionsService
        Mockito.doNothing().when(suggestionsService).addSuggestion(value);

        // Mock the HTTP request and response
        mockServer.expect(requestTo("https://medium.com/feed/" + value))
                .andRespond(MockRestResponseCreators.withSuccess());

        // Call the get method
        String actualResponse = feedReaderService.get(value);

        // Verify that the SuggestionsService method was called
        Mockito.verify(suggestionsService).addSuggestion(value);

        // Assert that the response is not null and matches the expected response
        assertThat(actualResponse).isNotNull();
    }
}
