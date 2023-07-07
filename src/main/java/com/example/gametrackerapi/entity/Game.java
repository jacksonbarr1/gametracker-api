package com.example.gametrackerapi.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "games")
@Data
public class Game {
    @Id
    private int id;

    private String name;

    private String summary;

    private List<String> artworkURLs;

    private List<String> genres;
}
