package com.example.demo.controller;

import com.example.demo.service.SuggestionsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SuggestionsController.class)
public class SuggestionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuggestionsService suggestionsService;

    @Test
    public void testSuggestionsEndpoint() throws Exception {
        // Mock the behavior of SuggestionsService
        List<String> expectedSuggestions = Arrays.asList("suggestion1", "suggestion2");
        Mockito.when(suggestionsService.getSuggestions()).thenReturn(expectedSuggestions);

        // Perform GET request to /suggestions endpoint
        mockMvc.perform(get("/suggestions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value("suggestion1"))
                .andExpect(jsonPath("$[1]").value("suggestion2"));
    }
}
