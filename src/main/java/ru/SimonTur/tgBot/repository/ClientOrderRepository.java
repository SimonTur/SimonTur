package ru.SimonTur.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.SimonTur.tgBot.entity.ClientOrder;
@RepositoryRestResource(collectionResourceRel = "client-orders", path = "client-orders")


    public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
    }



