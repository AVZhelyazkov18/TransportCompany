package org.informatics.service;

import org.informatics.dao.ClientDao;
import org.informatics.dto.ClientDto;
import org.informatics.entity.Client;
import org.informatics.exceptions.EmailAlreadyExistException;

public class ClientService {

    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public boolean clientExists(Long id) {
        return (clientDao.findById(id) != null);
    }

    public Long registerClient(ClientDto dto) {
        if (clientDao.existsByEmail(dto.getEmail()))
            throw new EmailAlreadyExistException("Email already exists");

        Client client = new Client(dto.getFirstName(), dto.getMidName(), dto.getLastName(), dto.getEmail(), dto.getPhone());
        clientDao.save(client);

        return client.getId();
    }
}
