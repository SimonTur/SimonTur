package ru.SimonTur.tgBot.service.impl;

import ru.SimonTur.tgBot.model.Client;
import ru.SimonTur.tgBot.model.ClientOrder;
import ru.SimonTur.tgBot.repository.ClientOrderRepository;
import ru.SimonTur.tgBot.repository.ClientRepository;
import ru.SimonTur.tgBot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final ClientOrderRepository clientOrderRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OrderServiceImpl(ClientOrderRepository clientOrderRepository, ClientRepository clientRepository) {
        this.clientOrderRepository = clientOrderRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientOrder> getClientOrders(long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        return clientOrderRepository.findByClient(client);
    }
}
