package org.informatics.flow;

import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.InvalidOptionException;
import org.informatics.flow.driver.CreateDriverFlow;
import org.informatics.flow.driver.DeleteDriverFlow;
import org.informatics.flow.driver.ListDriverFlow;
import org.informatics.flow.vehicle.CreateVehicleFlow;
import org.informatics.flow.vehicle.DeleteVehicleFlow;
import org.informatics.flow.vehicle.ListVehicleFlow;
import org.informatics.ui_utils.MenuController;
import org.informatics.ui_utils.MenuOption;

import java.util.List;
import java.util.Scanner;

public class DriverMenuFlow implements Flow{
    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        List<MenuOption> menu = List.of(
                new MenuOption(1, "Add Driver"),
                new MenuOption(2, "List Drivers"),
                new MenuOption(3, "Delete Driver"),
                new MenuOption(0, "Back")
        );

        MenuOption option;
        try {
            option = MenuController.showMenu("== Driver menu ==", menu);

            switch (option.getCode()) {
                case 1 -> new CreateDriverFlow().execute(scanner, context);
                case 2 -> new ListDriverFlow().execute(scanner, context);
                case 3 -> new DeleteDriverFlow().execute(scanner, context);
                case 0 -> {}
                default -> System.out.println("Invalid option");
            }
        } catch (InvalidOptionException e) {
            System.out.println("Invalid option.");
        }
    }
}
