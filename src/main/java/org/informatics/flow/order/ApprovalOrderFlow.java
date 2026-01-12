package org.informatics.flow.order;

import org.informatics.context.ApplicationContext;
import org.informatics.entity.Order;
import org.informatics.exceptions.InvalidDriverException;
import org.informatics.exceptions.InvalidOptionException;
import org.informatics.exceptions.InvalidOrderException;
import org.informatics.flow.Flow;
import org.informatics.ui_utils.MenuController;
import org.informatics.ui_utils.MenuOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApprovalOrderFlow implements Flow {

    @Override
    public void execute(Scanner scanner, ApplicationContext context) {
        List<Order> pendingOrders = context.getOrderService().getOrdersPendingApproval();

        if (pendingOrders.isEmpty()) {
            System.out.println("No orders pending approval.");
            return;
        }

        List<MenuOption> menu = new ArrayList<>();
        int inc = 1;

        for (Order o : pendingOrders)
            menu.add(new MenuOption(inc++, String.format("%s -> %s", o.getStartLocation(), o.getEndLocation())));

        menu.add(new MenuOption(0, "Back"));

        Order order = null;

        try {
            MenuOption option = MenuController.showMenu("== Pending orders == ", menu);

            if (option.getCode() == 0) return;

            order = pendingOrders.get(option.getCode() - 1);
        } catch (InvalidOptionException e) {
            System.out.println("Option was invalid.");
        }

        if(order == null) throw new InvalidOrderException("Order not found.");

        System.out.print("Driver Id: ");
        Long driverId = Long.parseLong(scanner.nextLine().trim());

        BigDecimal price;
        while (true) {
            try {
                System.out.print("Price: ");
                price = new BigDecimal(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price.");
            }
        }

        try {
            context.getOrderService().approveOrder(
                    order.getId(),
                    driverId,
                    price,
                    context.getWorkerService().getWorkerFromId(context.getAuthService().getLoggedWorkerId())
            );
        } catch (InvalidOrderException | InvalidDriverException e) {
            System.out.println(e.getMessage());
            return;
        }


        System.out.println("Order approved successfully.");
    }
}
