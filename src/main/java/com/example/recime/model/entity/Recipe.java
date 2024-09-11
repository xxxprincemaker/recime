package com.example.recime.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
