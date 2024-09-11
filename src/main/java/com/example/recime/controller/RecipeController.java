package com.example.recime.controller;

import com.example.recime.model.dto.RecipeDTO;
import com.example.recime.model.rest.ResponseError;
import com.example.recime.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final PagedResourcesAssembler<RecipeDTO> pagedResourcesAssembler;

    @Operation(summary = "Retrieve trending recipes",
            description = "Fetch a paginated list of trending recipes, with support for sorting and pagination.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A paginated list of trending recipes."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request if parameters are invalid.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}, useReturnTypeSchema = true),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}, useReturnTypeSchema = true),
    })
    @GetMapping(value = "/search/findAllTrendingRecipes", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<RecipeDTO>>> getTrendingRecipes(
            @Parameter(description = "The page number to retrieve.") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "The number of recipes per page.") @RequestParam(defaultValue = "5") Integer size,
            @Parameter(description = "The field by which to sort the recipes.") @RequestParam(defaultValue = "position") String sortBy,
            @Parameter(description = "The sorting direction (ascending or descending).") @RequestParam(defaultValue = "asc") String direction) {

        Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipes(page, size, sortBy, direction);
        PagedModel<EntityModel<RecipeDTO>> pagedModel = pagedResourcesAssembler.toModel(recipeDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Retrieve trending recipes by difficulty",
            description = "Fetch a paginated list of trending recipes filtered by difficulty, with support for sorting and pagination.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "A paginated list of trending recipes filtered by difficulty."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request if parameters are invalid.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}, useReturnTypeSchema = true),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}, useReturnTypeSchema = true),
    })
    @GetMapping(value = "/search/findAllTrendingRecipesByDifficulty", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<RecipeDTO>>> getTrendingRecipesByDifficulty(
            @Parameter(description = "The page number to retrieve.") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "The number of recipes per page.") @RequestParam(defaultValue = "5") Integer size,
            @Parameter(description = "The field by which to sort the recipes.") @RequestParam(defaultValue = "position") String sortBy,
            @Parameter(description = "The difficulty level of the recipes to filter by.") @RequestParam String difficulty,
            @Parameter(description = "The sorting direction (ascending or descending).") @RequestParam(defaultValue = "asc") String direction) {

        Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipesByDifficulty(page, size, sortBy, direction, difficulty);
        PagedModel<EntityModel<RecipeDTO>> pagedModel = pagedResourcesAssembler.toModel(recipeDTOPage);
        return ResponseEntity.ok(pagedModel);
    }
}
