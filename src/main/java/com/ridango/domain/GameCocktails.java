package com.ridango.domain;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class GameCocktails extends BaseEntity {

    @ManyToOne
    public Cocktail coctail;


    @ManyToOne
    public Game game;




    public Cocktail getCoctail() {
        return coctail;
    }

    public void setCoctail(Cocktail coctail) {
        this.coctail = coctail;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }





}
