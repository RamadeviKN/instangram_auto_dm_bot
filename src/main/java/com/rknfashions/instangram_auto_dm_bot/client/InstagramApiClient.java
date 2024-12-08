package com.rknfashions.instangram_auto_dm_bot.client;

import com.rknfashions.instangram_auto_dm_bot.config.InstagramConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InstagramApiClient {

    @Autowired
    private InstagramConfig instagramConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getUserProfile(String userId) {
        String url = instagramConfig.getBaseUrl() + "/" + userId + "?access_token=" + instagramConfig.getAccessToken();
        return restTemplate.getForObject(url, String.class);
    }

    public String sendMessage(String recipientId, String message) {
        String url = instagramConfig.getBaseUrl() + "/me/messages?access_token=" + instagramConfig.getAccessToken();
        String payload = String.format(
            "{\"recipient\": {\"id\": \"%s\"}, \"message\": {\"text\": \"%s\"}}",
            recipientId, message
        );
        return restTemplate.postForObject(url, payload, String.class);
    }
}
