package com.example.recime.unit.service;


import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.model.entity.Recipe;
import com.example.recime.repository.RecipeRepository;
import com.example.recime.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class RecipeServiceUnitTest {
    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTrendingRecipes() {
        // Arrange
        Recipe recipe = Recipe.builder().name("Recipe1").position(1).description(null).build();
        Recipe recipe2 = Recipe.builder().name("Recipe2").position(4).description(null).build();
        Recipe recipe3 = Recipe.builder().name("Recipe3").position(3).description(null).build();


        // Create a list of recipes already ordered by position to simulate the database
        List<Recipe> recipes = List.of(recipe, recipe3, recipe2);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("position").ascending());
        Page<Recipe> recipePage = new PageImpl<>(recipes, pageable, recipes.size());

        when(recipeRepository.findAll(pageable)).thenReturn(recipePage);

        // Act
        Page<RecipeDTO> result = recipeService.getTrendingRecipes(0, 10);

        // Assert that is getting the recipe on the correct position
        assertEquals(3, result.getTotalElements());
        assertEquals(Sort.by("position").ascending(), result.getSort());

        assertEquals("Recipe1", result.getContent().get(0).getName());
        assertNull(result.getContent().get(0).getDescription());

        assertEquals("Recipe3", result.getContent().get(1).getName());
        assertEquals(3, result.getContent().get(1).getPosition());

        assertEquals("Recipe2", result.getContent().get(2).getName());
        assertEquals(4, result.getContent().get(2).getPosition());

    }

    @Test
    void testGetTrendingRecipesByDifficulty() {
        // Arrange
        Recipe recipe1 = Recipe.builder().name("Recipe1").position(1).difficulty("easy").build();
        Recipe recipe2 = Recipe.builder().name("Recipe2").position(4).difficulty("hard").build();
        Recipe recipe3 = Recipe.builder().name("Recipe3").position(3).difficulty("easy").build();

        List<Recipe> recipes = List.of(recipe1, recipe3); // Só incluir receitas com dificuldade "easy"
        Pageable pageable = PageRequest.of(0, 10, Sort.by("position").ascending());
        Page<Recipe> recipePage = new PageImpl<>(recipes, pageable, recipes.size());

        when(recipeRepository.findAllByDifficulty("easy", pageable)).thenReturn(recipePage);

        // Act
        Page<RecipeDTO> result = recipeService.getTrendingRecipesByDifficulty(0, 10, "easy");

        // Assert
        assertEquals(2, result.getTotalElements());
        List<RecipeDTO> content = result.getContent();

        assertEquals("Recipe1", content.get(0).getName());
        assertEquals(1, content.get(0).getPosition());
        assertEquals("easy", content.get(0).getDifficulty());

        assertEquals("Recipe3", content.get(1).getName());
        assertEquals(3, content.get(1).getPosition());
        assertEquals("easy", content.get(1).getDifficulty());
    }
}
