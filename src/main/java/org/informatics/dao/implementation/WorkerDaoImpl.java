package org.informatics.dao.implementation;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.informatics.dao.WorkerDao;
import org.informatics.entity.Worker;

public class WorkerDaoImpl implements WorkerDao {

    private final EntityManager em;

    public WorkerDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Worker save(Worker entity) {
        em.getTransaction().begin();
        Worker result = entity.getId() == null ? persist(entity) : em.merge(entity);
        em.getTransaction().commit();
        return result;
    }

    private Worker persist(Worker entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Worker findById(Long id) {
        return em.find(Worker.class, id);
    }

    @Override
    public List<Worker> findAll() {
        return em.createQuery("FROM Worker", Worker.class).getResultList();
    }

    @Override
    public void delete(Worker entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }
}