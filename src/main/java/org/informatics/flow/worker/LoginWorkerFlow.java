package org.informatics.flow.worker;

import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.InvalidWorkerException;
import org.informatics.flow.Flow;
import org.informatics.flow.WorkerMenuFlow;

import java.util.Scanner;

public class LoginWorkerFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        System.out.print("Worker Id: ");
        Long workerId = Long.parseLong(scanner.nextLine().trim());

        try {
            context.getAuthService().login(workerId, context);
        } catch (InvalidWorkerException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Login successful.");
        new WorkerMenuFlow().execute(scanner, context);
    }
}