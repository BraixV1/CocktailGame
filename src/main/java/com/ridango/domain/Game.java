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
    @Column(updatable = false, name = "createdAtDt")
    public Date createdAtDt = new Date();


    @Timestamp
    @Column(name = "lastPlayedDt")
    public Date LastPlayedDt = new Date();


    @Column(name = "title")
    public String title = "";

    @Column(name = "triesLeft")
    public Integer triesLeft = 5;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User Player;

    public Integer score = 0;

    @ManyToOne
    @JoinColumn(name = "cocktail_id")
    public Cocktail currentCocktail;

    public String revealedName = "";


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cocktail_id")
    public List<GameCocktails> usedCocktails = new ArrayList<>();

    @OneToOne
    public Hint hint;

}