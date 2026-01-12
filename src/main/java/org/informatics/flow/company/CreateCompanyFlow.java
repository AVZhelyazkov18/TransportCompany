package org.informatics.flow;

import org.informatics.context.ApplicationContext;
import org.informatics.dto.CompanyDto;
import org.informatics.dto.WorkerDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class CreateCompanyFlow {

    public void execute(Scanner scanner, ApplicationContext context) {
        System.out.print("Company name: ");
        String companyName = scanner.nextLine().trim();

        System.out.print("Registered Date (yyyy-MM-dd) or use 'now' for today: ");
        LocalDate registeredOn;
        String dateInput = scanner.nextLine().trim();
        if (dateInput.equals("now"))
            registeredOn = LocalDate.now();
        else
            registeredOn = LocalDate.parse(dateInput);

        CompanyDto company = new CompanyDto(companyName, null, registeredOn);

        context.getCompanyService().createCompany(company, context);

        System.out.format("Successfully created Company with name: %s\n", companyName);

        System.out.print("Owner First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Owner Last Name: ");
        String lastName = scanner.nextLine().trim();

        System.out.println("Owner Email: ");
        String email = scanner.nextLine().trim();

        WorkerDto ownerDto = new WorkerDto(firstName, lastName, email, BigDecimal.ZERO);

        Long ownerId = context.getWorkerService().createWorker(ownerDto, context);
        System.out.println("Successfully created Worker account with Id: " + ownerId);

        company.setOwnerId(ownerId);
        context.getCompanyService().updateCompanyOwner(company, context);

        System.out.format("Successfully set company owner to: %s - %s %s", ownerId.toString(), firstName, lastName);
    }
}
