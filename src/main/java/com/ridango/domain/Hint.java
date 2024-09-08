package com.ridango.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class Hint extends BaseEntity {


    @OneToOne
    public Game game;

    public String revealedInput;

    public boolean showStrDrinkThumb = false;

    public boolean showStrIngredient1 = false;

    public boolean showStrIngredient2 = false;

    public boolean showStrIngredient3 = false;

    public boolean showStrIngredient4 = false;

    public boolean showStrIngredient5 = false;

    public boolean showStrIngredient6 = false;

    public boolean showStrIngredient7 = false;

    public boolean showStrIngredient8 = false;

    public boolean showStrIngredient9 = false;

    public boolean showStrIngredient10 = false;

    public boolean showStrIngredient11 = false;

    public boolean showStrIngredient12 = false;

    public boolean showStrIngredient13 = false;

    public boolean showStrIngredient14 = false;

    public boolean showStrIngredient15 = false;




}
