package com.example.recime.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "Recipe")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "IMAGEURL")
    private String imageUrl;
    @Column(name = "RECIPE")
    private String recipe;
    @Column(name = "POSITION")
    private Integer position;
    @Column(name = "DIFFICULTY")
    private String difficulty;

    @ElementCollection
    @CollectionTable(name = "Recipe_Ingredient", joinColumns = @JoinColumn(name = "RECIPE_ID"))
    @MapKeyJoinColumn(name = "INGREDIENT_ID")
    @Column(name = "QUANTITY")
    private Map<Ingredient, Double> ingredients;
}
