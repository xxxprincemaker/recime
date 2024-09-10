package com.example.recime.controller;

import com.example.recime.model.Recipe;
import com.example.recime.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/v1")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/getTrendingRecipes")
    private List<Recipe> getTrendingRecipes(){
        return recipeService.getTrendingRecipes();
    }

}
