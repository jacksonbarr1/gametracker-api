package com.example.gametrackerapi.service;

import com.example.gametrackerapi.dto.GameDTO;
import com.example.gametrackerapi.entity.Game;
import com.example.gametrackerapi.repository.GameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    @Autowired
    private final GameRepository gameRepository;
    private final IGDBRequestHelper dispatcher;

    public String findById(int id) {
        String body = "fields *; where id=" + id + ";";
        ResponseEntity<String> response = dispatcher.post("/games", body);
        return response.toString();
    }

    public Game addById(int id) {
        String body = "fields id, name, summary, artworks, genres; where id=" + id + ";";
        String jsonResponse = dispatcher.post("/games", body).getBody();

        ObjectMapper mapper = new ObjectMapper();
        GameDTO gameDTO = null;

        try {
            JsonNode root = mapper.readTree(jsonResponse);
            if (root.isArray() && root.size() > 0) {
                gameDTO = mapper.treeToValue(root.get(0), GameDTO.class);
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Game game = fromDTO(gameDTO);
        return gameRepository.save(game);
    }

    private Game fromDTO(GameDTO gameDTO) {
        Game game = new Game();
        ObjectMapper mapper = new ObjectMapper();

        game.setId(gameDTO.getId());
        game.setName(gameDTO.getName());
        game.setSummary(gameDTO.getSummary());

        List<String> artworkURLs = new ArrayList<>();
        for (int i = 0; i < 3 && i < gameDTO.getArtworkIds().size(); i++) {
           String jsonResponse = dispatcher.getArtworkURL(gameDTO.getArtworkIds().get(i));
           try {
               JsonNode root = mapper.readTree(jsonResponse);
               if (root.isArray() && root.size() > 0) {
                   String url = root.get(0).get("url").asText();
                   artworkURLs.add(url.substring(2));
               }
           } catch (JsonMappingException e) {
               throw new RuntimeException(e);
           } catch (JsonProcessingException e) {
               throw new RuntimeException(e);
           }
        }
        game.setArtworkURLs(artworkURLs);

        List<String> genreNames = new ArrayList<>();
        for (int i = 0; i < 3 && i < gameDTO.getGenreIds().size(); i++) {
            String jsonResponse = dispatcher.getGenreName(gameDTO.getGenreIds().get(i));
            try {
                JsonNode root = mapper.readTree(jsonResponse);
                if (root.isArray() && root.size() > 0) {
                    String genre = root.get(0).get("name").asText();
                    genreNames.add(genre);
                }
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        game.setGenres(genreNames);

        System.out.println(game);
        return game;
    }
}
