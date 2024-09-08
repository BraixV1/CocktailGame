package com.ridango.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Setter
@Getter
public class MenuItem {

    public String MenuLabel = "";

    public Function<Void, String> MenuLabelFunction;

    public String ShortCut = "";

    public Function<Void, String> MethodToRun = null;

    public Function<EMenuLevel, String> SubMenuToRun = null;


    public MenuItem(String shortCut, Function<Void, String> menuLabelFunction, Function<Void, String> methodToRun) {

        this.ShortCut = shortCut;
        this.MenuLabelFunction = menuLabelFunction;
        this.MethodToRun = methodToRun;

    }
}

