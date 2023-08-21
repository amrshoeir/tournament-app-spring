package com.example.tournament.controller.rest;

import com.example.tournament.model.Game;
import com.example.tournament.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/games",produces="application/json",consumes="application/json")
public class GameRestController {
    private final GameService gameService;

    @Autowired
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    //http :8080/api/games
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(this.gameService.getGames());
    }

    //http :8080/api/games/1
    @GetMapping(value=("/{id}"))
    public ResponseEntity<Game> getGame(@PathVariable("id") Long id){
        Game game = this.gameService.getGame(id);
        return ResponseEntity.ok(game);
    }
// req body: "{'It takes two','Speed run tournament style',2,200000,'2023-05-27'}" | http POST :8080/api/games
    @PostMapping
    public ResponseEntity<Void> addGame(@RequestBody Game game){
        this.gameService.addGame(game);
        return ResponseEntity.ok().build();
    }


    // req body: "{'Apex legends','',2,200000,'2023-05-27'}" | http POST :8080/api/games/3
    @PutMapping(path={"/{id}"})
    public ResponseEntity<Void> updateGame(@PathVariable("id") Long id,
                           @RequestParam String name,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) int prizePool,
                           @RequestParam int teamSize){
        this.gameService.updateGame(id,name,description,teamSize,prizePool);
        return ResponseEntity.ok().build();
    }
 // http DELETE :8080/api/games/2
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") Long id){
        this.gameService.deleteGame(id);
        return ResponseEntity.ok().build();
    }

}
