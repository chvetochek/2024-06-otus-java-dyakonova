package ru.otus.services;

import org.springframework.stereotype.Service;
import ru.otus.domain.Client;
import ru.otus.domain.Phone;
import ru.otus.repository.ClientRepository;
import ru.otus.repository.PhoneRepository;
import ru.otus.sessionmanager.TransactionManager;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final TransactionManager transactionManager;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(TransactionManager transactionManager, ClientRepository clientRepository, PhoneRepository phoneRepository) {
        this.transactionManager = transactionManager;
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionManager.doInTransaction(() -> {
            var cl = clientRepository.save(client);
            if (client.getPhones() != null) {
                for (Phone phone : cl.getPhones()) {
                    phone.setClientId(cl.getId());
                }
            }
            return cl;
        });
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
