package com.example.recime.util.mapper;

import com.example.recime.model.dto.IngredientDTO;
import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.model.entity.Ingredient;
import com.example.recime.model.entity.Recipe;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class RecipeMapper {

    public static RecipeDTO convertToDTO(Recipe recipe) {
        return RecipeDTO.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .imageUrl(recipe.getImageUrl())
                .recipe(recipe.getRecipe())
                .position(recipe.getPosition())
                .difficulty(recipe.getDifficulty())
                .ingredients(convertToIngredientMapToDTOSet(recipe.getIngredients()))
                .build();
    }

    private static Set<IngredientDTO> convertToIngredientMapToDTOSet(Map<Ingredient, Double> ingredientsMap) {
        Set<IngredientDTO> ingredients = new LinkedHashSet<>();

        ingredientsMap.forEach((ingredient, quantity) -> {
            ingredients.add(IngredientDTO.builder()
                    .name(ingredient.getName())
                    .quantity(quantity)
                    .unit(ingredient.getUnit())
                    .build());
        });

        return ingredients;
    }
}
