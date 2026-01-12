package org.informatics.service;

import org.informatics.context.ApplicationContext;
import org.informatics.dao.OrderDao;
import org.informatics.dao.ClientDao;
import org.informatics.dto.OrderDto;
import org.informatics.entity.Client;
import org.informatics.entity.Order;
import org.informatics.enums.TransportType;
import org.informatics.exceptions.ClientNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class OrderService {

    private final OrderDao orderDao;
    private final ClientDao clientDao;

    public OrderService(OrderDao orderDao, ClientDao clientDao) {
        this.orderDao = orderDao;
        this.clientDao = clientDao;
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

    public Map<Long, BigDecimal> getRevenuePerDriverForPeriod(
            LocalDate from,
            LocalDate to
    ) {
        return orderDao.sumRevenuePerDriverForPeriod(from, to);
    }
}