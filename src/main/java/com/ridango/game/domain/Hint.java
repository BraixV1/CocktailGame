package com.ridango.game.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "HINT")
@Getter
@Setter
public class Hint extends BaseEntity {

    @Column(name = "showStrDrinkThumb")
    private boolean showStrDrinkThumb = false;

    @Column(name = "showStrIngredient1")
    private boolean showStrIngredient1 = false;

    @Column(name = "showStrIngredient2")
    private boolean showStrIngredient2 = false;

    @Column(name = "showStrIngredient3")
    private boolean showStrIngredient3 = false;

    @Column(name = "showStrIngredient4")
    private boolean showStrIngredient4 = false;

    @Column(name = "showStrIngredient5")
    private boolean showStrIngredient5 = false;

    @Column(name = "showStrIngredient6")
    private boolean showStrIngredient6 = false;

    @Column(name = "showStrIngredient7")
    private boolean showStrIngredient7 = false;

    @Column(name = "showStrIngredient8")
    private boolean showStrIngredient8 = false;

    @Column(name = "showStrIngredient9")
    private boolean showStrIngredient9 = false;

    @Column(name = "showStrIngredient10")
    private boolean showStrIngredient10 = false;

    @Column(name = "showStrIngredient11")
    private boolean showStrIngredient11 = false;

    @Column(name = "showStrIngredient12")
    private boolean showStrIngredient12 = false;

    @Column(name = "showStrIngredient13")
    private boolean showStrIngredient13 = false;

    @Column(name = "showStrIngredient14")
    private boolean showStrIngredient14 = false;

    @Column(name = "showStrIngredient15")
    private boolean showStrIngredient15 = false;


    public void resetHint(){
        showStrDrinkThumb = false;
        showStrIngredient1 = false;
        showStrIngredient2 = false;
        showStrIngredient3 = false;
        showStrIngredient4 = false;
        showStrIngredient5 = false;
        showStrIngredient6 = false;
        showStrIngredient7 = false;
        showStrIngredient8 = false;
        showStrIngredient9 = false;
        showStrIngredient10 = false;
        showStrIngredient11 = false;
        showStrIngredient12 = false;
        showStrIngredient13 = false;
        showStrIngredient14 = false;
        showStrIngredient15 = false;
    }
}
