package ru.SimonTur.tgBot.repository;

import ru.SimonTur.tgBot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    @Query("SELECT DISTINCT p FROM ClientOrder o JOIN o.products p WHERE o.client.id = :clientId")
    List<Product> findProductsByClientId(@Param("clientId") Long clientId);

    // Остальные методы...
    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Product p JOIN p.orders o GROUP BY p.id ORDER BY COUNT(p) DESC")
    List<Product> findTopPopularProducts(Integer limit);
}