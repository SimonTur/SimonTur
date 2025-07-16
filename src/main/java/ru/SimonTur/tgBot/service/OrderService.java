package ru.SimonTur.tgBot.service;

import org.springframework.stereotype.Service;
import ru.SimonTur.tgBot.model.ClientOrder;
import java.util.List;
@Service
public interface OrderService {
    List<ClientOrder> getClientOrders(long clientId);
}