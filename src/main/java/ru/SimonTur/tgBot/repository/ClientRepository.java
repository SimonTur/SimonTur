package ru.SimonTur.tgBot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.SimonTur.tgBot.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@RepositoryRestResource
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByFullNameContainingIgnoreCase(String name);
}