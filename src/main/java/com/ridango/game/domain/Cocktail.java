package com.ridango.game.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COCKTAIL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cocktail extends BaseEntity {

    @Column(name = "idDrink")
    public Integer idDrink;

    @Column(name = "strDrink")
    public String strDrink;

    @Column(name = "strInstructions")
    public String strInstructions;

    @Column(name = "strDrinkThumb")
    public String strDrinkThumb;

    @Column(name = "strIngredient1")
    public String strIngredient1;

    @Column(name = "strIngerient2")
    public String strIngredient2;

    @Column(name = "strIngredient3")
    public String strIngredient3;

    @Column(name = "strIngredient4")
    public String strIngredient4;

    @Column(name = "strIngredient5")
    public String strIngredient5;

    @Column(name = "strIngredient6")
    public String strIngredient6;

    @Column(name = "strIngredient7")
    public String strIngredient7;

    @Column(name = "strIngredient8")
    public String strIngredient8;

    @Column(name = "strIngredient9")
    public String strIngredient9;

    @Column(name = "strIngredient10")
    public String strIngredient10;

    @Column(name = "strIngredient11")
    public String strIngredient11;

    @Column(name = "strIngredient12")
    public String strIngredient12;

    @Column(name = "strIngredient13")
    public String strIngredient13;

    @Column(name = "strIngredient14")
    public String strIngredient14;

    @Column(name = "strIngredient15")
    public String strIngredient15;

}
