package com.example.gametrackerapi.controller;

import com.example.gametrackerapi.entity.Game;
import com.example.gametrackerapi.util.IGDBRequestHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private IGDBRequestHelper dispatcher;

    public GameController(@Value("${igdb.client-id}") String clientId,
                          @Value("${igdb.access-token}") String accessToken) {
        dispatcher = new IGDBRequestHelper(clientId, accessToken);
    }

    @GetMapping("/findById")
    String findById(@RequestParam int id) {
        String body = "fields *; where id=" + id + ";";
        ResponseEntity<String> response = dispatcher.post("https://api.igdb.com/v4/games", body);
        return response.toString();
    }
}
