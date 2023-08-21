package com.example.tournament.repository;

import com.example.tournament.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository
        extends JpaRepository<Game,Long> {
}
