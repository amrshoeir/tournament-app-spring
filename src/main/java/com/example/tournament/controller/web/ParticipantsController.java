package com.example.tournament.controller.web;

import com.example.tournament.model.Game;
import com.example.tournament.model.Player;
import com.example.tournament.service.GameService;
import com.example.tournament.service.PlayerService;
import com.example.tournament.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ParticipantsController {
    private GameService gameService;
    private PlayerService playerService;
    private TeamService teamService;

    public ParticipantsController(GameService gameService,
                                  PlayerService playerService,
                                  TeamService teamService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.teamService = teamService;
    }
    @GetMapping("/participants")
    public String getParticipants(Model model){
        model.addAttribute("players", playerService.getPlayers());
        model.addAttribute("teams", teamService.getTeams());
        return "part";
    }
    @GetMapping("/participants/player/edit/{id}")
    public String getEditForm(Model model,@PathVariable("id") Long id){
        model.addAttribute("player",playerService.getPlayer(id));
        return "edit_player";
    }
    @PostMapping("/participants/player/edit/{id}")
    public String editPlayer(@ModelAttribute("player") Player player){
        playerService.save(player);
        return "redirect:/participants";
    }
    @GetMapping("/participants/player/{id}")
    public String deletePlayer(@PathVariable("id") Long id){
        playerService.deletePlayer(id);
        return "redirect:/participants";
    }
    @GetMapping("/participants/team/{id}")
    public String deleteTeam(@PathVariable("id") Long id){
        teamService.deleteTeam(id);
        return "redirect:/participants";
    }
}
