package com.rknfashions.instangram_auto_dm_bot.controller;

import com.rknfashions.instangram_auto_dm_bot.service.InstagramBotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @Autowired
    private InstagramBotService instagramBotService;

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
        try {
            logger.info("Received webhook payload: {}", payload);
            String senderId = extractSenderId(payload);
            String messageText = extractMessageText(payload);

            instagramBotService.sendAutoDM(senderId, "Thank you for your message!");

            return ResponseEntity.ok("Webhook processed successfully.");
        } catch (Exception e) {
            logger.error("Error processing webhook: ", e);
            return ResponseEntity.badRequest().body("Error processing webhook.");
        }
    }

    private String extractSenderId(Map<String, Object> payload) {
        // Check if 'entry' is null or empty
        if (payload.get("entry") == null || ((List) payload.get("entry")).isEmpty()) {
            throw new IllegalArgumentException("No entry data in the webhook payload");
        }
    
        // Cast "entry" to a List<Map<String, Object>>
        List<Map<String, Object>> entryList = (List<Map<String, Object>>) payload.get("entry");
    
        // Get the first entry map from the list
        Map<String, Object> entry = entryList.get(0);
    
        // Check if 'messaging' is null or empty
        if (entry.get("messaging") == null || ((List) entry.get("messaging")).isEmpty()) {
            throw new IllegalArgumentException("No messaging data in the entry");
        }
    
        // Cast "messaging" to a List<Map<String, Object>>
        List<Map<String, Object>> messagingList = (List<Map<String, Object>>) entry.get("messaging");
    
        // Get the first messaging map from the list
        Map<String, Object> messaging = messagingList.get(0);
    
        // Check if 'sender' is present
        if (messaging.get("sender") == null) {
            throw new IllegalArgumentException("No sender data in the messaging");
        }
    
        // Cast the sender object to a Map<String, Object>
        Map<String, Object> sender = (Map<String, Object>) messaging.get("sender");
    
        // Extract the "id" field from the sender map
        return (String) sender.get("id");
    }
    

    private String extractMessageText(Map<String, Object> payload) {
        // Check if 'entry' is null or empty
        if (payload.get("entry") == null || ((List) payload.get("entry")).isEmpty()) {
            throw new IllegalArgumentException("No entry data in the webhook payload");
        }
    
        // Cast "entry" to a List<Map<String, Object>>
        List<Map<String, Object>> entryList = (List<Map<String, Object>>) payload.get("entry");
    
        // Get the first entry map from the list
        Map<String, Object> entry = entryList.get(0);
    
        // Check if 'messaging' is null or empty
        if (entry.get("messaging") == null || ((List) entry.get("messaging")).isEmpty()) {
            throw new IllegalArgumentException("No messaging data in the entry");
        }
    
        // Cast "messaging" to a List<Map<String, Object>>
        List<Map<String, Object>> messagingList = (List<Map<String, Object>>) entry.get("messaging");
    
        // Get the first messaging map from the list
        Map<String, Object> message = messagingList.get(0);
    
        // Check if 'message' is null
        if (message.get("message") == null) {
            throw new IllegalArgumentException("No message data in the messaging");
        }
    
        // Extract the "message" map and get the "text" field
        Map<String, Object> messageMap = (Map<String, Object>) message.get("message");
    
        // Check if "text" is present
        if (messageMap.get("text") == null) {
            throw new IllegalArgumentException("No text data in the message");
        }
    
        // Return the "text" field
        return (String) messageMap.get("text");
    }
    
}
