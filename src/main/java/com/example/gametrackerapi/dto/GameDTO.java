package com.example.gametrackerapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GameDTO {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("artworks")
    private List<Integer> artworkIds;

    @JsonProperty("genres")
    private List<Integer> genreIds;
}
