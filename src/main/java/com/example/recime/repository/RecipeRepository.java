package com.example.recime.repository;

import com.example.recime.model.entity.Recipe;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface RecipeRepository extends JpaRepository<Recipe, String> {


    Page<Recipe> findAllByDifficulty(String difficulty, Pageable pageable);
}
