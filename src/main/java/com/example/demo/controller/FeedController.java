package com.example.demo.controller;

import com.example.demo.service.FeedReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FeedController {

    @Autowired
    private FeedReaderService feedReaderService;

    @GetMapping(value={"/feed", "/feed/", "/feed/{value}"}, produces = "application/xml")
    public String feed(@PathVariable(name="value", required = false) String value) {
        String result = null;
        if (value != null) {
            result = this.feedReaderService.get(value);
        }
        return result;
    }
}
