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
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
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

import java.util.List;

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
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}),
    })
    @GetMapping(value = "v1/search/findAllTrendingRecipes", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<RecipeDTO>>> getTrendingRecipes(
            @Valid @Parameter(description = "The page number to retrieve.") @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @Valid @Parameter(description = "The number of recipes per page.") @RequestParam(defaultValue = "5") @Min(1) Integer size,
            @Valid @Parameter(description = "The field by which to sort the recipes.") @RequestParam(defaultValue = "position") @Pattern(regexp = "^(position|name)$", message = "Invalid sort format") String sortBy,
            @Valid @Parameter(description = "The sorting direction (ascending or descending).") @RequestParam(defaultValue = "asc") @Pattern(regexp = "^(asc|desc)$", message = "Invalid sort format") String direction) {

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
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}),
    })
    @GetMapping(value = "v1/search/findAllTrendingRecipesByDifficulty", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<RecipeDTO>>> getTrendingRecipesByDifficulty(
            @Valid @Parameter(description = "The page number to retrieve.") @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @Valid @Parameter(description = "The number of recipes per page.") @RequestParam(defaultValue = "5") @Min(1) Integer size,
            @Valid @Parameter(description = "The field by which to sort the recipes.") @RequestParam(defaultValue = "position") @Pattern(regexp = "^(position|name)$", message = "Invalid sort format") String sortBy,
            @Valid @Parameter(description = "The difficulty level of the recipes to filter by.") @RequestParam @Pattern(regexp = "^(easy|medium|hard)$") String difficulty,
            @Valid @Parameter(description = "The sorting direction (ascending or descending).") @RequestParam(defaultValue = "asc") @Pattern(regexp = "^(asc|desc)$", message = "Invalid direction format") String direction) {

        Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipesByDifficulty(page, size, sortBy, direction, difficulty);
        PagedModel<EntityModel<RecipeDTO>> pagedModel = pagedResourcesAssembler.toModel(recipeDTOPage);
        return ResponseEntity.ok(pagedModel);
    }


    @Operation(summary = "Retrieve trending recipes by ingredients",
            description = "Fetch a paginated list of trending recipes by ingredients, with support for sorting and pagination.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A paginated list of trending recipes."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request if parameters are invalid.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))}),
    })
    @GetMapping(value = "v1/search/findAllTrendingRecipesByIngredients", produces = "application/json")
    public ResponseEntity<PagedModel<EntityModel<RecipeDTO>>> getTrendingRecipesByIngredient(
            @Valid @Parameter(description = "The page number to retrieve.") @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @Valid @Parameter(description = "The number of recipes per page.") @RequestParam(defaultValue = "5") @Min(1) Integer size,
            @Valid @Parameter(description = "The field by which to sort the recipes.") @RequestParam(defaultValue = "position") @Pattern(regexp = "^(position|name)$", message = "Invalid sort format") String sortBy,
            @Valid @Parameter(description = "The sorting direction (ascending or descending).") @RequestParam(defaultValue = "asc") @Pattern(regexp = "^(asc|desc)$", message = "Invalid sort format") String direction,
            @RequestParam List<String> ingredients,
            @Parameter(description = "The boolean value to check if need only recipes with the ingredient or recipes that only have the ingredient") @RequestParam(defaultValue = "false") Boolean onlyIngredient
    ) {
        Page<RecipeDTO> recipeDTOPage = recipeService.getTrendingRecipesByIngredient(page, size, sortBy, direction, ingredients, onlyIngredient);
        PagedModel<EntityModel<RecipeDTO>> pagedModel = pagedResourcesAssembler.toModel(recipeDTOPage);
        return ResponseEntity.ok(pagedModel);
    }
}
