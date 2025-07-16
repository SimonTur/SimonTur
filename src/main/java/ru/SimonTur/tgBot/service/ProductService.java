package ru.SimonTur.tgBot.service;

import ru.SimonTur.tgBot.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getProductsByCategoryId(long id);
    List<Product> getClientProducts(long clientId);
    List<Product> getTopPopularProducts(Integer limit);
    List<Product> searchProductsByName(String name);
}