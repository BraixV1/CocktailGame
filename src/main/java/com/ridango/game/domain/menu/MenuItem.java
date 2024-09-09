package com.ridango.game.domain.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Setter
@Getter
public class MenuItem {

    public String MenuLabel = "";

    public String description = "";

    public String ShortCut = "";

    public Function<Void, String> MethodToRun = null;

    public Function<EMenuLevel, String> SubMenuToRun = null;


    public MenuItem(String shortCut, String description, Function<Void, String> methodToRun) {

        this.ShortCut = shortCut;
        this.description = description;
        this.MethodToRun = methodToRun;

    }
}

