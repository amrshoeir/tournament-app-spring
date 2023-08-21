package com.example.tournament.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.*;

@Entity
@Table(name = Game.TBL_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Game extends AbstractEntity {
    public static final String TBL_NAME = "games";
    public static final String FLD_DESCRIPTION = "description";
    public static final String FLD_TEAM_SIZE = "team_size";
    public static final String FLD_PRIZE_POOL = "prize_pool";

    @Column(name = FLD_DESCRIPTION, nullable = false, length = 350)
    @NotNull(message = "description exists to explain game's tournament rules for players and teams")
    private String description;
    @Column(name = FLD_TEAM_SIZE, nullable = false, length = 10)
    @NotNull(message = "Include game's team size")
    private int teamSize;
    @Column(name = FLD_PRIZE_POOL, nullable = false)
    @NotNull(message = "Include Prize Pool")
    private int prizePool;
}
