package com.ridango.domain;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table
@Getter
@Setter
public class Game extends BaseEntity {

    @Timestamp
    public Date createdAtDt = new Date();


    @Timestamp
    public Date LastPlayedDt = new Date();

    public String title = "";

    public Integer triesLeft = 5;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User Player;

    public Integer score = 0;

    @ManyToOne
    public Cocktail currentCocktail;

    public String revealedName = "";


    @OneToMany
    public List<GameCocktails> usedCocktails = new ArrayList<>();

    @OneToOne
    public Hint hint;

}