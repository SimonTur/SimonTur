package ru.SimonTur.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.SimonTur.tgBot.entity.Product;
import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "products", path = "products")

public interface ProductRepository extends JpaRepository<Product, Long> {
List<Product> findByName(String name);
}