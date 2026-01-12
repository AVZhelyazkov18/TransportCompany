package org.informatics.flow.order;

import org.informatics.context.ApplicationContext;
import org.informatics.exceptions.AlreadyPaidException;
import org.informatics.exceptions.InsufficientBalanceException;
import org.informatics.exceptions.InvalidOrderException;
import org.informatics.flow.Flow;

import java.math.BigDecimal;
import java.util.Scanner;

public class PaymentOrderFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {

        try {
            System.out.print("Order Id: ");
            Long orderId = Long.parseLong(scanner.nextLine().trim());

            System.out.print("Cash amount: ");
            BigDecimal cash = new BigDecimal(scanner.nextLine().trim());

            context.getOrderService().payApprovedOrder(orderId, cash);

            System.out.println("Order paid successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid order amount.");
        } catch (InvalidOrderException e) {
            System.out.println("No order found with that id.");
        } catch (AlreadyPaidException e) {
            System.out.println("Order already payed.");
        } catch (InsufficientBalanceException | IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
