package com.ridango.game.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GAME")
@Getter
@Setter
public class Game extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "createdAtDt")
    private Date createdAtDt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastPlayedDt")
    private Date lastPlayedDt = new Date();

    @Column(name = "title")
    private String title = "";

    @Column(name = "triesLeft")
    private Integer triesLeft = 5;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User player;


    @Column(name = "score")
    private Integer score = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COCKTAIL_ID")
    private Cocktail currentCocktail;

    @Column(name = "revealedName")
    private String revealedName = "";

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GameCocktails> usedCocktails = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "HINT_ID")
    private Hint hint;

}
