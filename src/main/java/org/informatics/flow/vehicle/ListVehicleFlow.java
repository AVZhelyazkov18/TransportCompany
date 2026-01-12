package org.informatics.flow.vehicle;

import org.informatics.context.ApplicationContext;
import org.informatics.entity.Vehicle;
import org.informatics.flow.Flow;

import java.util.List;
import java.util.Scanner;

public class ListVehicleFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        List<Vehicle> vehicles = context.getVehicleService().getAllVehicles();

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("=== VEHICLES ===");
        for (Vehicle v : vehicles)
            System.out.format("%s | %s | %s%n", v.getId(), v.getType(), v.getRegistrationNumber());
    }
}
