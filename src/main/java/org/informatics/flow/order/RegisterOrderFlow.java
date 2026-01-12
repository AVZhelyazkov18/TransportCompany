package org.informatics.flow.order;

import org.informatics.context.ApplicationContext;
import org.informatics.dto.OrderDto;
import org.informatics.enums.TransportType;
import org.informatics.exceptions.IllegalCargoException;
import org.informatics.exceptions.InvalidClientException;
import org.informatics.exceptions.RequiredCargoException;
import org.informatics.flow.Flow;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RegisterOrderFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        try {
            System.out.print("Client Id: ");
            String clientId = scanner.nextLine().trim();

            if (!context.getClientService().clientExists(Long.parseLong(clientId)))
                throw new InvalidClientException("Client doesn't exist with Id: " + clientId);

            System.out.print("Start location: ");
            String startLocation = scanner.nextLine().trim();

            System.out.print("End location: ");
            String endLocation = scanner.nextLine().trim();

            System.out.print("Date of departure (yyyy-MM-dd): ");
            LocalDate departure = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Date of arrival (yyyy-MM-dd): ");
            LocalDate arrival = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Transport type (PASSENGERS / CARGO): ");
            TransportType transportType = TransportType.valueOf(scanner.nextLine().trim().toUpperCase());

            Double cargoWeight = null;
            if (transportType == TransportType.CARGO) {
                System.out.print("Cargo weight: ");
                cargoWeight = Double.parseDouble(scanner.nextLine().trim());
            }

            System.out.print("Would you like to pay now? (Y/N): ");
            boolean paid = scanner.nextLine().trim().charAt(0) == 'Y';

            OrderDto dto = new OrderDto(
                    Long.parseLong(clientId),
                    startLocation,
                    endLocation,
                    departure,
                    arrival,
                    transportType,
                    cargoWeight,
                    paid
            );

            context.getOrderService().registerOrder(dto, context);

            System.out.println("Order registered successfully");

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use yyyy-MM-dd.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input or client not found");
        } catch (RequiredCargoException | IllegalCargoException | InvalidClientException e) {
            System.out.println(e.getMessage());
        }
    }
}