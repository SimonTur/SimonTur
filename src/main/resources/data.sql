-- Категории
INSERT INTO categories (name) VALUES ('Еда');
INSERT INTO categories (name) VALUES ('Напитки');
INSERT INTO categories (name) VALUES ('Электроника');

-- Товары
INSERT INTO products (name, category_id) VALUES ('Ролл Филадельфия', 1);
INSERT INTO products (name, category_id) VALUES ('Макароны по-флотски', 1);
INSERT INTO products (name, category_id) VALUES ('Кола', 2);
INSERT INTO products (name, category_id) VALUES ('Фанта', 2);
INSERT INTO products (name, category_id) VALUES ('Телефон', 3);
INSERT INTO products (name, category_id) VALUES ('Ноутбук', 3);

-- Клиенты
INSERT INTO clients (full_name) VALUES ('Иван Петров');
INSERT INTO clients (full_name) VALUES ('Роман Иванов');
INSERT INTO clients (full_name) VALUES ('Алексей Сидоров');

-- Заказы
INSERT INTO client_orders (client_id) VALUES (1);
INSERT INTO client_orders (client_id) VALUES (1);
INSERT INTO client_orders (client_id) VALUES (2);
INSERT INTO client_orders (client_id) VALUES (3);

-- Товары в заказах
INSERT INTO order_products (order_id, product_id) VALUES (1, 1);
INSERT INTO order_products (order_id, product_id) VALUES (1, 3);
INSERT INTO order_products (order_id, product_id) VALUES (2, 2);
INSERT INTO order_products (order_id, product_id) VALUES (2, 4);
INSERT INTO order_products (order_id, product_id) VALUES (3, 5);
INSERT INTO order_products (order_id, product_id) VALUES (4, 6);
INSERT INTO order_products (order_id, product_id) VALUES (4, 1);