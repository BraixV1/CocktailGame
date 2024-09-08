package com.ridango.domain;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table
public class Game extends BaseEntity {


    @Timestamp
    public Date createdAtDt;


    @Timestamp
    public Date LastPlayedDt;

    @Timestamp
    public boolean completedAtDt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User Player;

    public Integer score = 0;

    public UUID CoctailId;

    @ManyToOne
    public Cocktail currentCoctail = null;


    @OneToMany
    public List<GameCocktails> usedCocktails ;












    public Date getCreatedAtDt() {
        return createdAtDt;
    }

    public void setCreatedAtDt(Date createdAtDt) {
        this.createdAtDt = createdAtDt;
    }

    public Date getLastPlayedDt() {
        return LastPlayedDt;
    }

    public void setLastPlayedDt(Date lastPlayedDt) {
        LastPlayedDt = lastPlayedDt;
    }

    public User getPlayer() {
        return Player;
    }

    public void setPlayer(User player) {
        Player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Cocktail getCurrentCoctail() {
        return currentCoctail;
    }

    public void setCurrentCoctail(Cocktail currentCoctail) {
        this.currentCoctail = currentCoctail;
    }

    public List<GameCocktails> getUsedCocktails() {
        return usedCocktails;
    }

    public void setUsedCocktails(List<GameCocktails> usedCocktails) {
        this.usedCocktails = usedCocktails;
    }

    public boolean isCompletedAtDt() {
        return completedAtDt;
    }

    public void setCompletedAtDt(boolean completedAtDt) {
        this.completedAtDt = completedAtDt;
    }
}
