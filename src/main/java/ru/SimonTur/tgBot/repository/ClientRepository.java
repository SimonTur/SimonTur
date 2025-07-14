package ru.SimonTur.tgBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.SimonTur.tgBot.entity.Client;

@Repository
@RepositoryRestResource(collectionResourceRel = "clients", path = "clients")

public interface ClientRepository extends JpaRepository<Client, Long> {
}