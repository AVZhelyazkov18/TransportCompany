package org.informatics.flow.vehicle;

import org.informatics.context.ApplicationContext;
import org.informatics.flow.Flow;

import java.util.Scanner;

public class DeleteVehicleFlow implements Flow {
    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        System.out.print("Vehicle ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine().trim());

        boolean hasBeenDeleted = context.getVehicleService().deleteVehicle(id);

        if (hasBeenDeleted)
            System.out.println("Vehicle deleted.");
    }
}