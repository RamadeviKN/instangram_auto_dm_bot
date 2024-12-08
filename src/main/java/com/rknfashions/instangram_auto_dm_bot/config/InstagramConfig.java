package com.rknfashions.instangram_auto_dm_bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstagramConfig {
    @Value("${instagram.graph.api.url}")
    private String baseUrl;

    @Value("${instagram.graph.api.token}")
    private String accessToken;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }
}

