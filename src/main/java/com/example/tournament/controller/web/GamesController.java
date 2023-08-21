package com.example.tournament.controller.web;

import com.example.tournament.model.Game;
import com.example.tournament.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GamesController {
    private GameService gameService;

    public GamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public String getGames(Model model){
        model.addAttribute("games",gameService.getGames());
        return "games";
    }
    @GetMapping("/games/add")
    public String addGame(Model model){
        model.addAttribute("game",new Game());
        return "add_game";
    }
    @GetMapping("/games/edit/{id}")
    public String editGame(Model model,@PathVariable("id") Long id){
        model.addAttribute("game",gameService.getGame(id));
        return "edit_game";
    }
    @GetMapping("/games/delete/{id}")
    public String deleteGame(@PathVariable("id") Long id){
        gameService.deleteGame(id);
        return "redirect:/games";
    }
    @PostMapping("/games")
    public String postGame(@ModelAttribute Game game){
        gameService.addGame(game);
        return "redirect:/games";
    }
    @PostMapping("/games/edit/{id}")
    public String editGame(@ModelAttribute("game") Game game){
        gameService.updateGame(game);
        return "redirect:/games";
    }
}
