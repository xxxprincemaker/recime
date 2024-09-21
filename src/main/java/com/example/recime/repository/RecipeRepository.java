package com.example.recime.repository;

import com.example.recime.model.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(exported = false)
public interface RecipeRepository extends JpaRepository<Recipe, String> {


    Page<Recipe> findAllByDifficulty(String difficulty, Pageable pageable);

    @Query("select r from Recipe r join r.ingredients i where key(i).name in :ingredients  ")
    Page<Recipe> findAllByIngredient(List<String> ingredients, Pageable pageable);

    @Query(""" 
            SELECT r FROM Recipe r
            JOIN r.ingredients i
            WHERE key(i).name IN :ingredients
            GROUP BY r
            HAVING COUNT(key(i).name) = :ingredientCount
            AND COUNT(DISTINCT key(i).name) = :ingredientCount
            """)
    Page<Recipe> findAllRecipesByThatHaveOnlyThoseIngredients(List<String> ingredients, int ingredientCount,Pageable pageable);
}
