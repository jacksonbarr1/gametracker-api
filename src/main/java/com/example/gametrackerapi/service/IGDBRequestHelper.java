package com.example.gametrackerapi.service;

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

    public ResponseEntity<String> post(String destination, String body) {
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        String baseUrl = "https://api.igdb.com/v4";
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + destination, entity, String.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED || response.getStatusCode() == HttpStatus.FORBIDDEN) {
            throw new InvalidAccessKeyException();
        }

        return response;
    }

    public String getArtworkURL(int artworkID) {
        String body = "fields url; where id=" + artworkID + ";";
        ResponseEntity<String> response = post("/artworks", body);
        return response.getBody();
    }

    public String getGenreName(int genreID) {
        String body = "fields name; where id=" + genreID + ";";
        ResponseEntity<String> response = post("/genres", body);
        return response.getBody();
    }
}
