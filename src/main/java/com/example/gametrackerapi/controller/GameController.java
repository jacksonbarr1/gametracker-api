package com.example.gametrackerapi.controller;

import com.example.gametrackerapi.entity.Game;
import com.example.gametrackerapi.service.GameService;
import com.example.gametrackerapi.service.IGDBRequestHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @GetMapping("/findById")
    String findById(@RequestParam int id) {
        return gameService.findById(id);
    }

    @PostMapping("/addById")
    Game addById(@RequestParam int id) {
        return gameService.addById(id);
    }

}
