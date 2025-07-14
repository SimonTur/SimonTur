package ru.SimonTur.tgBot;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import ru.SimonTur.tgBot.repository.*;
import ru.SimonTur.tgBot.entity.*;


@SpringBootTest(classes = TgBotApplication.class)
@ActiveProfiles("test")
@Transactional
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

        // 4. Создаем подкатегории для Бургеров
        Category classicBurgers = createCategory("Классические бургеры", burgers);
        Category spicyBurgers = createCategory("Острые бургеры", burgers);

        // 5. Создаем подкатегории для Напитков
        Category soda = createCategory("Газированные напитки", drinks);
        Category energyDrinks = createCategory("Энергетические напитки", drinks);
        Category juices = createCategory("Соки", drinks);
        Category otherDrinks = createCategory("Другие", drinks);

        // 6. Создаем товары (минимум 3 в каждой подкатегории)
        // 6.1 Классические роллы
        createProduct("Филадельфия", "Лосось, сыр, рис", 350.0, classicRolls);
        createProduct("Калифорния", "Краб, авокадо, огурец", 320.0, classicRolls);
        createProduct("Унаги", "Угорь, соус унаги", 380.0, classicRolls);

        // 6.2 Запеченные роллы
        createProduct("Запеченный с креветкой", "Креветка, сыр, соус", 400.0, bakedRolls);
        createProduct("Запеченный с лососем", "Лосось, сыр, икра", 420.0, bakedRolls);
        createProduct("Запеченный с угрем", "Угорь, сыр, соус спайси", 450.0, bakedRolls);

        // 6.3 Сладкие роллы
        createProduct("Банан-шоколад", "Банан, шоколад, нутелла", 280.0, sweetRolls);
        createProduct("Клубничный", "Клубника, сливочный сыр", 300.0, sweetRolls);
        createProduct("Манго-кокос", "Манго, кокосовая стружка", 320.0, sweetRolls);

        // 6.4 Наборы роллов
        createProduct("Набор 'Стандарт'", "4 ролла по 8 шт", 1200.0, rollSets);
        createProduct("Набор 'Премиум'", "6 роллов по 8 шт", 1800.0, rollSets);
        createProduct("Набор 'Семейный'", "8 роллов по 8 шт", 2400.0, rollSets);

        // 6.5 Классические бургеры
        createProduct("Чизбургер", "Говядина, сыр, соус", 250.0, classicBurgers);
        createProduct("Гамбургер", "Говядина, овощи", 220.0, classicBurgers);
        createProduct("Чикенбургер", "Курица, салат", 230.0, classicBurgers);

        // 6.6 Острые бургеры
        createProduct("Двойной острый", "2 котлеты, острый соус", 350.0, spicyBurgers);
        createProduct("Мексиканский", "Котлета, халапеньо, сальса", 320.0, spicyBurgers);
        createProduct("Каррибургер", "Курица, соус карри", 300.0, spicyBurgers);

        // 6.7 Газированные напитки
        createProduct("Кола", "0.5 л", 120.0, soda);
        createProduct("Фанта", "0.5 л", 120.0, soda);
        createProduct("Спрайт", "0.5 л", 120.0, soda);

        // 6.8 Энергетические напитки
        createProduct("Red Bull", "250 мл", 180.0, energyDrinks);
        createProduct("Burn", "250 мл", 150.0, energyDrinks);
        createProduct("Adrenaline Rush", "250 мл", 160.0, energyDrinks);

        // 6.9 Соки
        createProduct("Апельсиновый", "1 л", 200.0, juices);
        createProduct("Яблочный", "1 л", 180.0, juices);
        createProduct("Томатный", "1 л", 170.0, juices);

        // 6.10 Другие напитки
        createProduct("Чай зеленый", "500 мл", 100.0, otherDrinks);
        createProduct("Чай черный", "500 мл", 100.0, otherDrinks);
        createProduct("Кофе латте", "300 мл", 150.0, otherDrinks);

        // 7. Создаем заказы
        ClientOrder order1 = createOrder(client1, 1, 1050.0);
        ClientOrder order2 = createOrder(client2, 2, 840.0);

        // 8. Добавляем товары в заказы
        Product product = productRepository.findByName("Филадельфия").stream().findFirst().orElseThrow();
        addProductToOrder(order1, product, 3);

        Product cola = productRepository.findByName("Кола").stream().findFirst().orElseThrow();
        addProductToOrder(order1, cola, 2);

        Product cheeseburger = productRepository.findByName("Чизбургер").stream().findFirst().orElseThrow();
        addProductToOrder(order2, cheeseburger, 2);

        Product orangeJuice = productRepository.findByName("Апельсиновый").stream().findFirst().orElseThrow();
        addProductToOrder(order2, orangeJuice, 1);
    }

    private Client createClient(Long externalId, String fullName, String phoneNumber, String address) {
        Client client = new Client();
        client.setExternalId(externalId);
        client.setFullName(fullName);
        client.setPhoneNumber(phoneNumber);
        client.setAddress(address);
        return clientRepository.save(client);  // Исправлено: clientRepository вместо ClientRepository
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
        product.setPrice(price);
        product.setCategory(category);
        return productRepository.save(product);
    }

    private ClientOrder createOrder(Client client, Integer status, Double total) {
        ClientOrder order = new ClientOrder();
        order.setClient(client);
        order.setStatus(status);
        order.setTotal(total);
        return clientOrderRepository.save(order);
    }

    private void addProductToOrder(ClientOrder order, Product product, Integer count) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setClientOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setCountProduct(count);
        orderProductRepository.save(orderProduct);
    }
}