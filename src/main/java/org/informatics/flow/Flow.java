package org.informatics.flow;

import org.informatics.context.ApplicationContext;

import java.util.Scanner;

public interface Flow {
    void execute(Scanner scanner, ApplicationContext context);
}
