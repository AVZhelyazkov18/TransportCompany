package org.informatics.flow.driver;

import org.informatics.context.ApplicationContext;
import org.informatics.dto.DriverDto;
import org.informatics.enums.Qualification;
import org.informatics.flow.Flow;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CreateDriverFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {

        System.out.print("First name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Salary: ");
        BigDecimal salary = new BigDecimal(scanner.nextLine().trim());

        Set<Qualification> qualifications = new HashSet<>();
        System.out.println("Qualifications Types:");
        for (Qualification q : Qualification.values())
            System.out.println(q.name());
        System.out.println("Qualifications (comma separated):");

        String[] input = scanner.nextLine().split(",");

        for (String q : input)
            qualifications.add(Qualification.valueOf(q.trim()));


        DriverDto dto = new DriverDto(firstName, lastName, email, salary, qualifications);

        context.getDriverService().createDriver(context, dto);

        System.out.println("Driver created successfully.");
    }
}
