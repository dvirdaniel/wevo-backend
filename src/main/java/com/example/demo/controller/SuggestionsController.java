package com.example.demo.controller;

import com.example.demo.service.SuggestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SuggestionsController {

    @Autowired
    private SuggestionsService suggestionsService;

    @GetMapping(value = "/suggestions")
    public List<String> suggestions() {
        return this.suggestionsService.getSuggestions();
    }
}
