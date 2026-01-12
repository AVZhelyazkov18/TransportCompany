package org.informatics.dao.implementation;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.informatics.dao.VehicleDao;
import org.informatics.entity.Vehicle;

public class VehicleDaoImpl implements VehicleDao {

    private final EntityManager em;

    public VehicleDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Vehicle save(Vehicle entity) {
        em.getTransaction().begin();
        Vehicle result = entity.getId() == null ? persist(entity) : em.merge(entity);
        em.getTransaction().commit();
        return result;
    }

    private Vehicle persist(Vehicle entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Vehicle findById(Long id) {
        return em.find(Vehicle.class, id);
    }

    @Override
    public List<Vehicle> findAll() {
        return em.createQuery("FROM Vehicle", Vehicle.class).getResultList();
    }

    @Override
    public void delete(Vehicle entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }
}