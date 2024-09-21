package com.example.recime.model.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientDTO {

    private String name;
    @EqualsAndHashCode.Exclude
    private Double quantity;
    @EqualsAndHashCode.Exclude
    private String unit;

}
