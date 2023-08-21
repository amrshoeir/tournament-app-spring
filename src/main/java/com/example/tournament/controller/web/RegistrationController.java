package com.example.tournament.controller.web;
;
import com.example.tournament.model.Player;
import com.example.tournament.model.Team;
import com.example.tournament.service.GameService;
import com.example.tournament.service.PlayerService;
import com.example.tournament.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    private GameService gameService;
    private PlayerService playerService;
    private TeamService teamService;

    public RegistrationController(GameService gameService,
                                  PlayerService playerService,
                                  TeamService teamService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @GetMapping("/register")
    public String getReg(Model model){
        model.addAttribute("games", gameService.getGames());
        return "reg";
}
    @GetMapping("/register/team/{id}")
    public String getTeamForm(Model model, @PathVariable("id") Long id){
        Team team = new Team();
        model.addAttribute("team",team);
        model.addAttribute("game",gameService.getGame(id));
        return "reg_team";
    }
    @GetMapping("/register/player/{id}")
    public String getPlayerForm(Model model, @PathVariable ("id") Long id){
        Player player = new Player();
        model.addAttribute("game",gameService.getGame(id));
        model.addAttribute("player",player);
    return "reg_player";
    }
    @PostMapping("/register/player/{id}")
    public String regPlayer( Player player,@PathVariable("id") Long id){
        player.setId(null);
        player.setGame(gameService.getGame(id));
        playerService.save(player);
        return "redirect:/success";
    }
    @PostMapping("/register/team/{id}")
    public String regTeam(Team team,@PathVariable("id") Long id){
        team.setId(null);
        team.setGame(gameService.getGame(id));
        teamService.save(team);
        return "redirect:/success";
    }
}
