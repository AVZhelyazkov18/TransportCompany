package org.informatics.flow;

import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.InvalidOptionException;
import org.informatics.flow.client.RegisterClientFlow;
import org.informatics.flow.order.RegisterOrderFlow;
import org.informatics.flow.worker.LoginWorkerFlow;
import org.informatics.ui_utils.MenuController;
import org.informatics.ui_utils.MenuOption;

import java.util.List;
import java.util.Scanner;

public class MainMenuFlow implements Flow{
    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        List<MenuOption> mainMenuOptions = List.of(
                new MenuOption(1, "Register client"),
                new MenuOption(2, "Register order"),
                new MenuOption(3, "Login as worker"),
                new MenuOption(0, "Exit")
        );

        MenuOption option = null;

        do {
            try {
                option = MenuController.showMenu("Transport Company Application", mainMenuOptions);

                switch (option.getCode()) {
                    case 1 -> new RegisterClientFlow(context.getClientService()).execute(scanner, context);
                    case 2 -> new RegisterOrderFlow().execute(scanner, context);
                    case 3 -> new LoginWorkerFlow().execute(scanner, context);
                    case 0 -> System.out.println("Exiting application.");
                }
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }


        } while(option.getCode() != 0);
    }
}
