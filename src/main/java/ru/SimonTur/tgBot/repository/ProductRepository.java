package ru.SimonTur.tgBot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.SimonTur.tgBot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p " +
            "INNER JOIN OrderProduct op ON p = op.product " +
            "INNER JOIN op.order o " +
            "INNER JOIN o.client c " +
            "WHERE c.id = :clientId")
    List<Product> findProductsByClientId(@Param("clientId") Long clientId);
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);
    @Query("SELECT p FROM Product p " +
            "JOIN OrderProduct op ON p = op.product " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(op) DESC " +
            "LIMIT :limit")
    List<Product> findTopPopularProducts(@Param("limit") Integer limit);


    // Поиск по названию
    List<Product> findByNameContainingIgnoreCase(String name);

    // Популярные товары
    //@Query("SELECT p FROM Product p JOIN p.orderProducts op GROUP BY p ORDER BY COUNT(op) DESC LIMIT :limit")
    //List<Product> findTopPopularProducts(@Param("limit") int limit);

}