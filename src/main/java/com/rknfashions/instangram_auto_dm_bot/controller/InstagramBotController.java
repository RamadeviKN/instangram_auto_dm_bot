package com.rknfashions.instangram_auto_dm_bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rknfashions.instangram_auto_dm_bot.service.InstagramBotService;

public class InstagramBotController {

    @Autowired
    private InstagramBotService botService;

    @PostMapping("/send-dm/{username}")
    public ResponseEntity<String> sendDirectMessage(@PathVariable String username, @PathVariable String message) {
        String response = botService.sendAutoDM(username,message);
        return ResponseEntity.ok(response);
    }
    
}
