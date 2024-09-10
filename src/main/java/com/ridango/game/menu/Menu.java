package com.ridango.game.menu;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContextException;

import java.util.*;

public class Menu {

    @Getter
    @Setter
    private String Title;

    @Getter
    @Setter
    private Map<String, MenuItem> MenuItems;

    @Getter
    @Setter
    private Menu parentMenu;

    private EMenuLevel level;

    private static final HashSet<String> ReservedShortcuts = new HashSet<>() {};


    public Menu(String title, List<MenuItem> menuItems) {
        this.level = EMenuLevel.First;
        this.Title = title;
        this.MenuItems = new HashMap<>();
        for (MenuItem menuItem : menuItems) {
            if (ReservedShortcuts.contains(menuItem.getShortCut().toLowerCase())) {
                throw new ApplicationContextException("Menu shortcut " + menuItem.getShortCut() + " is not allowed!");
            }

            if (MenuItems.containsKey(menuItem.getShortCut().toLowerCase())) {
                throw new ApplicationContextException("Menu shortcut " + menuItem.getShortCut() + " is already registered!");
            }

            MenuItems.put(menuItem.getShortCut().toLowerCase(), menuItem);
        }
    }


    public Menu(String title, Menu parent, List<MenuItem> menuItems) {
        this.level = EMenuLevel.Second;
        this.Title = title;
        this.MenuItems = new HashMap<>();
        this.parentMenu = parent;
        for (MenuItem menuItem : menuItems) {
            if (ReservedShortcuts.contains(menuItem.getShortCut().toLowerCase())) {
                throw new ApplicationContextException("Menu shortcut " + menuItem.getShortCut() + " is not allowed!");
            }

            if (MenuItems.containsKey(menuItem.getShortCut().toLowerCase())) {
                throw new ApplicationContextException("Menu shortcut " + menuItem.getShortCut() + " is already registered!");
            }

            MenuItems.put(menuItem.getShortCut().toLowerCase(), menuItem);
        }
    }



    private void Draw(EMenuLevel menuLevel) {
        String menuSeparator = "===============";
        if (Title != null) {
            System.out.println(Title);
            System.out.println(menuSeparator);
        }

        for (Map.Entry<String, MenuItem> entry : MenuItems.entrySet()) {
            System.out.print(entry.getKey());
            System.out.print(") ");
            System.out.print(entry.getValue().description);
            System.out.println();
        }

        if (menuLevel != EMenuLevel.First) {
            System.out.println("b) Back");
        }
        if (menuLevel == EMenuLevel.Other) {
            System.out.println("r) Return to main menu");
        }

        System.out.println("x) Exit");

        System.out.println(menuSeparator);

        System.out.print("Your choice: ");
    }

    public String Run() {
        return Run(EMenuLevel.First);
    }

    public String Run(EMenuLevel menuLevel) {
        clearConsole();
        String userChoice;
        Scanner scanner = new Scanner(System.in);

        do {
            Draw(menuLevel);
            userChoice = scanner.nextLine().trim();

            if (MenuItems.containsKey(userChoice)) {
                MenuItem selectedItem = MenuItems.get(userChoice);
                if (selectedItem.getMethodToRun() != null) {
                    String result = selectedItem.getMethodToRun().apply(null);
                    if ("x".equalsIgnoreCase(result)) {
                        userChoice = "x";
                    }
                }
            } else if ("b".equalsIgnoreCase(userChoice) && menuLevel != EMenuLevel.First) {
                this.parentMenu.Run();
                return userChoice;
            } else if (!"x".equalsIgnoreCase(userChoice)) {
                System.out.println("Undefined shortcut...");
            }

            System.out.println();
        } while (!"x".equalsIgnoreCase(userChoice));

        return userChoice;
    }

    private void clearConsole() {
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
