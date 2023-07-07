package com.example.gametrackerapi.repository;

import com.example.gametrackerapi.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, Integer> {
}
