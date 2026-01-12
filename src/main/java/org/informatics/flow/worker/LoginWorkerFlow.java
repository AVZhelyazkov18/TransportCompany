package org.informatics.flow;

import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.InvalidWorkerException;

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