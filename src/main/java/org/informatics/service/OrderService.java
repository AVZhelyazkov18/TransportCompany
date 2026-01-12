package org.informatics.service;

import org.informatics.context.ApplicationContext;
import org.informatics.dao.DriverDao;
import org.informatics.dao.OrderDao;
import org.informatics.dao.ClientDao;
import org.informatics.dto.OrderDto;
import org.informatics.entity.Client;
import org.informatics.entity.Driver;
import org.informatics.entity.Order;
import java.util.List;

import org.informatics.entity.Worker;
import org.informatics.exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class OrderService {

    private final OrderDao orderDao;
    private final ClientDao clientDao;
    private final DriverDao driverDao;

    public OrderService(OrderDao orderDao, ClientDao clientDao, DriverDao driverDao) {
        this.orderDao = orderDao;
        this.clientDao = clientDao;
        this.driverDao = driverDao;
    }

    public void registerOrder(OrderDto dto, ApplicationContext context) {
        Client client = clientDao.findById(dto.getClientId());

        if (client == null)
            throw new ClientNotFoundException("Client not found with id: " + dto.getClientId().toString());

        Order order = new Order(
                dto.getTransportType(),
                dto.getStartLocation(),
                dto.getEndLocation(),
                dto.getDateOfDeparture(),
                dto.getDateOfArrival(),
                dto.getCargoWeight(),
                client,
                context.getCompanyService().getCompany()
        );

        orderDao.save(order);
    }

    public long getTotalTransports() {
        return orderDao.countAllCompleted();
    }

    public BigDecimal getTotalRevenue() {
        return orderDao.sumTotalRevenue();
    }

    public BigDecimal getRevenueForPeriod(LocalDate from, LocalDate to) {
        return orderDao.sumRevenueForPeriod(from, to);
    }

    public Map<Long, Long> getTransportsPerDriver() {
        return orderDao.countTransportsPerDriver();
    }

    public Map<Long, BigDecimal> getRevenuePerDriverForPeriod(LocalDate from, LocalDate to) {
        return orderDao.sumRevenuePerDriverForPeriod(from, to);
    }

    public List<Order> getOrdersPendingApproval() {
        return orderDao.findAllPendingApproval();
    }

    public void approveOrder(Long orderId, Long driverId, BigDecimal price, Worker approver) {
        Order order = orderDao.findById(orderId);
        if (order == null)
            throw new InvalidOrderException("Order not found");

        Driver driver = driverDao.findById(driverId);
        if (driver == null)
            throw new InvalidDriverException("Driver not found");

        order.setDriver(driver);
        order.setPrice(price);
        order.setApprover(approver);

        orderDao.save(order);
    }

    public void payApprovedOrder(Long orderId, BigDecimal cashAmount) {
        Order order = orderDao.findById(orderId);

        if (order == null)
            throw new InvalidOrderException("Order not found with id: " + orderId);

        if (order.getApprover() == null)
            throw new IllegalStateException("Order is not approved");

        if (order.isPaid())
            throw new AlreadyPaidException("Order is already paid");

        if (cashAmount.compareTo(order.getPrice()) < 0)
            throw new InsufficientBalanceException("Insufficient balance. Required: %s, Provided: %s".formatted(order.getPrice(), cashAmount));

        order.setPaid(true);
        orderDao.save(order);
    }
}