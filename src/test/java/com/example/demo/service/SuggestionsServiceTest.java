package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SuggestionsServiceTest {

    @Autowired
    private SuggestionsService suggestionsService;

    @BeforeEach
    public void setUp() {
        // Clear the queue before each test
        suggestionsService.clearSuggestions();
    }

    @Test
    public void testAddSuggestion() {
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion2");
        suggestionsService.addSuggestion("suggestion3");

        List<String> suggestions = suggestionsService.getSuggestions();

        assertThat(suggestions).containsExactly("suggestion1", "suggestion2", "suggestion3");
    }

    @Test
    public void testAddDuplicateSuggestion() {
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion2");
        suggestionsService.addSuggestion("suggestion1");

        List<String> suggestions = suggestionsService.getSuggestions();

        assertThat(suggestions).containsExactly("suggestion1", "suggestion2");
    }

    @Test
    public void testQueueCapacity() {
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion2");
        suggestionsService.addSuggestion("suggestion3");
        suggestionsService.addSuggestion("suggestion4");
        suggestionsService.addSuggestion("suggestion5");

        suggestionsService.addSuggestion("suggestion6");

        List<String> suggestions = suggestionsService.getSuggestions();

        assertThat(suggestions).containsExactly("suggestion2", "suggestion3", "suggestion4", "suggestion5", "suggestion6");
    }

    @Test
    public void testQueueCapacityAndDuplicates() {
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion2");
        suggestionsService.addSuggestion("suggestion3");
        suggestionsService.addSuggestion("suggestion4");
        suggestionsService.addSuggestion("suggestion5");

        suggestionsService.addSuggestion("suggestion3");

        List<String> suggestions = suggestionsService.getSuggestions();

        assertThat(suggestions).containsExactly("suggestion1", "suggestion2", "suggestion3", "suggestion4", "suggestion5");
    }

    @Test
    public void testQueueCapacityWithDuplicateSuggestions() {
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion1");
        suggestionsService.addSuggestion("suggestion1");

        List<String> suggestions = suggestionsService.getSuggestions();

        assertThat(suggestions).containsExactly("suggestion1");
    }

    @Test
    public void testEmptyQueue() {
        List<String> suggestions = suggestionsService.getSuggestions();

        assertThat(suggestions).isEmpty();
    }
}
