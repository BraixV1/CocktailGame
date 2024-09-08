package com.ridango.domain;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table
@Getter
@Setter
public class Game extends BaseEntity {


    @Timestamp
    public Date createdAtDt;


    @Timestamp
    public Date LastPlayedDt;

    public String title;

    public Integer triesLeft;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User Player;

    public Integer score = 0;

    public UUID CoctailId;

    @ManyToOne
    public Cocktail currentCoctail = null;

    public String revealedName = "";


    @OneToMany
    public List<GameCocktails> usedCocktails ;

    @OneToOne
    public Hint hint;


}
