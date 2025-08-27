package com.example.dashboard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    private final SimpMessagingTemplate messagingTemplate;

    public DashboardController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // via WebSocket (STOMP)
    @MessageMapping("/send")
    @SendTo("/topic/updates")
    public String sendMessage(String message) {
        return message;
    }

    // via REST API (Postman / external service)
    @PostMapping("/api/send")
    public String apiSend(@RequestBody String message) {
        // kirim ke semua subscriber
        messagingTemplate.convertAndSend("/topic/updates", message);
        return "Message sent: " + message;
    }

}
