package ru.SimonTur.tgBot;

import org.springframework.beans.factory.annotation.Autowired;
import ru.SimonTur.tgBot.model.*;
import ru.SimonTur.tgBot.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDataInitializer implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final ClientOrderRepository clientOrderRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderProductRepository orderProductRepository;

    @Autowired
    public TestDataInitializer(ClientRepository clientRepository,
                               ClientOrderRepository clientOrderRepository,
                               ProductRepository productRepository,
                               CategoryRepository categoryRepository,
                               OrderProductRepository orderProductRepository) {
        this.clientRepository = clientRepository;
        this.clientOrderRepository = clientOrderRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public void run(String... args) {
        // Инициализация категорий
        Category food = new Category();
        food.setName("Еда");
        categoryRepository.save(food);

        Category drinks = new Category();
        drinks.setName("Напитки");
        categoryRepository.save(drinks);

        // Инициализация продуктов
        Product product1 = new Product();
        product1.setName("Ролл Филадельфия");
        product1.setCategory(food);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Макароны по-флотски");
        product2.setCategory(food);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Кола");
        product3.setCategory(drinks);
        productRepository.save(product3);

        // Инициализация клиента
        Client client1 = new Client();
        client1.setFullName("Иван Петров");
        clientRepository.save(client1);

        // Создание заказа (вариант с промежуточной сущностью)
        ClientOrder order1 = new ClientOrder();
        order1.setClient(client1);
        clientOrderRepository.save(order1);

        // Создание связей между заказом и продуктами
        OrderProduct op1 = new OrderProduct();
        op1.setOrder(order1);
        op1.setProduct(product1);
        op1.setCountProduct(1);
        orderProductRepository.save(op1);

        OrderProduct op2 = new OrderProduct();
        op2.setOrder(order1);
        op2.setProduct(product3);
        op2.setCountProduct(2);
        orderProductRepository.save(op2);
    }
}