package com.example.tournament.service;

import com.example.tournament.model.Game;
import com.example.tournament.repository.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() { return gameRepository.findAll(); }
    public Game getGame(Long id) {
        return gameRepository.findById(id).orElseThrow();
    }

    public void addGame(@RequestBody Game game){
        gameRepository.save(game);
    }
    public void updateGame(Game game){gameRepository.save(game);}
    @Transactional
    public void updateGame(Long id, String name, String description, int teamSize, int prizePool){
        Game game = gameRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Game with the id " + id + " does not exist")
        );
        if(name.length()>0){
            game.setName(name);
        }
        if(description.length()>10){
            game.setDescription(description);
        }
        if(teamSize>0 && teamSize<10){
            game.setTeamSize(teamSize);
        }
        if(prizePool>10000){
            game.setPrizePool(prizePool);
        }
    }
    public void deleteGame(Long id){
        if (!gameRepository.existsById(id)) {
            throw new IllegalStateException("No existing game with the id " + id);
        }
        gameRepository.deleteById(id);
    }

}
