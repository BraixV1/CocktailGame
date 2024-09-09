package com.ridango.game.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "GAME_COCKTAIL")
@Getter
@Setter
public class GameCocktails extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COCKTAIL_ID")
    private Cocktail cocktail;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GAME_ID")
    private Game game;
}
