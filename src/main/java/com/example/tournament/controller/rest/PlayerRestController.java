package com.example.tournament.controller.rest;

import com.example.tournament.model.Game;
import com.example.tournament.model.Player;
import com.example.tournament.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="/api/players",produces="application/json",consumes="application/json")
public class PlayerRestController {
    private PlayerService playerService;

    public PlayerRestController(@Autowired PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping
    public List<Player> getPlayers(){
        return playerService.getPlayers();
    }

    // http POST :8080/api/teams |
    // req body: '{"name":"Bilal","age":"22","email":"billoz@gmail.com","gameTag":"billz","Game":{'Fortnite','25 players Battle royale. Last 5 standing are chosen.',1,100000,'2023-06-06'}}'
    @PostMapping
    public void registerPlayer(@RequestBody Player player){
        this.playerService.save(player);
    }
    // http PUT :8080/api/players/1 |
    // req params: /?name=Martaa?age=16
    @PutMapping(path={"/{id}"})
    public ResponseEntity<Void> updatePlayer(@PathVariable("id") Long id,
                                           @RequestParam String name,
                                           @RequestParam(required = false) String gameTag,
                                           @RequestParam(required = false) int age,
                                           @RequestParam(required = false) String email){
        this.playerService.updatePlayer(id,name,gameTag,age,email);
        return ResponseEntity.ok().build();
    }

    // http DELETE :8080/api/players/2
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }
}
