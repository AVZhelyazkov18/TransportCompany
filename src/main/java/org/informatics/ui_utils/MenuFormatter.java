package org.informatics.ui_utils;

import java.util.List;

public class MenuFormatter {
    public static void printMenu(String title, List<MenuOption> options) {
        System.out.println();
        System.out.println("=== " + title + " ===");
        for (MenuOption option : options) {
            System.out.printf("%d. %s%n", option.getCode(), option.getLabel());
        }
        System.out.print("Select option: ");
    }
}
