package org.informatics.flow;

import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.InvalidOptionException;
import org.informatics.flow.company.GenerateCompanyTranscriptFlow;
import org.informatics.flow.vehicle.CreateVehicleFlow;
import org.informatics.flow.vehicle.DeleteVehicleFlow;
import org.informatics.flow.vehicle.ListVehicleFlow;
import org.informatics.ui_utils.MenuController;
import org.informatics.ui_utils.MenuOption;

import java.util.List;
import java.util.Scanner;

public class VehicleMenuFlow implements Flow{
    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        List<MenuOption> menu = List.of(
                new MenuOption(1, "Add Vehicle"),
                new MenuOption(2, "List Vehicles"),
                new MenuOption(3, "Delete Vehicle"),
                new MenuOption(0, "Back")
        );

        MenuOption option;
        try {
            option = MenuController.showMenu("Worker menu", menu);

            switch (option.getCode()) {
                case 1 -> new CreateVehicleFlow().execute(scanner, context);
                case 2 -> new ListVehicleFlow().execute(scanner, context);
                case 3 -> new DeleteVehicleFlow().execute(scanner, context);
                case 0 -> {}
                default -> System.out.println("Invalid option");
            }
        } catch (InvalidOptionException e) {
            System.out.println("Invalid option.");
        }
    }
}
