package ru.SimonTur.tgBot.controller;

import ru.SimonTur.tgBot.model.Client;
import ru.SimonTur.tgBot.model.ClientOrder;
import ru.SimonTur.tgBot.service.ClientService;
import ru.SimonTur.tgBot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/clients")
public class ClientRestController {
    private final ClientService clientService;
    private final OrderService orderService;

    @Autowired
    public ClientRestController(ClientService clientService, OrderService orderService) {
        this.clientService = clientService;
        this.orderService = orderService;
    }

    @GetMapping("/{id}/orders")
    public List<ClientOrder> getClientOrders(@PathVariable long id) {
        return orderService.getClientOrders(id);
    }

    @GetMapping("/search")
    public List<Client> searchClientsByName(@RequestParam String name) {
        return clientService.searchClientsByName(name);
    }
}