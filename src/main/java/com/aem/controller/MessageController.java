package com.aem.controller;

import com.aem.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Rest controller to get message
 */
@RestController("/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @GetMapping
    public String getMessage() {
        try {
            return service.process().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
