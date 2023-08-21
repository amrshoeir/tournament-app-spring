package com.example.tournament.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = Player.TBL_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Player extends AbstractEntity {
    public static final String TBL_NAME = "players";
    public static final String FLD_AGE = "age";
    public static final String FLD_GAME_TAG = "game_tag";
    public static final String FLD_EMAIL = "email";
    public static final String FLD_GAME = "game_id";


    @Column(name = FLD_AGE, nullable = false)
    @NotNull(message = "Can't be empty")
    @Min(message="Must be at least 13 years old",value = 13)
    private int age;
    @Column(name = FLD_GAME_TAG, nullable = false)
    @NotNull(message = "Please add your in-game tag")
    private String gameTag;
    @Column(name = FLD_EMAIL, nullable = false)
    @NotNull(message = "Please add email")
    @Email(message="Enter a valid email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = FLD_GAME)
    private Game game;
}
