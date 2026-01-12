package org.informatics.flow.company;

import org.informatics.context.ApplicationContext;
import org.informatics.flow.Flow;

import java.time.LocalDate;
import java.util.Scanner;

public class GenerateCompanyTranscriptFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {

        System.out.print("Start date (yyyy-MM-dd): ");
        LocalDate from = LocalDate.parse(scanner.nextLine().trim());

        System.out.print("End date (yyyy-MM-dd or now): ");
        String endDateInput = scanner.nextLine().trim();
        LocalDate to = endDateInput.equalsIgnoreCase("now") ? LocalDate.now() : LocalDate.parse(endDateInput);

        String fileName = context.getCompanyService().generateCompanyTranscript(context, from, to);

        System.out.println("Company transcript generated successfully.");
    }
}