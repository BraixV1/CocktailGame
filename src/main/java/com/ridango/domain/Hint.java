package com.ridango.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Hint extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "game_id")
    public Game game;

    @Column(name = "showStrDrinkTumb")
    public boolean showStrDrinkThumb = false;

    @Column(name = "showStrIngredient1")
    public boolean showStrIngredient1 = false;

    @Column(name = "showStrIngredient2")
    public boolean showStrIngredient2 = false;

    @Column(name = "showStrIngredient3")
    public boolean showStrIngredient3 = false;

    @Column(name = "showStrIngredient4")
    public boolean showStrIngredient4 = false;

    @Column(name = "showStrIngredient5")
    public boolean showStrIngredient5 = false;

    @Column(name = "showStrIngredient6")
    public boolean showStrIngredient6 = false;

    @Column(name = "showStrIngredient7")
    public boolean showStrIngredient7 = false;

    @Column(name = "showStrIngredient8")
    public boolean showStrIngredient8 = false;

    @Column(name = "showStrIngredient9")
    public boolean showStrIngredient9 = false;

    @Column(name = "showStrIngredient10")
    public boolean showStrIngredient10 = false;

    @Column(name = "showStrIngredient11")
    public boolean showStrIngredient11 = false;

    @Column(name = "showStrIngredient12")
    public boolean showStrIngredient12 = false;

    @Column(name = "showStrIngredient13")
    public boolean showStrIngredient13 = false;

    @Column(name = "showStrIngredient14")
    public boolean showStrIngredient14 = false;

    @Column(name = "showStrIngredient15")
    public boolean showStrIngredient15 = false;




}
