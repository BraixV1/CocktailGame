package com.ridango.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "GAME_COCKTAILS")
@Getter
@Setter
public class GameCocktails extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cocktail_id")
    public Cocktail coctail;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    public Game game;

}
