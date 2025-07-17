package ru.SimonTur.tgBot.service;

import org.springframework.stereotype.Service;
import ru.SimonTur.tgBot.model.Product;
import java.util.List;
@Service
public interface ProductService {
    Product createProduct(Product product);
    List<Product> getProductsByCategoryId(long id);
    List<Product> getClientProducts(long clientId);
    List<Product> getTopPopularProducts(Integer limit);
    List<Product> searchProductsByName(String name);
    List<Product> getClientProducts(Long clientId);
    }