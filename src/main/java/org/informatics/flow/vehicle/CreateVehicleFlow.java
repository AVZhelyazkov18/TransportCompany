package org.informatics.flow.vehicle;

import org.informatics.context.ApplicationContext;
import org.informatics.dto.VehicleDto;
import org.informatics.enums.VehicleType;
import org.informatics.flow.Flow;

import java.util.Scanner;

public class CreateVehicleFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        System.out.print("Vehicle type: ");
        VehicleType type = VehicleType.valueOf(scanner.nextLine().trim().toUpperCase());

        System.out.print("Registration number: ");
        String reg = scanner.nextLine().trim();

        VehicleDto dto = new VehicleDto(type, reg);

        context.getVehicleService().createVehicle(context, dto);

        System.out.println("Vehicle created.");
    }
}
