package ru.SimonTur.tgBot.controller;

import ru.SimonTur.tgBot.model.ClientOrder;
import ru.SimonTur.tgBot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/client/{id}")
    public List<ClientOrder> getClientOrders(@PathVariable long id) {
        return orderService.getClientOrders(id);
    }
}
