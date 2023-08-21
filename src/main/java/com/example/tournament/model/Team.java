package com.example.tournament.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = Team.TBL_NAME)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Team extends AbstractEntity{
    public static final String TBL_NAME = "teams";
    public static final String FLD_CAPTAIN_NAME = "captain_name";
    public static final String FLD_CAPTAIN_EMAIL = "captain_email";
    public static final String FLD_GAME = "game_id";
    @Column(name = FLD_CAPTAIN_NAME, nullable = false)
    @NotNull(message = "Can't be empty")
    private String captainName;
    @Column(name = FLD_CAPTAIN_EMAIL, nullable = false)
    @NotNull(message = "Please add email")
    @Email(message="Enter a valid email")
    private String captainEmail;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = FLD_GAME)
    private Game game;
}
