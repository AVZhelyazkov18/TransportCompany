package org.informatics.dao.implementation;

import jakarta.persistence.EntityManager;
import org.informatics.dao.DriverDao;
import org.informatics.entity.Driver;

import java.util.List;

public class DriverDaoImpl implements DriverDao {

    private final EntityManager em;

    public DriverDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Driver save(Driver entity) {
        em.getTransaction().begin();

        Driver result;
        if (entity.getId() == null) {
            em.persist(entity);
            result = entity;
        } else {
            result = em.merge(entity);
        }

        em.getTransaction().commit();
        return result;
    }

    @Override
    public Driver findById(Long id) {
        return em.find(Driver.class, id);
    }

    @Override
    public List<Driver> findAll() {
        return em.createQuery(
                "FROM Driver",
                Driver.class
        ).getResultList();
    }

    @Override
    public void delete(Driver entity) {
        em.getTransaction().begin();

        Driver managed = em.contains(entity)
                ? entity
                : em.merge(entity);

        em.remove(managed);

        em.getTransaction().commit();
    }
}
