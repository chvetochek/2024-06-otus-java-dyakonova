package ru.otus.services;

import org.springframework.stereotype.Service;
import ru.otus.domain.Client;
import ru.otus.repository.ClientRepository;
import ru.otus.sessionmanager.TransactionManager;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final TransactionManager transactionManager;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(TransactionManager transactionManager, ClientRepository clientRepository) {
        this.transactionManager = transactionManager;
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionManager.doInTransaction(() -> {
            return clientRepository.save(client);
        });
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
