package ru.SimonTur.tgBot.service.impl;

import ru.SimonTur.tgBot.model.Product;
import ru.SimonTur.tgBot.repository.ProductRepository;
import ru.SimonTur.tgBot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsByCategoryId(long id) {
        return productRepository.findByCategoryId(id);
    }

    @Override
    public List<Product> getClientProducts(long clientId) {
        return productRepository.findProductsByClientId(clientId); // Теперь метод существует
    }

    @Override
    public List<Product> getTopPopularProducts(Integer limit) {
        return productRepository.findTopPopularProducts(limit);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}