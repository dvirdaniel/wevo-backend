package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class FeedReaderService {

    private static final String SOURCE = "https://medium.com/feed/";
    @Autowired
    private SuggestionsService suggestionsService;

    public String get(String value) {

        try {
            // URL to send the GET request to
            String url = SOURCE + value;

            // Create a URL object from the specified URL
            URL obj = new URL(url);

            // Open a connection to the URL
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method to GET
            con.setRequestMethod("GET");

            // Get the response code
            int responseCode = con.getResponseCode();
            System.out.println("Content Type: " + con.getContentType());
            System.out.println("Response Code: " + responseCode);

            // Read the response from the server
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            bufferedReader.lines().forEach((inputLine) -> {response.append(inputLine);});
            bufferedReader.close();

            if (!response.isEmpty()) {
                this.suggestionsService.addSuggestion(value);

                // Print and return the response
                // System.out.println("Response Body: " + response); // TODO: can enable it for logging
                return response.toString();
            }
        } catch (IOException e) {
            System.err.println("Error getting response because: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}

