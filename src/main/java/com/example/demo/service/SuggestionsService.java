package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;


@Service
public class SuggestionsService {
    private static final int capacity = 5;
    private Queue<String> queue = new ArrayDeque<>(capacity);

    public List<String> getSuggestions() {
        return this.queue.stream().toList();
    }

    public void addSuggestion(String suggestion) {

        // If the queue doesn't contain the suggestion
        if (!this.queue.contains(suggestion)) {

            // If the queue reaches capacity then remove the head of this queue
            if (this.queue.size() == capacity) {
                this.queue.poll();
            }
            this.queue.offer(suggestion);
        }
    }

    public void clearSuggestions() {
        this.queue.clear();
    }

}

