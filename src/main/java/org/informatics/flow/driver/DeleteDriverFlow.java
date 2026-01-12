package org.informatics.flow.driver;

import org.informatics.context.ApplicationContext;
import java.util.Scanner;
import org.informatics.flow.Flow;

public class DeleteDriverFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        System.out.print("Driver Id to delete: ");
        Long id = Long.parseLong(scanner.nextLine().trim());

        if(context.getDriverService().deleteDriver(id))
            System.out.println("Driver deleted.");
    }
}

