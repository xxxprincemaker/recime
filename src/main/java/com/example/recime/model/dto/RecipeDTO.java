package com.example.recime.model.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private String name;
    private String description;
    private String imageUrl;
    private String recipe;
    private Integer position;
    private String difficulty;
    private Set<IngredientDTO> ingredients;
}
