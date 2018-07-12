package com.example.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Value("${my.message}")
    private String message;

    @GetMapping("/getMessage")
    public String getMessage() {
        return message;
    }
}
