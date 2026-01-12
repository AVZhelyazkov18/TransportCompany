package org.informatics.flow.worker;

import org.informatics.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Scanner;
import org.informatics.dto.WorkerDto;
import org.informatics.flow.Flow;

public class CreateWorkerFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        System.out.print("First name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.println("Salary (Example: 1000.00): ");
        String salary = scanner.nextLine().trim();

        WorkerDto dto = new WorkerDto(firstName, lastName, email, BigDecimal.valueOf(Double.parseDouble(salary)));

        context.getWorkerService().createWorker(dto, context);

        System.out.println("Worker created successfully");
    }
}

