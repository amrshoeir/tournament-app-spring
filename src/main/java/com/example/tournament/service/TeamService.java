package com.example.tournament.service;

import com.example.tournament.model.Player;
import com.example.tournament.model.Team;
import com.example.tournament.repository.GameRepository;
import com.example.tournament.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;
    private GameRepository gameRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<Team> getTeams(){
        return teamRepository.findAll();
    }
    public Optional<Team> getTeam(Long id){
        return teamRepository.findById(id);
    }

    public void save(Team team){
        teamRepository.save(team);
    }
    public void updateTeam(Long id, String name, String captainName, String captainEmail){
        Team team = teamRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Team with the id " + id + " does not exist")
        );
        if(name.length()>0){
            team.setName(name);
        }
        if(captainName.length()>0){
            team.setCaptainName(captainName);
        }
        if(captainEmail.length()>0){
            team.setCaptainEmail(captainEmail);
        }
    }
    public void deleteTeam(Long id){
        if (!teamRepository.existsById(id)) {
            throw new IllegalStateException("No existing team with the id " + id);
        }
        teamRepository.deleteById(id);
    }
}
