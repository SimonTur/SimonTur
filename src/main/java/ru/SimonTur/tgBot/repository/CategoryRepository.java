package ru.SimonTur.tgBot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.SimonTur.tgBot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@RepositoryRestResource
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}