package ru.SimonTur.tgBot.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.SimonTur.tgBot.model.Client;
import ru.SimonTur.tgBot.model.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.SimonTur.tgBot.model.Product;

import java.util.List;
@RepositoryRestResource
@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

    @Query("SELECT DISTINCT op.product FROM ClientOrder o JOIN o.orderProducts op WHERE o.client.id = :clientId")
    List<Product> findProductsByClientId(@Param("clientId") Long clientId);
    List<ClientOrder> findByClient(Client client);

}