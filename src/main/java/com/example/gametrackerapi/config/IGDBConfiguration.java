package com.example.gametrackerapi.config;

import com.example.gametrackerapi.service.IGDBRequestHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IGDBConfiguration {

    @Value("${igdb.client-id}")
    private String clientId;

    @Value("${igdb.access-token}")
    private String accessToken;

    @Bean
    public IGDBRequestHelper igdbRequestHelper() {
        return new IGDBRequestHelper(clientId, accessToken);
    }
}
