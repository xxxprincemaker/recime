package com.example.recime.service;

import com.example.recime.model.Recipe;
import com.example.recime.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public List<Recipe> getTrendingRecipes() {
        return recipeRepository.findAll();
    }
}
