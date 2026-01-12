package org.informatics.flow.driver;

import org.informatics.context.ApplicationContext;
import org.informatics.entity.Driver;
import org.informatics.flow.Flow;

import java.util.List;
import java.util.Scanner;

public class ListDriverFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        List<Driver> drivers = context.getDriverService().getAllDrivers();

        if (drivers.isEmpty()) {
            System.out.println("No drivers found.");
            return;
        }

        System.out.println("=== DRIVERS ===");
        for (Driver d : drivers)
            System.out.format("%s | %s %s | %s%n", d.getId(), d.getFirstName(), d.getLastName(), d.getQualifications());
    }
}