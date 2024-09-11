package com.example.recime.integration.service;

import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeServiceIntegrationTest {

    private final RecipeService recipeService;

    @Autowired
    public RecipeServiceIntegrationTest(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Test
    public void testGetTrendingRecipes() {

        final Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipes(0, 10, "position", "asc");

        Assertions.assertEquals(2, recipeDTOPage.getTotalPages());
        Assertions.assertEquals(20, recipeDTOPage.getTotalElements());
        Assertions.assertEquals("Classic Cheeseburger", recipeDTOPage.getContent().get(0).getName());

    }

    @Test
    public void testGetTrendingRecipesByDifficulty() {

        final Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipesByDifficulty(0, 10, "position","asc","easy");

        Assertions.assertEquals(1, recipeDTOPage.getTotalPages());
        Assertions.assertEquals(3, recipeDTOPage.getTotalElements());
        Assertions.assertEquals("Classic Cheeseburger", recipeDTOPage.getContent().get(0).getName());
        Assertions.assertEquals(1, recipeDTOPage.getContent().get(0).getPosition());
        Assertions.assertEquals("easy", recipeDTOPage.getContent().get(0).getDifficulty());
        Assertions.assertEquals("Hawaiian Burger", recipeDTOPage.getContent().get(1).getName());
        Assertions.assertEquals(8, recipeDTOPage.getContent().get(1).getPosition());
        Assertions.assertEquals("easy", recipeDTOPage.getContent().get(1).getDifficulty());

    }
}
