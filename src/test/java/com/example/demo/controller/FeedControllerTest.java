package com.example.demo.controller;

import com.example.demo.service.FeedReaderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FeedReaderService feedReaderService;

    @Test
    public void testFeedControllerWithValue() throws Exception {
        String value = "@dvirdaniel";
        final String contentType = MediaType.APPLICATION_XML+";charset="+StandardCharsets.UTF_8.name();

        // Perform GET request with value
        mockMvc.perform(get("/feed/{value}", value))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string(Matchers.not("")));
    }

    @Test
    public void testFeedControllerWithoutValue() throws Exception {

        // Perform GET request without value
        mockMvc.perform(get("/feed"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}