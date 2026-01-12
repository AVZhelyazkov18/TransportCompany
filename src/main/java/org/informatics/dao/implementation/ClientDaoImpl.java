package org.informatics.dao.implementation;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.informatics.dao.ClientDao;
import org.informatics.entity.Client;

public class ClientDaoImpl implements ClientDao {

    private final EntityManager em;

    public ClientDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Client save(Client entity) {
        em.getTransaction().begin();
        Client result = entity.getId() == null ? persist(entity) : em.merge(entity);
        em.getTransaction().commit();
        return result;
    }

    private Client persist(Client entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    @Override
    public List<Client> findAll() {
        return em.createQuery("FROM Client", Client.class).getResultList();
    }

    @Override
    public void delete(Client entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public boolean existsByEmail(String email) {
        Long count = em.createQuery("SELECT COUNT(c) FROM Client c WHERE c.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return count > 0;
    }
}