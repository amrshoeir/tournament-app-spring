package com.example.tournament.service;

import com.example.tournament.model.Game;
import com.example.tournament.model.Player;
import com.example.tournament.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }
    public Player getPlayer(Long id){
        return playerRepository.findById(id).orElseThrow(()->
                new IllegalStateException(String.format("No player found with id:%d",id)));
    }
    public void save(Player player){
        playerRepository.save(player);
    }
    @Transactional
    public void updatePlayer(Long id, String name, String gameTag, int age, String email){
        Player player = playerRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Game with the id " + id + " does not exist")
        );
        if(name.length()>0){
            player.setName(name);
        }
        if(gameTag.length()>0){
            player.setGameTag(gameTag);
        }
        if(age>13 && age<90){
            player.setAge(age);
        }
        if(email.length()>0){
            player.setEmail(email);
        }
    }

    public void deletePlayer(Long id){
        if (!playerRepository.existsById(id)) {
            throw new IllegalStateException("No existing player with the id " + id);
        }
        playerRepository.deleteById(id);
    }

}
