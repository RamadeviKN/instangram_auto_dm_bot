package com.rknfashions.instangram_auto_dm_bot.service;

import com.rknfashions.instangram_auto_dm_bot.client.InstagramApiClient;
import com.rknfashions.instangram_auto_dm_bot.model.User;
import com.rknfashions.instangram_auto_dm_bot.repository.UserRepository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class InstagramBotService {

    private static final Logger logger = LoggerFactory.getLogger(InstagramBotService.class);

    @Value("${instagram.graph.api.token}")
    private String accessToken;

    @Value("${instagram.graph.api.url}")
    private String graphApiUrl;

    private final WebClient webClient;

    public InstagramBotService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(graphApiUrl).build();
    }

    public String sendAutoDM(String recipientId, String message) {
        String url = String.format("/me/messages?access_token=%s", accessToken);

        // Payload
        Map<String, Object> payload = Map.of(
            "recipient", Map.of("id", recipientId),
            "message", Map.of("text", message)
        );
        WebClient webClient = WebClient.create("https://graph.instagram.com");

        String payload1 = String.format("{\"recipient\":{\"id\":\"%s\"},\"message\":{\"text\":\"%s\"}}", recipientId, message);
        logger.info("Payload body content : "+ payload1);


        try {
            logger.info("Sending message to Instagram API...");
            logger.info("URL is " + url);
            webClient.post()                 
                .uri("/me/messages?access_token=" + accessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload1)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> logger.info("Instagram API Response: {}", response))
                .doOnError(e -> logger.error("Error while sending message: ", e))
                .block();
                logger.info("Sending automatic DM to sender: " + recipientId);
                logger.debug("Response from Instagram API: ");

                return("Webhook processed successfully!");
        } catch (Exception e) {
            logger.error("Exception while sending message: ", e);
            return("Webhook processing unsuccessful!");
        }
    }
}

