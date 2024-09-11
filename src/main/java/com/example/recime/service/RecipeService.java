package com.example.recime.service;

import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.model.entity.Recipe;
import com.example.recime.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public Page<RecipeDTO> getTrendingRecipes(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("position").ascending());
        Page<Recipe> recipePage = recipeRepository.findAll(pageable);
        List<RecipeDTO> recipeDTOPage = recipePage.getContent().stream().map(this::convertToDTO).toList();
        return  new PageImpl<>(recipeDTOPage, pageable, recipePage.getTotalElements());
    }

    public Page<RecipeDTO> getTrendingRecipesByDifficulty(Integer page, Integer size, String difficulty) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("position").ascending());
        Page<Recipe> recipePage = recipeRepository.findAllByDifficulty(difficulty, pageable);
        List<RecipeDTO> recipeDTOPage = recipePage.getContent().stream().map(this::convertToDTO).toList();
        return  new PageImpl<>(recipeDTOPage, pageable, recipePage.getTotalElements());
    }

    private RecipeDTO convertToDTO(Recipe recipe) {
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
