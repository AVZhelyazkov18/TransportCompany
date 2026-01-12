package org.informatics.dao;

import org.informatics.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface OrderDao extends BaseDao<Order, Long> {
    Map<Long, BigDecimal>  sumRevenuePerDriverForPeriod(LocalDate from, LocalDate to);
    Map<Long, Long> countTransportsPerDriver();
    BigDecimal sumRevenueForPeriod(LocalDate from, LocalDate to);
    BigDecimal sumTotalRevenue();
    long countAllCompleted();
}