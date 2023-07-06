package com.example.gametrackerapi.util;

import com.example.gametrackerapi.core.exception.InvalidAccessKeyException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


public class IGDBRequestHelper {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public IGDBRequestHelper(String clientId, String accessToken) {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();

        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Client-ID", clientId);
        headers.set("Authorization", "Bearer " + accessToken);
    }

    public ResponseEntity<String> post(String uri, String body) {
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED || response.getStatusCode() == HttpStatus.FORBIDDEN) {
            throw new InvalidAccessKeyException();
        }

        return response;
    }
}
