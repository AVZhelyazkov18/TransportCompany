package org.informatics.flow;

import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.InvalidOptionException;
import org.informatics.flow.company.GenerateCompanyTranscriptFlow;
import org.informatics.ui_utils.MenuController;
import org.informatics.ui_utils.MenuOption;

import java.util.List;
import java.util.Scanner;

public class WorkerMenuFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        while (context.getAuthService().isLoggedIn()) {
            List<MenuOption> menu = List.of(
                    new MenuOption(1, "Report company data on file"),
                    new MenuOption(2, "Approve orders"),
                    new MenuOption(3, "Vehicle management"),
                    new MenuOption(0, "Log out")
            );

            MenuOption option;

            try {
                option = MenuController.showMenu("Worker menu", menu);

                switch (option.getCode()) {
                    case 1 -> new GenerateCompanyTranscriptFlow().execute(scanner, context);
                    case 2 -> System.out.println("Approve orders selected");
                    case 3 -> new VehicleMenuFlow().execute(scanner, context);
                    case 0 -> {
                        context.getAuthService().logout();
                        System.out.println("You have logged out.");
                    }
                }
            } catch (InvalidOptionException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}