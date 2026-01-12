package org.informatics.dao.implementation;

import jakarta.persistence.EntityManager;
import java.util.List;

import org.informatics.dao.CompanyDao;
import org.informatics.entity.Company;

public class CompanyDaoImpl implements CompanyDao {

    private final EntityManager em;

    public CompanyDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Company save(Company entity) {
        em.getTransaction().begin();
        Company result = entity.getId() == null ? persist(entity) : em.merge(entity);
        em.getTransaction().commit();
        return result;
    }

    private Company persist(Company entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Company findById(Long id) {
        return em.find(Company.class, id);
    }

    @Override
    public List<Company> findAll() {
        return em.createQuery("FROM Company", Company.class).getResultList();
    }

    @Override
    public void delete(Company entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public Long count() {
        return em.createQuery("SELECT COUNT(c) FROM Company c", Long.class).getSingleResult();
    }
}