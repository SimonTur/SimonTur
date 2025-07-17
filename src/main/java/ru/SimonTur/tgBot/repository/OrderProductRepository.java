package ru.SimonTur.tgBot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.SimonTur.tgBot.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@RepositoryRestResource
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}