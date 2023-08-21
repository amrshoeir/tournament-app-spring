package com.example.tournament.controller.rest;

import com.example.tournament.model.Team;
import com.example.tournament.service.TeamService;
import javassist.NotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path="/api/teams",produces="application/json",consumes="application/json")
public class TeamRestController {
    private TeamService teamService;

    public TeamRestController(@Autowired TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> teams = this.teamService.getTeams();
        return ResponseEntity.ok(teams);
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") Long id) throws Exception {
        Team team = teamService.getTeam(id).orElseThrow(()->
                new Exception(String.format("No valid id: %d for team",id)));
        return ResponseEntity.ok(team);
    }
    // http POST :8080/api/teams |
    // req body: '{"name":"Immortals","captainName":"Khaled","captainEmail":"khalood@gmail.com","Game":{'Apex','3v3 Elimination bracket. You can only sign up as a team',3,300000,'2023-06-06'}}'
    @PostMapping
    public ResponseEntity<Void> registerTeam(@RequestBody Team team){
        teamService.save(team);
        return ResponseEntity.ok().build();
    }
    // http PUT :8080/api/teams/3}
    // | req params: ?name="Goulash?captainName=Amoor?captainEmail=ammoor@gmail.com
    @PutMapping(value="/{id}")
    public ResponseEntity<Void> editTeam(@PathVariable("id") Long id,
            @RequestParam String name,
            @RequestParam(required = false) String captainName,
            @RequestParam(required = false) String captainEmail) {
        this.teamService.updateTeam(id,name,captainName,captainEmail);
        return ResponseEntity.ok().build();
    }
    // http DELETE :8080/api/teams/2
    @DeleteMapping(path="{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long id){
        teamService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }
}
