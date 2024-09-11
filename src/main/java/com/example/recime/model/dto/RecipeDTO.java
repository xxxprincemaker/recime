package com.example.recime.model.dto;

import lombok.*;

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
}
