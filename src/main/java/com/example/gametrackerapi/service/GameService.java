package com.example.gametrackerapi.service;

import com.example.gametrackerapi.entity.Game;

public interface GameService {
    String findById(int id);

    Game addById(int id);
}
