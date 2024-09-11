package com.example.recime.repository;

import com.example.recime.model.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, String> {


    Page<Recipe> findAllByDifficulty(String difficulty, Pageable pageable);
}
