package org.informatics.ui_utils;

import org.informatics.exceptions.InvalidOptionException;

import java.util.List;
import java.util.Scanner;

public class MenuController {

    private static final MenuFormatter formatter = new MenuFormatter();
    private static final Scanner scanner = new Scanner(System.in);

    public static MenuOption showMenu(String title, List<MenuOption> options) throws InvalidOptionException {
        MenuFormatter.printMenu(title, options);
        int choice = Integer.parseInt(scanner.nextLine());

        return options.stream()
                .filter(o -> o.getCode() == choice)
                .findFirst()
                .orElseThrow(() -> new InvalidOptionException("Invalid option"));
    }
}
