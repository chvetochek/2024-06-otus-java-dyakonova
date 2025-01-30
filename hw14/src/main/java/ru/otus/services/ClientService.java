package ru.otus.services;

import java.util.List;
import java.util.Optional;

import ru.otus.domain.Client;

public interface ClientService {
    Client saveClient(Client client);

    List<Client> findAll();
}
