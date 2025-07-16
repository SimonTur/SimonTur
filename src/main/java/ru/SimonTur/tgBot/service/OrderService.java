package ru.SimonTur.tgBot.service;

import ru.SimonTur.tgBot.model.ClientOrder;
import java.util.List;

public interface OrderService {
    List<ClientOrder> getClientOrders(long clientId);
}