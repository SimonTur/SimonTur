package ru.SimonTur.tgBot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.SimonTur.tgBot.model.Category;
import ru.SimonTur.tgBot.model.Product;
import ru.SimonTur.tgBot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String name) {
        if (categoryId != null && name != null) {
            List<Product> products = productService.searchProductsByName(name);
            products.removeIf(p -> !p.getCategory().getId().equals(categoryId));
            return products;
        } else if (categoryId != null) {
            return productService.getProductsByCategoryId(categoryId);
        } else if (name != null) {
            return productService.searchProductsByName(name);
        }
        return List.of();
    }

    @GetMapping("/popular")
    public List<Product> getTopPopularProducts(@RequestParam Integer limit) {
        return productService.getTopPopularProducts(limit);
    }

    @GetMapping("/{clientId}/products")
    public List<Product> getClientProducts(@PathVariable Long clientId) {
        return productService.getClientProducts(clientId);
    }
}