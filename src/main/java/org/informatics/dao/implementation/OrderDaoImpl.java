package org.informatics.dao.implementation;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.informatics.dao.OrderDao;
import org.informatics.entity.Order;

public class OrderDaoImpl implements OrderDao {

    private final EntityManager em;

    public OrderDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Order save(Order entity) {
        em.getTransaction().begin();
        Order result = entity.getId() == null ? persist(entity) : em.merge(entity);
        em.getTransaction().commit();
        return result;
    }

    private Order persist(Order entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return em.createQuery("FROM Order", Order.class).getResultList();
    }

    @Override
    public void delete(Order entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public long countAllCompleted() {
        return em.createQuery(
                "SELECT COUNT(o) FROM Order o WHERE o.isPaid = true",
                Long.class
        ).getSingleResult();
    }

    @Override
    public BigDecimal sumTotalRevenue() {
        return em.createQuery(
                "SELECT SUM(o.price) FROM Order o " +
                        "WHERE o.isPaid = true",
                BigDecimal.class
        ).getSingleResult();
    }

    @Override
    public BigDecimal sumRevenueForPeriod(LocalDate from, LocalDate to) {
        return em.createQuery(
                        """
                        SELECT SUM(o.price) FROM Order o
                        WHERE o.isPaid = true AND o.arrivalDate BETWEEN :from AND :to
                        """,
                        BigDecimal.class
                )
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();
    }

    @Override
    public Map<Long, Long> countTransportsPerDriver() {

        List<Object[]> results = em.createQuery(
                """
                SELECT o.driver.id, COUNT(o) FROM Order o
                WHERE o.isPaid = true
                GROUP BY o.driver.id
                """,
                Object[].class
        ).getResultList();

        Map<Long, Long> map = new HashMap<>();
        for (Object[] row : results) {
            map.put((Long) row[0], (Long) row[1]);
        }
        return map;
    }

    @Override
    public Map<Long, BigDecimal> sumRevenuePerDriverForPeriod(LocalDate from, LocalDate to) {

        List<Object[]> results = em.createQuery(
                        """
                        SELECT o.driver.id, SUM(o.price) FROM Order o
                        WHERE o.isPaid = true
                        AND o.arrivalDate BETWEEN :from AND :to
                        GROUP BY o.driver.id
                        """,
                        Object[].class
                )
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();

        Map<Long, BigDecimal> map = new HashMap<>();
        for (Object[] row : results) {
            map.put((Long) row[0], (BigDecimal) row[1]);
        }
        return map;
    }
}