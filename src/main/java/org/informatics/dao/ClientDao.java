package org.informatics.dao;

import org.informatics.entity.Client;

public interface ClientDao extends BaseDao<Client, Long> {
    boolean existsByEmail(String email);
}
