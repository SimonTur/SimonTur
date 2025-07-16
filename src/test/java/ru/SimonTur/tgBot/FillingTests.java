package ru.SimonTur.tgBot;

import jakarta.transaction.Transactional;
import org.hibernate.annotations.processing.SQL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.SimonTur.tgBot.repository.*;
import ru.SimonTur.tgBot.model.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;



@SpringBootTest(classes = TgBotApplication.class)
@ActiveProfiles("test")
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class FillingTests {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;





    @BeforeEach
    void setUp() {
        // Инициализация тестовых данных
        Category category = new Category();
        category.setName("Test Category");
        categoryRepository.save(category);

        Product product = new Product();
        product.setName("Test Product");
        product.setCategory(category);
        productRepository.save(product);
    }






    @Test
    public void fillDatabaseWithTestData() {
        // 1. Создаем клиентов
        Client client1 = createClient(123456789L, "Иван Иванов", "+79123456789", "ул. Пушкина, д. 10");
        Client client2 = createClient(987654321L, "Мария Петрова", "+79219876543", "пр. Ленина, д. 42");

        // 2. Создаем родительские категории
        Category pizza = createCategory("Пицца", null);
        Category rolls = createCategory("Роллы", null);
        Category burgers = createCategory("Бургеры", null);
        Category drinks = createCategory("Напитки", null);

        // 3. Создаем подкатегории для Роллов
        Category classicRolls = createCategory("Классические роллы", rolls);
        Category bakedRolls = createCategory("Запеченные роллы", rolls);
        Category sweetRolls = createCategory("Сладкие роллы", rolls);
        Category rollSets = createCategory("Наборы", rolls);

        // 4. Создаем товары
        Product philadelphia = createProduct("Филадельфия", "Лосось, сыр, рис", 350.0, classicRolls);
        Product california = createProduct("Калифорния", "Краб, авокадо, огурец", 320.0, classicRolls);
        Product cola = createProduct("Кола", "0.5 л", 120.0, drinks);
        Product cheeseburger = createProduct("Чизбургер", "Говядина, сыр, соус", 250.0, burgers);

        // 5. Создаем заказы
        ClientOrder order1 = createOrder(client1, 1, 1050.0);
        ClientOrder order2 = createOrder(client2, 2, 840.0);

        // 6. Добавляем товары в заказы
        addProductToOrder(order1, philadelphia, 2);
        addProductToOrder(order1, cola, 1);
        addProductToOrder(order2, cheeseburger, 3);
        addProductToOrder(order2, california, 1);
    }

    private Client createClient(Long externalId, String fullName, String phoneNumber, String address) {
        Client client = new Client();
        client.setExternalId(externalId);  // Теперь метод доступен
        client.setFullName(fullName);
        client.setPhoneNumber(phoneNumber);
        client.setAddress(address);
        return clientRepository.save(client);
    }

    private Category createCategory(String name, Category parent) {
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        return categoryRepository.save(category);
    }

    private Product createProduct(String name, String description, Double price, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);  // Теперь метод доступен
        product.setCategory(category);
        return productRepository.save(product);
    }

    private ClientOrder createOrder(Client client, Integer status, Double total) {
        ClientOrder order = new ClientOrder();
        order.setClient(client);
        order.setStatus(status);
        order.setTotal(total);  // Теперь метод доступен
        return clientOrderRepository.save(order);
    }


    private void addProductToOrder(ClientOrder order, Product product, Integer count) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setCountProduct(count);
        orderProductRepository.save(orderProduct);
    }
}