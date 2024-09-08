package com.ridango.menu;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Setter
@Getter
public class MenuItem {

    public String MenuLabel;

    public Function<Void, String> MenuLabelFunction;

    public String ShortCut;

    public Function<Void, String> MethodToRun;

    public Function<EMenuLevel, String> SubMenuToRun;


}

