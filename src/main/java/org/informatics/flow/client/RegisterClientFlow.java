package org.informatics.flow.client;

import org.informatics.context.ApplicationContext;
import org.informatics.dto.ClientDto;
import org.informatics.exceptions.EmailAlreadyExistException;
import org.informatics.flow.Flow;
import org.informatics.service.ClientService;

import java.util.Scanner;

public class RegisterClientFlow implements Flow {

    private final ClientService clientService;

    public RegisterClientFlow(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        try {
            System.out.print("First name: ");
            String firstName = scanner.nextLine();

            System.out.print("Middle name: ");
            String middleName = scanner.nextLine();

            System.out.print("Last name: ");
            String lastName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            ClientDto clientDto = new ClientDto(firstName, middleName, lastName, email, phone);

            Long clientId = clientService.registerClient(clientDto);
            System.out.format("Client registered successfully. Your Client ID is: %s\n", clientId);

        } catch (EmailAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
