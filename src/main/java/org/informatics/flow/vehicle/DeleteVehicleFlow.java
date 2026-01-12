package org.informatics.flow;

import org.informatics.context.ApplicationContext;

import java.util.Scanner;

public class DeleteVehicleFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {

        System.out.print("Vehicle ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine().trim());

        context.getVehicleService().deleteVehicle(id);

        System.out.println("Vehicle deleted");
    }
}
