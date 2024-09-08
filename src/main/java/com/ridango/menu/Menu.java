package com.ridango.menu;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContextException;

import java.util.*;

public class Menu {

    @Getter
    @Setter
    public String Title;

    @Getter
    @Setter
    public Map<String, MenuItem> MenuItems;

    private final String MenuSeparator = "===============";
    private static HashSet<String> ReservedShortcuts = new HashSet<>() {};


    public Menu(String title, List<MenuItem> menuItems) {
        this.Title = title;
        for (MenuItem menuItem : menuItems) {
            if (ReservedShortcuts.contains(menuItem.ShortCut.toLowerCase())) {
                throw new ApplicationContextException("Menu shortcut" + menuItem.ShortCut + "in not allowed list!");
            }

            if (MenuItems.containsKey(menuItem.ShortCut.toLowerCase())) {
                throw new ApplicationContextException("Menu shortcut" + menuItem.ShortCut + " is already registered!");
            }

            MenuItems.put(menuItem.ShortCut.toLowerCase(), menuItem);
        }
    }

    private void Draw(EMenuLevel menuLevel) {
        if (Title != null) {
            System.out.println(Title);
            System.out.println(MenuSeparator);
        }

        for (Map.Entry<String, MenuItem> entry : MenuItems.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(") ");
            System.out.println(entry.getValue().MenuLabelFunction != null ? entry.getValue().MenuLabelFunction : entry.getValue().MenuLabel);
        }

        if (menuLevel != EMenuLevel.First) {
            System.out.println("b) Back");
        }
        if (menuLevel == EMenuLevel.Other) {
            System.out.println("r) Return to main menu");
        }

        System.out.println("x) Exit");

        System.out.println(MenuSeparator);

        System.out.print("Your choice: ");
    }


    public String Run(){
        return Run(EMenuLevel.First);
    }

    public String Run(EMenuLevel menuLevel) {
        clearConsole();

        String userChoice = "";
        Scanner scanner = new Scanner(System.in);

        do{
            Draw(menuLevel);
            userChoice = scanner.nextLine().trim();

            if (getMenuItems().containsKey(userChoice)) {
                MenuItem selectedItem = getMenuItems().get(userChoice);
                if (selectedItem.getSubMenuToRun() != null) {
                    String result = "";
                    if (menuLevel == EMenuLevel.First) {
                        result = selectedItem.getSubMenuToRun().apply(EMenuLevel.Second);
                    }
                    else {
                        result = selectedItem.getSubMenuToRun().apply(EMenuLevel.Other);
                    }
                } else if (selectedItem.getSubMenuToRun() != null) {
                    String result = selectedItem.getMethodToRun().toString();
                    if ("x".equalsIgnoreCase(result)) {
                        userChoice = "x";
                    }
                }
            } else if (!ReservedShortcuts.contains(userChoice.toLowerCase())) {
                System.out.println("Undefined shortcut...");
            }

            System.out.println();

        } while (!ReservedShortcuts.contains(userChoice));

        return userChoice;

    }


    private void clearConsole() {
        // Attempt to clear the console
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error clearing console: " + e.getMessage());
        }
    }


}
