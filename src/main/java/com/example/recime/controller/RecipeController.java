package com.example.recime.controller;

import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    private final PagedResourcesAssembler<RecipeDTO> pagedResourcesAssembler;

    @GetMapping(value = "/getTrendingRecipes", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<RecipeDTO>>> getTrendingRecipes(@RequestParam(defaultValue = "0") Integer page,
                                                                                 @RequestParam(defaultValue = "5") Integer size,
                                                                                 @RequestParam(defaultValue = "position") String sortBy,
                                                                                 @RequestParam(defaultValue = "asc") String direction) {
        Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipes(page, size, sortBy, direction);
        PagedModel<EntityModel<RecipeDTO>> pagedModel = pagedResourcesAssembler.toModel(recipeDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping(value = "/getTrendingRecipesByDifficulty", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<RecipeDTO>>> getTrendingRecipesByDifficulty(@RequestParam(defaultValue = "0") Integer page,
                                                                                             @RequestParam(defaultValue = "5") Integer size,
                                                                                             @RequestParam(defaultValue = "position") String sortBy,
                                                                                             @RequestParam String difficulty,
                                                                                             @RequestParam(defaultValue = "asc") String direction) {
        Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipesByDifficulty(page, size, sortBy, direction,difficulty);
        PagedModel<EntityModel<RecipeDTO>> pagedModel = pagedResourcesAssembler.toModel(recipeDTOPage);
        return ResponseEntity.ok(pagedModel);
    }
}
