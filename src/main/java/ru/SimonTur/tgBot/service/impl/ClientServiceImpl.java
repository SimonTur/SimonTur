package ru.SimonTur.tgBot.service.impl;

import ru.SimonTur.tgBot.model.Client;
import ru.SimonTur.tgBot.repository.ClientRepository;
import ru.SimonTur.tgBot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> searchClientsByName(String name) {
        return clientRepository.findByFullNameContainingIgnoreCase(name);
    }
}