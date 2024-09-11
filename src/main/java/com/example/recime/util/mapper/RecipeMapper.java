package com.example.recime.util.mapper;

import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.model.entity.Recipe;

public class RecipeMapper {

    public static RecipeDTO convertToDTO(Recipe recipe) {
        return RecipeDTO.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .imageUrl(recipe.getImageUrl())
                .recipe(recipe.getRecipe())
                .position(recipe.getPosition())
                .difficulty(recipe.getDifficulty())
                .build();
    }
}
